package com.seedxray.client.mixin;

import com.seedxray.client.render.TerrainTransparencyHooks;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer", remap = false)
public abstract class SodiumBlockRendererMixin {
    @Inject(method = "renderModel", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private void seedxray$hideClientOreBlocks(
            @Coerce Object model,
            @Coerce Object state,
            @Coerce Object pos,
            @Coerce Object origin,
            CallbackInfo ci
    ) {
        if (state instanceof BlockState blockState && TerrainTransparencyHooks.shouldHideClientBlockTexture(blockState)) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "bufferQuad",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/caffeinemc/mods/sodium/api/util/ColorARGB;toABGR(I)I",
                    remap = false
            ),
            index = 0,
            remap = false,
            require = 0
    )
    private int seedxray$terrainAlpha(int argb) {
        if (!TerrainTransparencyHooks.terrainTransparencyActive()) {
            return argb;
        }
        int alpha = Math.max(8, Math.min(255, Math.round(TerrainTransparencyHooks.terrainAlpha() * 255.0F)));
        return (argb & 0x00FFFFFF) | (alpha << 24);
    }
}

package com.seedxray.client.mixin;

import com.seedxray.client.render.TerrainTransparencyHooks;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.block.BlockModelRenderer.class)
public abstract class BlockModelRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void seedxray$hideClientOreBlocks(
            BlockRenderView world,
            List<BlockModelPart> parts,
            BlockState state,
            BlockPos pos,
            MatrixStack matrices,
            VertexConsumer vertexConsumer,
            boolean cull,
            int overlay,
            CallbackInfo ci
    ) {
        if (TerrainTransparencyHooks.shouldHideClientBlockTexture(state)) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderQuad",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumer;quad(Lnet/minecraft/client/util/math/MatrixStack$Entry;Lnet/minecraft/client/render/model/BakedQuad;[FFFFF[II)V"
            ),
            index = 6
    )
    private float seedxray$terrainAlpha(float originalAlpha) {
        return TerrainTransparencyHooks.terrainTransparencyActive() ? TerrainTransparencyHooks.terrainAlpha() : originalAlpha;
    }
}

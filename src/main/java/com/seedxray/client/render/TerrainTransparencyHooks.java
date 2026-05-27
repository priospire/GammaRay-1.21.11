package com.seedxray.client.render;

import com.seedxray.client.XrayClientMod;
import com.seedxray.client.config.XrayConfig;
import com.seedxray.client.util.BlockUtil;
import com.seedxray.client.util.RenderCompatibility;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;

public final class TerrainTransparencyHooks {
    private TerrainTransparencyHooks() {
    }

    public static boolean terrainTransparencyActive() {
        XrayConfig config = XrayClientMod.CONFIG.get();
        return config.enabled && config.useTerrainTransparency && !RenderCompatibility.shouldDisableTerrainAlphaHooks();
    }

    public static boolean shouldMakeBlockTransparent(BlockState state) {
        return terrainTransparencyActive() && state != null && !state.isAir();
    }

    public static boolean shouldHideClientBlockTexture(BlockState state) {
        return terrainTransparencyActive() && state != null && BlockUtil.oreTargetOf(state).isPresent();
    }

    public static float terrainAlpha() {
        XrayConfig config = XrayClientMod.CONFIG.get();
        float alpha = 1.0F - (config.transparencyPercent / 100.0F);
        return Math.max(0.01F, Math.min(1.0F, alpha));
    }

    public static void scheduleTerrainRefresh(MinecraftClient client) {
        if (client == null || client.worldRenderer == null) {
            return;
        }
        client.execute(() -> {
            if (client.worldRenderer != null) {
                client.worldRenderer.scheduleTerrainUpdate();
                client.worldRenderer.reload();
            }
        });
    }
}

package com.seedxray.client.render;

import com.seedxray.client.XrayClientMod;
import com.seedxray.client.config.XrayConfig;
import com.seedxray.client.util.RenderCompatibility;
import net.minecraft.client.gui.DrawContext;

public final class DimmingOverlayRenderer {
    private DimmingOverlayRenderer() {
    }

    public static void render(DrawContext context) {
        XrayConfig config = XrayClientMod.CONFIG.get();
        if (!config.enabled || (!config.useFallbackDimmingOverlay && !RenderCompatibility.shouldDisableTerrainAlphaHooks())) {
            return;
        }
        int alpha = Math.max(0, Math.min(150, (int) (config.transparencyPercent * 1.5F)));
        context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), (alpha << 24));
    }
}

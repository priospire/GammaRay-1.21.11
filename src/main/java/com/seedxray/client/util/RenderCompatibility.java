package com.seedxray.client.util;

import net.fabricmc.loader.api.FabricLoader;

public final class RenderCompatibility {
    private RenderCompatibility() {
    }

    public static boolean isVulkanRendererPresent() {
        FabricLoader loader = FabricLoader.getInstance();
        return loader.isModLoaded("vulkanmod") || loader.isModLoaded("vulkan");
    }

    public static boolean shouldDisableTerrainAlphaHooks() {
        return isVulkanRendererPresent();
    }
}

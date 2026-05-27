package com.seedxray.client.util;

import net.minecraft.client.world.ClientWorld;

public final class DimensionUtil {
    private DimensionUtil() {
    }

    public static String id(ClientWorld world) {
        return world.getRegistryKey().getValue().toString();
    }

    public static String shortName(String dimensionId) {
        return switch (dimensionId) {
            case "minecraft:overworld" -> "Overworld";
            case "minecraft:the_nether" -> "Nether";
            case "minecraft:the_end" -> "End";
            default -> dimensionId;
        };
    }
}

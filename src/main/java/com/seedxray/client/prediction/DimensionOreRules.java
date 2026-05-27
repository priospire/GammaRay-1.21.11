package com.seedxray.client.prediction;

import java.util.EnumSet;
import java.util.Set;

public final class DimensionOreRules {
    private DimensionOreRules() {
    }

    public static Set<OreTarget> targetsForDimension(String dimensionId) {
        if ("minecraft:overworld".equals(dimensionId)) {
            return EnumSet.of(
                    OreTarget.DIAMOND,
                    OreTarget.EMERALD,
                    OreTarget.GOLD,
                    OreTarget.IRON,
                    OreTarget.COAL,
                    OreTarget.COPPER,
                    OreTarget.REDSTONE,
                    OreTarget.LAPIS
            );
        }
        if ("minecraft:the_nether".equals(dimensionId)) {
            return EnumSet.of(OreTarget.ANCIENT_DEBRIS, OreTarget.NETHER_GOLD, OreTarget.NETHER_QUARTZ);
        }
        return EnumSet.noneOf(OreTarget.class);
    }
}

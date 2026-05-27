package com.seedxray.client.prediction;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum OreTarget {
    DIAMOND("Diamond", 0xFF20E7FF, DimensionGroup.OVERWORLD),
    EMERALD("Emerald", 0xFF21E86D, DimensionGroup.OVERWORLD),
    GOLD("Gold", 0xFFFFD447, DimensionGroup.OVERWORLD),
    IRON("Iron", 0xFFE6B17A, DimensionGroup.OVERWORLD),
    COAL("Coal", 0xFF36393F, DimensionGroup.OVERWORLD),
    COPPER("Copper", 0xFFFF8A3D, DimensionGroup.OVERWORLD),
    REDSTONE("Redstone", 0xFFFF3030, DimensionGroup.OVERWORLD),
    LAPIS("Lapis", 0xFF2E5DFF, DimensionGroup.OVERWORLD),
    ANCIENT_DEBRIS("Ancient Debris", 0xFFFF6A3D, DimensionGroup.NETHER),
    NETHER_GOLD("Nether Gold", 0xFFFFC533, DimensionGroup.NETHER),
    NETHER_QUARTZ("Nether Quartz", 0xFFF6F2E8, DimensionGroup.NETHER);

    private final String displayName;
    private final int defaultColor;
    private final DimensionGroup dimensionGroup;

    OreTarget(String displayName, int defaultColor, DimensionGroup dimensionGroup) {
        this.displayName = displayName;
        this.defaultColor = defaultColor;
        this.dimensionGroup = dimensionGroup;
    }

    public String displayName() {
        return displayName;
    }

    public int defaultColor() {
        return defaultColor;
    }

    public DimensionGroup dimensionGroup() {
        return dimensionGroup;
    }

    public Block[] blocks() {
        return switch (this) {
            case DIAMOND -> new Block[] {Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE};
            case EMERALD -> new Block[] {Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE};
            case GOLD -> new Block[] {Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE};
            case IRON -> new Block[] {Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE};
            case COAL -> new Block[] {Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE};
            case COPPER -> new Block[] {Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE};
            case REDSTONE -> new Block[] {Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE};
            case LAPIS -> new Block[] {Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE};
            case ANCIENT_DEBRIS -> new Block[] {Blocks.ANCIENT_DEBRIS};
            case NETHER_GOLD -> new Block[] {Blocks.NETHER_GOLD_ORE};
            case NETHER_QUARTZ -> new Block[] {Blocks.NETHER_QUARTZ_ORE};
        };
    }

    public boolean isSupportedPredictionTarget() {
        return true;
    }

    public boolean belongsInDimension(String dimensionId) {
        return dimensionGroup.belongsInDimension(dimensionId);
    }

    public enum DimensionGroup {
        OVERWORLD,
        NETHER,
        END;

        public boolean belongsInDimension(String dimensionId) {
            return switch (this) {
                case OVERWORLD -> "minecraft:overworld".equals(dimensionId);
                case NETHER -> "minecraft:the_nether".equals(dimensionId);
                case END -> "minecraft:the_end".equals(dimensionId);
            };
        }
    }
}

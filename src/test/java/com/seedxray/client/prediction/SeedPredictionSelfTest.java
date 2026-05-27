package com.seedxray.client.prediction;

import java.util.EnumSet;
import java.util.Set;

public final class SeedPredictionSelfTest {
    private static final long SELF_TEST_SEED = 1234567890123456789L;

    private SeedPredictionSelfTest() {
    }

    public static void main(String[] args) {
        int overworld = countRadius("minecraft:overworld", Set.of(
                OreTarget.DIAMOND,
                OreTarget.GOLD,
                OreTarget.IRON,
                OreTarget.REDSTONE,
                OreTarget.LAPIS,
                OreTarget.COAL,
                OreTarget.COPPER
        ), 2);
        int nether = countRadius("minecraft:the_nether", EnumSet.of(
                OreTarget.ANCIENT_DEBRIS,
                OreTarget.NETHER_GOLD,
                OreTarget.NETHER_QUARTZ
        ), 4);
        int ancientDebris = countRadius("minecraft:the_nether", Set.of(OreTarget.ANCIENT_DEBRIS), 8);

        if (overworld <= 0) {
            throw new IllegalStateException("Overworld seed prediction self-test produced zero records");
        }
        if (nether <= 0) {
            throw new IllegalStateException("Nether seed prediction self-test produced zero records");
        }
        if (ancientDebris <= 0) {
            throw new IllegalStateException("Ancient debris seed prediction self-test produced zero records");
        }
        System.out.println("Seed prediction self-test passed: overworld=" + overworld
                + ", nether=" + nether
                + ", ancient_debris_candidates=" + ancientDebris);
    }

    private static int countRadius(String dimensionId, Set<OreTarget> targets, int radius) {
        int count = 0;
        for (int chunkZ = -radius; chunkZ <= radius; chunkZ++) {
            for (int chunkX = -radius; chunkX <= radius; chunkX++) {
                count += VanillaOrePredictionEngine.countSeedPlacementOriginsForChunk(dimensionId, chunkX, chunkZ, SELF_TEST_SEED, targets);
            }
        }
        return count;
    }

}

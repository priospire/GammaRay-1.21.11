package com.seedxray.client.prediction;

import com.seedxray.client.XrayClientMod;

public final class WorldSeedContext {
    private WorldSeedContext() {
    }

    public static long serverSeed() {
        try {
            return XrayClientMod.CONFIG.get().worldSeed;
        } catch (RuntimeException ignored) {
            return 0L;
        }
    }
}

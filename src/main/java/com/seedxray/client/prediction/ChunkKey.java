package com.seedxray.client.prediction;

import net.minecraft.util.math.ChunkPos;

public record ChunkKey(String dimensionId, int chunkX, int chunkZ) {
    public ChunkPos chunkPos() {
        return new ChunkPos(chunkX, chunkZ);
    }
}

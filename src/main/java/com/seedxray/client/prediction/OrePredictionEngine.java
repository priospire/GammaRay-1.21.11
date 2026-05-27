package com.seedxray.client.prediction;

import net.minecraft.client.world.ClientWorld;

public interface OrePredictionEngine {
    OrePredictionResult predictChunk(ClientWorld world, ChunkKey chunkKey, long tick);
}

package com.seedxray.client.prediction;

import net.minecraft.client.world.ClientWorld;

public final class DisabledPredictionEngine implements OrePredictionEngine {
    @Override
    public OrePredictionResult predictChunk(ClientWorld world, ChunkKey chunkKey, long tick) {
        return new OrePredictionResult(chunkKey, true, "Prediction disabled");
    }
}

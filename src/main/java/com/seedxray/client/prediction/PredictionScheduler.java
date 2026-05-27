package com.seedxray.client.prediction;

import com.seedxray.client.XrayClientMod;
import com.seedxray.client.XrayConstants;
import com.seedxray.client.config.XrayConfig;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.ChunkPos;

public final class PredictionScheduler {
    private final OrePredictionEngine engine;
    private final PredictionCache cache;
    private final Queue<ChunkKey> queue = new ArrayDeque<>();
    private final Set<ChunkKey> queued = new HashSet<>();

    public PredictionScheduler(OrePredictionEngine engine, PredictionCache cache) {
        this.engine = engine;
        this.cache = cache;
    }

    public void tick(MinecraftClient client) {
        XrayConfig config = XrayClientMod.CONFIG.get();
        if (!config.displayMode.showsPredictions() && !config.displayMode.showsDiagnostics()) {
            return;
        }
        if (client.world == null || client.player == null) {
            return;
        }

        enqueueNearby(client.world, client.player, config);

        int budget = config.maxPredictedChunksPerTick;
        while (budget-- > 0 && !queue.isEmpty()) {
            ChunkKey key = queue.poll();
            queued.remove(key);
            if (cache.hasChunk(key)) {
                continue;
            }
            cache.put(engine.predictChunk(client.world, key, client.world.getTime()));
        }
    }

    private void enqueueNearby(ClientWorld world, ClientPlayerEntity player, XrayConfig config) {
        String dimensionId = world.getRegistryKey().getValue().toString();
        if (DimensionOreRules.targetsForDimension(dimensionId).stream().noneMatch(target -> target.isSupportedPredictionTarget() && config.isOreEnabled(target))) {
            return;
        }

        ChunkPos center = player.getChunkPos();
        int radius = config.predictionRadiusChunks + XrayConstants.PREDICTION_ORIGIN_MARGIN_CHUNKS;
        for (int ring = 0; ring <= radius; ring++) {
            for (int dz = -ring; dz <= ring; dz++) {
                for (int dx = -ring; dx <= ring; dx++) {
                    if (Math.max(Math.abs(dx), Math.abs(dz)) != ring) {
                        continue;
                    }
                    ChunkKey key = new ChunkKey(dimensionId, center.x + dx, center.z + dz);
                    if (!cache.hasChunk(key) && queued.add(key)) {
                        queue.add(key);
                    }
                }
            }
        }
    }

    public int queueSize() {
        return queue.size();
    }

    public void clearQueue() {
        queue.clear();
        queued.clear();
    }
}

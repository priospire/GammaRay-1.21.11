package com.seedxray.client.diagnostic;

import com.seedxray.client.prediction.OrePredictionRecord;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public final class DiagnosticCache {
    private final Map<String, Map<Long, OrePredictionRecord>> unpredictedClientOres = new HashMap<>();

    public synchronized void putUnpredicted(OrePredictionRecord record) {
        unpredictedClientOres
                .computeIfAbsent(record.dimensionId(), ignored -> new HashMap<>())
                .put(record.pos().asLong(), record);
    }

    public synchronized boolean hasUnpredicted(String dimensionId, BlockPos pos) {
        Map<Long, OrePredictionRecord> records = unpredictedClientOres.get(dimensionId);
        return records != null && records.containsKey(pos.asLong());
    }

    public synchronized Collection<OrePredictionRecord> unpredictedForDimension(String dimensionId) {
        Map<Long, OrePredictionRecord> records = unpredictedClientOres.get(dimensionId);
        if (records == null) {
            return ListOf.none();
        }
        return new ArrayList<>(records.values());
    }

    public synchronized List<OrePredictionRecord> unpredictedNear(String dimensionId, ChunkPos center, int radiusChunks) {
        Map<Long, OrePredictionRecord> records = unpredictedClientOres.get(dimensionId);
        if (records == null) {
            return List.of();
        }
        List<OrePredictionRecord> nearby = new ArrayList<>();
        for (OrePredictionRecord record : records.values()) {
            ChunkPos pos = record.chunkPos();
            if (Math.abs(pos.x - center.x) <= radiusChunks && Math.abs(pos.z - center.z) <= radiusChunks) {
                nearby.add(record);
            }
        }
        return nearby;
    }

    public synchronized int unpredictedCount(String dimensionId) {
        Map<Long, OrePredictionRecord> records = unpredictedClientOres.get(dimensionId);
        return records == null ? 0 : records.size();
    }

    public synchronized void clear() {
        unpredictedClientOres.clear();
    }

    public synchronized void clearNonDimension(String dimensionId) {
        unpredictedClientOres.keySet().removeIf(key -> !key.equals(dimensionId));
    }

    private static final class ListOf {
        private static Collection<OrePredictionRecord> none() {
            return java.util.List.of();
        }
    }
}

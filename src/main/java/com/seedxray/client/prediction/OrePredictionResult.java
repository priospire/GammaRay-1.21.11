package com.seedxray.client.prediction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OrePredictionResult {
    private final ChunkKey chunkKey;
    private final List<OrePredictionRecord> records = new ArrayList<>();
    private final boolean exact;
    private final String accuracyNote;

    public OrePredictionResult(ChunkKey chunkKey, boolean exact, String accuracyNote) {
        this.chunkKey = chunkKey;
        this.exact = exact;
        this.accuracyNote = accuracyNote;
    }

    public ChunkKey chunkKey() {
        return chunkKey;
    }

    public void add(OrePredictionRecord record) {
        records.add(record);
    }

    public List<OrePredictionRecord> records() {
        return Collections.unmodifiableList(records);
    }

    public boolean exact() {
        return exact;
    }

    public String accuracyNote() {
        return accuracyNote;
    }
}

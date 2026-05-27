package com.seedxray.client.prediction;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.jetbrains.annotations.Nullable;

public final class OrePredictionRecord {
    private final BlockPos pos;
    private final ChunkPos chunkPos;
    private final String dimensionId;
    private final OreTarget oreTarget;
    @Nullable
    private final BlockState predictedBlockState;
    private final OreSource source;
    private PredictionVisibilityStatus visibilityStatus;
    @Nullable
    private BlockState clientSeenBlockState;
    private final long tickGenerated;
    private long lastDiagnosticScanTick;
    private final float confidence;
    private final String ruleSource;
    @Nullable
    private final String biome;

    public OrePredictionRecord(
            BlockPos pos,
            ChunkPos chunkPos,
            String dimensionId,
            OreTarget oreTarget,
            OreSource source,
            PredictionVisibilityStatus visibilityStatus,
            @Nullable BlockState clientSeenBlockState,
            long tickGenerated,
            long lastDiagnosticScanTick,
            float confidence,
            String ruleSource,
            @Nullable String biome
    ) {
        this(
                pos,
                chunkPos,
                dimensionId,
                oreTarget,
                null,
                source,
                visibilityStatus,
                clientSeenBlockState,
                tickGenerated,
                lastDiagnosticScanTick,
                confidence,
                ruleSource,
                biome
        );
    }

    public OrePredictionRecord(
            BlockPos pos,
            ChunkPos chunkPos,
            String dimensionId,
            OreTarget oreTarget,
            @Nullable BlockState predictedBlockState,
            OreSource source,
            PredictionVisibilityStatus visibilityStatus,
            @Nullable BlockState clientSeenBlockState,
            long tickGenerated,
            long lastDiagnosticScanTick,
            float confidence,
            String ruleSource,
            @Nullable String biome
    ) {
        this.pos = pos.toImmutable();
        this.chunkPos = chunkPos;
        this.dimensionId = dimensionId;
        this.oreTarget = oreTarget;
        this.predictedBlockState = predictedBlockState;
        this.source = source;
        this.visibilityStatus = visibilityStatus;
        this.clientSeenBlockState = clientSeenBlockState;
        this.tickGenerated = tickGenerated;
        this.lastDiagnosticScanTick = lastDiagnosticScanTick;
        this.confidence = confidence;
        this.ruleSource = ruleSource;
        this.biome = biome;
    }

    public BlockPos pos() {
        return pos;
    }

    public ChunkPos chunkPos() {
        return chunkPos;
    }

    public String dimensionId() {
        return dimensionId;
    }

    public OreTarget oreTarget() {
        return oreTarget;
    }

    @Nullable
    public BlockState predictedBlockState() {
        return predictedBlockState;
    }

    public OreSource source() {
        return source;
    }

    public PredictionVisibilityStatus visibilityStatus() {
        return visibilityStatus;
    }

    public void setVisibilityStatus(PredictionVisibilityStatus visibilityStatus) {
        this.visibilityStatus = visibilityStatus;
    }

    @Nullable
    public BlockState clientSeenBlockState() {
        return clientSeenBlockState;
    }

    public void setClientSeenBlockState(@Nullable BlockState clientSeenBlockState) {
        this.clientSeenBlockState = clientSeenBlockState;
    }

    public long tickGenerated() {
        return tickGenerated;
    }

    public long lastDiagnosticScanTick() {
        return lastDiagnosticScanTick;
    }

    public void setLastDiagnosticScanTick(long lastDiagnosticScanTick) {
        this.lastDiagnosticScanTick = lastDiagnosticScanTick;
    }

    public float confidence() {
        return confidence;
    }

    public String ruleSource() {
        return ruleSource;
    }

    @Nullable
    public String biome() {
        return biome;
    }
}

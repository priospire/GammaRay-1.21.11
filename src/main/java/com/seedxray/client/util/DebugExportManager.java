package com.seedxray.client.util;

import com.seedxray.client.XrayClientMod;
import com.seedxray.client.prediction.OrePredictionRecord;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public final class DebugExportManager {
    private DebugExportManager() {
    }

    public static Path exportCurrentDimension() throws IOException {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            throw new IOException("No world loaded");
        }
        String dimensionId = client.world.getRegistryKey().getValue().toString();
        Path exportDir = FabricLoader.getInstance().getConfigDir().resolve("seed-xray-exports");
        Files.createDirectories(exportDir);
        Path output = exportDir.resolve("seed-xray-" + dimensionId.replace(':', '_') + "-" + System.currentTimeMillis() + ".csv");
        try (Writer writer = Files.newBufferedWriter(output)) {
            writer.write("source,dimension,x,y,z,ore,status,client_block,confidence,rule\n");
            for (OrePredictionRecord record : XrayClientMod.PREDICTION_CACHE.recordsForDimension(dimensionId)) {
                writeRecord(writer, record);
            }
            for (OrePredictionRecord record : XrayClientMod.DIAGNOSTIC_CACHE.unpredictedForDimension(dimensionId)) {
                writeRecord(writer, record);
            }
        }
        return output;
    }

    private static void writeRecord(Writer writer, OrePredictionRecord record) throws IOException {
        writer.write(record.source().name());
        writer.write(',');
        writer.write(record.dimensionId());
        writer.write(',');
        writer.write(Integer.toString(record.pos().getX()));
        writer.write(',');
        writer.write(Integer.toString(record.pos().getY()));
        writer.write(',');
        writer.write(Integer.toString(record.pos().getZ()));
        writer.write(',');
        writer.write(record.oreTarget().name());
        writer.write(',');
        writer.write(record.visibilityStatus().name());
        writer.write(',');
        writer.write(record.clientSeenBlockState() == null ? "" : BlockUtil.blockName(record.clientSeenBlockState()));
        writer.write(',');
        writer.write(Float.toString(record.confidence()));
        writer.write(',');
        writer.write(record.ruleSource().replace(',', ';'));
        writer.write('\n');
    }
}

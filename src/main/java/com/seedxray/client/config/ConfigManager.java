package com.seedxray.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seedxray.client.XrayConstants;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

public final class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Path configPath = FabricLoader.getInstance().getConfigDir().resolve(XrayConstants.MOD_ID + ".json");
    private XrayConfig config = new XrayConfig();

    public XrayConfig get() {
        return config;
    }

    public Path path() {
        return configPath;
    }

    public void load() {
        if (Files.exists(configPath)) {
            try (Reader reader = Files.newBufferedReader(configPath)) {
                JsonElement element = JsonParser.parseReader(reader);
                boolean hasWorldSeed = element instanceof JsonObject object && object.has("worldSeed");
                XrayConfig loaded = GSON.fromJson(element, XrayConfig.class);
                if (loaded != null) {
                    config = loaded;
                    config.seedConfigured = config.seedConfigured || hasWorldSeed;
                }
            } catch (IOException | RuntimeException ignored) {
                config = new XrayConfig();
            }
        }
        config.applyDefaults();
        save();
    }

    public void save() {
        config.applyDefaults();
        try {
            Files.createDirectories(configPath.getParent());
            try (Writer writer = Files.newBufferedWriter(configPath)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException ignored) {
        }
    }
}

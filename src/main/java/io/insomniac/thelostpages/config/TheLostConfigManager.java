package io.insomniac.thelostpages.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.insomniac.thelostpages.TheLostPages;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TheLostConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("thelostpages.json")
            .toFile();

    private static TheLostConfig INSTANCE;
    private static final TheLostConfig DEFAULTS = createDefaults();

    public static TheLostConfig get() {
        if (INSTANCE == null) {
            load();
        }
        return INSTANCE;
    }

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, TheLostConfig.class);
            } catch (IOException e) {
                TheLostPages.LOGGER.error("Failed to load config file! Falling back to defaults.", e);
                INSTANCE = DEFAULTS;
            }
        } else {
            INSTANCE = DEFAULTS;
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            TheLostPages.LOGGER.error("Failed to save config file!", e);
        }
    }

    public static TheLostConfig getDefaults() {
        return DEFAULTS;
    }


    private static TheLostConfig createDefaults() {
        TheLostConfig config = new TheLostConfig();

        config.blocks.defaultWeight = 10.0;
        // ...

        return config;
    }
}

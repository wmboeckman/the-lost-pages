package io.insomniac.thelostpages.client;

import io.insomniac.thelostpages.config.TheLostConfig;
import io.insomniac.thelostpages.config.TheLostConfigManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class TLPClothConfigScreen {

    private static final TheLostConfig DEFAULTS = TheLostConfigManager.getDefaults();

    public static Screen createScreen(Screen parent) {
        TheLostConfig config = TheLostConfigManager.get();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("The Lost Pages - Config"));

        builder.setSavingRunnable(() -> {
            // Save changes to disk
            TheLostConfigManager.save();
        });

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory blocksCategory = builder.getOrCreateCategory(Component.literal("General"));
        ConfigCategory spawnerEntitiesCategory = builder.getOrCreateCategory(Component.literal("Special Settings"));
        ConfigCategory lootTablesCategory = builder.getOrCreateCategory(Component.literal("IDK"));


        return builder.build();
    }
}
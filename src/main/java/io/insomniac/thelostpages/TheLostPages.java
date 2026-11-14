package io.insomniac.thelostpages;

import io.insomniac.thelostpages.blocks.ModBlocks;
import io.insomniac.thelostpages.entities.ModEntityTypes;
import io.insomniac.thelostpages.fluids.ModFluids;
import io.insomniac.thelostpages.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheLostPages implements ModInitializer {

    public static String MOD_ID = "thelostpages";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
        ModFluids.initialize();
        ModEntityTypes.initialize();
    }
}

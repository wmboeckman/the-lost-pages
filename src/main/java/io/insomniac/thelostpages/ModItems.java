package io.insomniac.thelostpages;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static void initialize() {
        /* SUSPICIOUS_SUBSTANCE */
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((creativeTab) -> creativeTab.accept(ModItems.SUSPICIOUS_SUBSTANCE));

        // Add the suspicious substance to the composting registry with a 30% chance of increasing the composter's level.
        CompostableRegistry.INSTANCE.add(ModItems.SUSPICIOUS_SUBSTANCE, 0.3f);

        // Add item to the registry of fuels. 20 ticks = 1 second.
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 2 * 20);
        });
    }

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static final Item SUSPICIOUS_SUBSTANCE = register(ModItemIds.SUSPICIOUS_SUBSTANCE, Item::new, new Item.Properties());
}

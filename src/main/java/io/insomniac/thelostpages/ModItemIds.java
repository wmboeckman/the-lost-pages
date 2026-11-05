package io.insomniac.thelostpages;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItemIds {
    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, name));
    }

    public static final ResourceKey<Item> ICHOR_BUCKET = create("ichor_bucket");

    public static final ResourceKey<Item> SUSPICIOUS_SUBSTANCE = create("suspicious_substance");
}

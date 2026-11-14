package io.insomniac.thelostpages.entities;

import io.insomniac.thelostpages.TheLostPages;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeIds {
    public static ResourceKey<EntityType<?>> BOOK_MAGIC = create("conjured_book");

    private static ResourceKey<EntityType<?>> create(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, name));
    }
}

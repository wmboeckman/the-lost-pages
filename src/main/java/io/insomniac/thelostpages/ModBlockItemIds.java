package io.insomniac.thelostpages;

import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class ModBlockItemIds {
    private static BlockItemId create_block_item(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, name);
        return BlockItemId.create(id, id);
    }

    private static ResourceKey<Block> create_block(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, name);
        return ResourceKey.create(Registries.BLOCK, id);
    }

    public static final BlockItemId LOST_BOOKSHELF = create_block_item("lost_bookshelf");

    public static final ResourceKey<Block> THICK_ICHOR = create_block("ichor");
}
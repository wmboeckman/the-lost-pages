package io.insomniac.thelostpages.blocks;

import io.insomniac.thelostpages.fluids.ModFluids;
import io.insomniac.thelostpages.fluids.ThickIchorFluid;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(ModBlocks.WET_PULP.asItem());
            creativeTab.accept(ModBlocks.METAMORPHIC_PULP.asItem());
            creativeTab.accept(ModBlocks.LOST_BOOKSHELF.asItem());
        });
    }

    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create the block instance
        Block block = register(id.block(), blockFactory, properties);

        // Create the block item instance
        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

        return block;
    }

    private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create the block instance
        Block block = blockFactory.apply(properties.setId(id));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static final Block LOST_BOOKSHELF = register(
            ModBlockItemIds.LOST_BOOKSHELF,
            LostBookshelf::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)
    );

    public static final Block WET_PULP = register(
            ModBlockItemIds.WET_PULP,
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)
    );

    public static final Block DENSE_PULP = register(
            ModBlockItemIds.DENSE_PULP,
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)
    );

    public static final Block METAMORPHIC_PULP = register(
            ModBlockItemIds.METAMORPHIC_PULP,
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
    );

    public static final Block THICK_ICHOR = register(
            ModBlockItemIds.THICK_ICHOR,
            (props) -> new LiquidBlock(ModFluids.THICK_ICHOR_SOURCE, props),
            ThickIchorFluid.blockProperties
    );
}
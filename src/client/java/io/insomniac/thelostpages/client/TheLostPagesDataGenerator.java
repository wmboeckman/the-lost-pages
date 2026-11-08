package io.insomniac.thelostpages.client;

import io.insomniac.thelostpages.*;
import io.insomniac.thelostpages.blocks.ModBlocks;
import io.insomniac.thelostpages.fluids.ModFluids;
import io.insomniac.thelostpages.items.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class TheLostPagesDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EnglishLangProvider::new);
        pack.addProvider(ModFluidTagProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }

    public static class EnglishLangProvider extends FabricLanguageProvider {
        protected EnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
            // Specifying en_us is optional, as it's the default language code
            super(dataOutput, "en_us", registryLookup);
        }

        @Override
        public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
            translationBuilder.add("block."+ TheLostPages.MOD_ID +".lost_bookshelf", "Strange Bookshelf");
            translationBuilder.add("block."+ TheLostPages.MOD_ID +".wet_pulp", "Wet Pulp");
            translationBuilder.add("block."+ TheLostPages.MOD_ID +".dense_pulp", "Dense Pulp");
            translationBuilder.add("block."+ TheLostPages.MOD_ID +".metamorphic_pulp", "Metamorphic Pulp");

            translationBuilder.add("block."+ TheLostPages.MOD_ID +".ichor", "Thick Ichor");
            translationBuilder.add("item."+ TheLostPages.MOD_ID +".ichor_bucket", "Bucket of Ichor");

            translationBuilder.add("item."+ TheLostPages.MOD_ID +".suspicious_substance", "Suspicious Substance");
        }
    }

    public static class ModFluidTagProvider extends FabricTagsProvider.FluidTagsProvider {

        public ModFluidTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
            super(output, registryLookupFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider registries) {
            builder(ModTags.ICHOR)
                    .add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.THICK_ICHOR_FLOWING).orElseThrow())
                    .add(BuiltInRegistries.FLUID.getResourceKey(ModFluids.THICK_ICHOR_SOURCE).orElseThrow());
        }

        @Override
        public @NonNull String getName() {
            return "ModFluidTagsProvider";
        }
    }

    public static class ModModelProvider extends FabricModelProvider {
        public ModModelProvider(FabricPackOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
            blockStateModelGenerator.createTrivialBlock(ModBlocks.LOST_BOOKSHELF, TexturedModel.COLUMN_ALT);
            blockStateModelGenerator.createTrivialBlock(ModBlocks.WET_PULP, TexturedModel.CUBE);
            blockStateModelGenerator.createTrivialBlock(ModBlocks.DENSE_PULP, TexturedModel.CUBE);
            blockStateModelGenerator.createTrivialBlock(ModBlocks.METAMORPHIC_PULP, TexturedModel.CUBE);
        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerator) {
            itemModelGenerator.generateFlatItem(ModItems.ICHOR_BUCKET, ModelTemplates.FLAT_HANDHELD_ITEM);

            itemModelGenerator.generateFlatItem(ModItems.SUSPICIOUS_SUBSTANCE, ModelTemplates.FLAT_ITEM);
        }

        @Override
        public @NonNull String getName() {
            return "ModModelProvider";
        }
    }

    public static class ModRecipeProvider extends FabricRecipeProvider {
        public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
            return new RecipeProvider(registryLookup, exporter) {
                @Override
                public void buildRecipes() {
                    HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                    shapeless(RecipeCategory.BUILDING_BLOCKS, Items.DIRT) // You can also specify an int to produce more than one
                            .requires(Items.COARSE_DIRT) // You can also specify an int to require more than one, or a tag to accept multiple things
                            // Create an advancement that gives you the recipe
                            .unlockedBy(getHasName(Items.COARSE_DIRT), has(Items.COARSE_DIRT))
                            .save(output);
                }
            };
        }

        @Override
        public @NonNull String getName() {
            return "ModRecipeProvider";
        }
    }
}


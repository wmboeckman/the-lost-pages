package io.insomniac.thelostpages.client;

import io.insomniac.thelostpages.client.renderer.BookTestEntityRenderer;
import io.insomniac.thelostpages.client.renderer.ModEntityModelLayers;
import io.insomniac.thelostpages.entities.ModEntityTypes;
import io.insomniac.thelostpages.fluids.ModFluids;
import io.insomniac.thelostpages.TheLostPages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public class TheLostPagesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FluidRenderingRegistry.register(
                ModFluids.THICK_ICHOR_SOURCE,
                ModFluids.THICK_ICHOR_FLOWING,

                new FluidModel.Unbaked(
                        new Material(Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, "block/ichor_still")),
                        new Material(Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, "block/ichor_flow")),
                        new Material(Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, "block/ichor_overlay")),
                        BlockTintSources.constant(ARGB.opaque(0x132C3B))
                )

        );

        ModEntityModelLayers.registerModelLayers();
        EntityRenderers.register(ModEntityTypes.BOOK_MAGIC, BookTestEntityRenderer::new);
    }
}

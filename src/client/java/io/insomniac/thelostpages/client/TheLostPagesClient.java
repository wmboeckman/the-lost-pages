package io.insomniac.thelostpages.client;

import io.insomniac.thelostpages.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.block.FluidModel;
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
                        new Material(Identifier.withDefaultNamespace("block/lava_still")),
                        new Material(Identifier.withDefaultNamespace("block/lava_flow")),
                        new Material(Identifier.withDefaultNamespace("block/lava_overlay")),
                        BlockTintSources.constant(ARGB.opaque(0x0a1f1c))
                )
        );
    }
}

package io.insomniac.thelostpages.fluids;

import io.insomniac.thelostpages.TheLostPages;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.Fluid;

public class ModFluidIds {
    public static final ResourceKey<Fluid> THICK_ICHOR_FLOWING = create("thick_ichor_flowing");
    public static final ResourceKey<Fluid> THICK_ICHOR_SOURCE = create("thick_ichor");

    public static ResourceKey<Fluid> create(String name) {
        // Create the fluid key.
        return ResourceKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, name));
    }
}

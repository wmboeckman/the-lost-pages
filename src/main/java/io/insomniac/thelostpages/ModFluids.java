package io.insomniac.thelostpages;

import io.insomniac.thelostpages.fluids.ThickIchorFluid;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

public class ModFluids {
    public static final FlowingFluid THICK_ICHOR_FLOWING = register(ModFluidIds.THICK_ICHOR_FLOWING, new ThickIchorFluid.Flowing());
    public static final FlowingFluid THICK_ICHOR_SOURCE = register(ModFluidIds.THICK_ICHOR_SOURCE, new ThickIchorFluid.Source());

    private static FlowingFluid register(ResourceKey<Fluid> key, FlowingFluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, key, fluid);
    }

    public static void initialize() {
    }
}

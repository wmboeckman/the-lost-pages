package io.insomniac.thelostpages;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
    public static final TagKey<Fluid> ICHOR = TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, "ichor"));
}

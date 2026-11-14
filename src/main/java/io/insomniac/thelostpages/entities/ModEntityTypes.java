package io.insomniac.thelostpages.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class ModEntityTypes {
    public static final EntityType<BookTestEntity> BOOK_MAGIC = register(
            ModEntityTypeIds.BOOK_MAGIC,
            EntityType.Builder.<BookTestEntity>of(BookTestEntity::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f),
            BookTestEntity.createAttributes()
    );

    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    private static <T extends LivingEntity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder, AttributeSupplier.Builder attributes) {
        EntityType<T> type = register(key, builder);
        FabricDefaultAttributeRegistry.register(type, attributes);
        return type;
    }

    public static void initialize() {
    }
}

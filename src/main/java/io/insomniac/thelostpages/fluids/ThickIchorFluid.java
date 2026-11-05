package io.insomniac.thelostpages.fluids;

import io.insomniac.thelostpages.ModBlocks;
import io.insomniac.thelostpages.ModFluids;
import io.insomniac.thelostpages.ModItems;
import io.insomniac.thelostpages.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public abstract class ThickIchorFluid extends FlowingFluid {

    public static final BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.FIRE)
            .replaceable()
            .noCollision()
            .randomTicks()
            .strength(100.0F)
            .lightLevel(_ -> 0)
            .pushReaction(PushReaction.DESTROY)
            .noLootTable()
            .liquid()
            .sound(SoundType.EMPTY);


    @Override
    public void animateTick(Level world, BlockPos pos, FluidState state, RandomSource random) {
        if (!state.isSource() && !(Boolean) state.getValue(FALLING)) {
            if (random.nextInt(64) == 0) {
                world.playLocalSound(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, // Bubbling poison/swamp sound
                        SoundSource.AMBIENT,
                        random.nextFloat() * 0.25F + 0.75F,
                        random.nextFloat() + 0.5F,
                        false);
            }
        } else if (random.nextInt(10) == 0) {
            world.addParticle(
                    ParticleTypes.SQUID_INK, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(),
                    pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }

    @Override
    protected boolean canConvertToSource(ServerLevel world) {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropResources(state, world, pos, blockEntity);
    }

    @Override
    protected void entityInside(Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler) {
        handler.apply(InsideBlockEffectType.EXTINGUISH);

        if (!(world instanceof ServerLevel serverLevel) || !(entity instanceof LivingEntity livingEntity)) return;

        if (world.getGameTime() % 20 == 0) {
            // Hurt and weaken entities that step inside.
            livingEntity.hurtServer(serverLevel, world.damageSources().magic(), 1.0F); // 1 heart/sec
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 150, -3));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 150, -3));
        }
    }

    @Override
    protected int getSlopeFindDistance(LevelReader world) {
        return 2;
    }

    @Override
    public int getDropOff(LevelReader world) {
        return 2;
    }

    @Override
    public int getTickDelay(LevelReader world) {
        return 45;
    }

    @Override
    public boolean canBeReplacedWith(FluidState state, BlockGetter world, BlockPos pos, Fluid fluid,
                                     Direction direction) {
        return direction == Direction.DOWN && !fluid.is(ModTags.ICHOR);
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    @Override
    public @NonNull Fluid getFlowing() {
        return ModFluids.THICK_ICHOR_FLOWING;
    }

    @Override
    public @NonNull Fluid getSource() {
        return ModFluids.THICK_ICHOR_SOURCE;
    }

    @Override
    public boolean isSame(@NonNull Fluid fluid) {
        return fluid == ModFluids.THICK_ICHOR_SOURCE || fluid == ModFluids.THICK_ICHOR_FLOWING;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return ModBlocks.THICK_ICHOR.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public Item getBucket() {
        return ModItems.ICHOR_BUCKET;
    }

    public static class Flowing extends ThickIchorFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends ThickIchorFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}

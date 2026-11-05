package io.insomniac.thelostpages.mixins;

import io.insomniac.thelostpages.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Mixin(Entity.class)
public abstract class EntityFluidInteractionMixin {

    @Shadow protected boolean firstTick;
    @Final @Shadow private EntityFluidInteraction fluidInteraction;

    @Shadow public abstract Level level();
    @Shadow public abstract Vec3 position();
    @Shadow public abstract void makeStuckInBlock(BlockState state, Vec3 motionMultiplier);

    /// Hook into the fluidInteraction variable in Entity to inject custom fluid tags.
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/EntityFluidInteraction;<init>(Ljava/util/Set;)V"
            ),
            index = 0
    )
    private Set<TagKey<Fluid>> thickichor$addThickIchorTag(Set<TagKey<Fluid>> original) {
        // The original set may be immutable (Set.of), so copy it before adding
        Set<TagKey<Fluid>> tags = new HashSet<>(original);

        // ADD TAGS HERE
        tags.add(ModTags.ICHOR);

        return tags;
    }


    @Inject(method = "baseTick", at = @At("TAIL"))
    private void thickichor$slowInFluid(CallbackInfo ci) {
        try (Level level = this.level()) {

            if (!this.firstTick && this.fluidInteraction.isInFluid(ModTags.ICHOR)) {
                this.makeStuckInBlock(level.getBlockState(BlockPos.containing(this.position())), new Vec3(0.3, 0.1, 0.3));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


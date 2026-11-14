package io.insomniac.thelostpages.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class BookOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (Math.random() < 0.1) {
			world.addParticle(ParticleTypes.ENCHANT, x, y, z, (Mth.nextInt(RandomSource.create(), -1, 1)), (Mth.nextInt(RandomSource.create(), -1, 1)), (Mth.nextInt(RandomSource.create(), -1, 1)));
		}
		if (entity.getY() - world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z) > 5) {
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (-0.2), (entity.getDeltaMovement().z())));
		}
		if (entity.isInWater()) {
			//entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.DROWN)), 1);
		}
	}
}
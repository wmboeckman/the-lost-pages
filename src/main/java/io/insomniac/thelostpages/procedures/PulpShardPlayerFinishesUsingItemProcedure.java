package io.insomniac.thelostpages.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class PulpShardPlayerFinishesUsingItemProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		//entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.GENERIC), entity), Mth.nextInt(RandomSource.create(), 2, 8));
	}
}
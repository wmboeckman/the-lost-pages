package io.insomniac.thelostpages.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BookSpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
			return true;
		}
		return false;
	}
}
package io.insomniac.thelostpages.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BookRightclickedOnProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
			if (world instanceof ServerLevel _level) {
				itemstack.hurtAndBreak(4, _level, null, _stkprov -> {
				});
			}
			//entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.ON_FIRE)),
			//		(float) ((entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(Attributes.MAX_HEALTH) ? _livingEntity4.getAttribute(Attributes.MAX_HEALTH).getBaseValue() : 0) / 2));
			entity.igniteForSeconds(10);
			if (world instanceof Level _level) {
//				if (!_level.isClientSide()) {
//					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("item.firecharge.use")), SoundSource.HOSTILE, 1, 1);
//				} else {
//					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("item.firecharge.use")), SoundSource.HOSTILE, 1, 1, false);
//				}
			}
		}
	}
}
package io.insomniac.thelostpages.blocks;

import io.insomniac.thelostpages.TheLostPages;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;

public class LostBookshelf extends Block {
    public LostBookshelf(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            ServerLevel targetLevel = serverLevel.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, "the_library")));

            // Teleport back to overworld if already in target dimension
            if (serverLevel == targetLevel) {
                targetLevel = serverLevel.getServer().getLevel(ServerLevel.OVERWORLD);
            }

            if (targetLevel != null) {
                int playerX = (int) player.getX();
                int playerZ = (int) player.getZ();

                //player.playSound(SoundEvent.createFixedRangeEvent(Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, "portal_enter"), 5));

                int safeY = findSafeSpawnHeight(targetLevel, playerX, playerZ);

                ((ServerPlayer) player).teleportTo(targetLevel, playerX + 0.5, safeY, playerZ + 0.5, Relative.ALL, player.getYRot(), player.getXRot(), false);

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    private int findSafeSpawnHeight(ServerLevel level, int centerX, int centerZ) {
        // Force the target chunk to load (and generate if needed).
        // Bit-shifting by 4 (>> 4) converts block coordinates to chunk coordinates.
        level.getChunk(centerX >> 4, centerZ >> 4, ChunkStatus.FULL, true);

        // Try the exact coordinates first using Minecraft's Heightmap.
        // MOTION_BLOCKING ignores air, tall grass, etc., and returns the Y level of the air block directly above the ground.
        int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, centerX, centerZ);

        TheLostPages.LOGGER.info("First check Y-level: {}", y);

        // If greater than the minimum build height, we found valid ground
        if (y > level.getMinY()) {
            return y;
        }

        // If void spot, search nearby...
        for (int radius = 1; radius <= 16; radius++) {
            // We only want to check the perimeter of the current radius to avoid re-checking identical blocks
            for (int x = centerX - radius; x <= centerX + radius; x++) {
                for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                    // Only check if we are on the outer edge of the current radius square
                    if (Math.abs(x - centerX) == radius || Math.abs(z - centerZ) == radius) {
                        TheLostPages.LOGGER.warn("Scanning Y-level at: {}, {}", x, z);

                        // Ensure the chunk we are checking is also loaded!
                        // (A 16-block radius can cross into neighboring chunks)
                        level.getChunk(x >> 4, z >> 4, ChunkStatus.FULL, true);

                        y = level.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
                        TheLostPages.LOGGER.warn("Y-level Found: {}", y);

                        if (y > level.getMinY()) {
                            return y;
                        }
                    }
                }
            }
        }

        // Fallback: spawn at world height (I hope you have wings)
        return level.getMaxY();
    }
}

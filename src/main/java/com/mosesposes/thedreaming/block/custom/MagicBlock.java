package com.mosesposes.thedreaming.block.custom;

import com.mosesposes.thedreaming.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class MagicBlock extends Block{
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos,
                                               Player pPlayer, BlockHitResult pHitResult) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof ServerPlayer player) {
            // Start the sleeping animation
            player.startSleeping(pPos);
            player.getServer().submit(() -> {
                try {
                    // Delay for 100 ticks (5 seconds)
                    Thread.sleep(100 * 50); // Sleep in milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                teleportToEnd(player);
            });
            // Schedule the teleportation after a short delay
            teleportToEnd(player);
        } else if (pEntity instanceof ItemEntity itemEntity) {
            if (isValidItem(itemEntity.getItem())) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
            }

            if (itemEntity.getItem().getItem() == Items.RABBIT_FOOT) {
                itemEntity.setItem(new ItemStack(Items.EMERALD, itemEntity.getItem().getCount()));
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private void teleportToEnd(ServerPlayer player) {
        // Get the server instance
        ServerLevel serverLevel = player.level().getServer().getLevel(Level.END);
        if (serverLevel != null) {
            // Set the destination position in the End
            BlockPos destination = new BlockPos(100, 50, 100); // Adjust these coordinates as needed
            Vec3 teleportPos = destination.getCenter(); // Get the center of the block for the teleportation

            // Perform the teleportation
            player.teleportTo(serverLevel, teleportPos.x, teleportPos.y, teleportPos.z, player.getYRot(), player.getXRot());
        } else {
            player.sendSystemMessage(Component.translatable("message.the_dreaming.teleport_failed"));
        }
    }

    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }


    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.the_dreaming.magic_block.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}

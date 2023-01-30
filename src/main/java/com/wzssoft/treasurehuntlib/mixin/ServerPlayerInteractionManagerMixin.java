package com.wzssoft.treasurehuntlib.mixin;

import com.wzssoft.treasurehuntlib.TreasureHuntLib;
import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * added since 17.0.6
 *
 * @specialNote refine breaking
 */
@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {

    @Shadow
    @Final
    protected ServerPlayerEntity player;

    @Shadow
    protected ServerWorld world;

    @Inject(method = "processBlockBreakingAction", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockBreakingInfo(ILnet/minecraft/util/math/BlockPos;I)V"), cancellable = true)
    public void injectProcessBlockBreakingAction(BlockPos pos, PlayerActionC2SPacket.Action action, Direction direction, int worldHeight, int sequence, CallbackInfo callbackInfo) {

        if (action == PlayerActionC2SPacket.Action.START_DESTROY_BLOCK) {
            BlockState state = world.getBlockState(pos);
            ItemStack stack = player.getMainHandStack();
            BlockState blockState3 = null;

            if (MixinDataHelper.TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.containsKey(state.getBlock()) && world.getBlockState(pos.up()).isAir()) {
                if (stack.hasEnchantments()) {
                    NbtList list = stack.getEnchantments();

                    for (int i = 0; i < list.size(); i++) {
                        NbtCompound nbtCompound = list.getCompound(i);
                        if (nbtCompound.getString("id").equals(TreasureHuntLib.MODID + ":treasure")) {
                            blockState3 = MixinDataHelper.TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.get(state.getBlock()).getDefaultState();
                        }
                    }
                }
            } else {
                return;
            }

            if (blockState3 != null) {

                List<ItemStack> lootPools = Block.getDroppedStacks(state, world, pos, world.getBlockEntity(pos), player, stack);
                if (!(lootPools == null)) {
                    lootPools.forEach(itemStack -> Block.dropStack(world, pos, itemStack));
                }

                world.setBlockState(pos, blockState3, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState3));
                stack.damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                callbackInfo.cancel();
                return;
            }
            return;
        }
        return;
    }
}


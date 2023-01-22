package com.wzssoft.treasurehuntlib.mixin;

import com.wzssoft.treasurehuntlib.TreasureHuntLib;
import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * added since 17.0.1
 *
 * @reference Turf
 * @reference induction coil block
 * @reference copper block
 */

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {


    public MiningToolItemMixin() {
    }

    //当铲子左键物品时
    @Inject(method = "postMine(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true)
    public void injectPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (miner instanceof PlayerEntity player) {

            Block block = state.getBlock();
            BlockState blockState3 = null;

            if (MixinDataHelper.TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.containsKey(block) && world.getBlockState(pos.up()).isAir()) {
                if (stack.hasEnchantments()) {
                    NbtList list = stack.getEnchantments();

                    for (int i = 0; i < list.size(); i++) {
                        NbtCompound nbtCompound = list.getCompound(i);
                        if (nbtCompound.getString("id").equals(TreasureHuntLib.MODID + ":treasure")) {
                            blockState3 = MixinDataHelper.TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.get(block).getDefaultState();
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;

            }
            if (blockState3 != null) {
                world.setBlockState(pos, blockState3, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState3));
            }
        }
        return;
    }
}
package com.wzssoft.treasurehuntlib.mixin;


import com.wzssoft.treasurehuntlib.block.common.Farmland;
import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * added since 17.0.4
 */

@Mixin(CropBlock.class)
public class CropBlockMixin {

    @Inject(method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    public void injectCanPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        //判断此方块 instance of CanPlantFlower
        if (floor.getBlock() instanceof Farmland block && block.canPlantPlant((World) world, floor, pos)) {

            if (block.hasAirOnTop((World) world, pos)) {
                callbackInfoReturnable.setReturnValue(true);
                return;
            }
            boolean b = block.canPlantSurvive((World) world, floor, pos);
            callbackInfoReturnable.setReturnValue(b);
            return;
        }
        return;
    }


    @Inject(method = "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V", at = @At("HEAD"), cancellable = true)
    public void injectRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo callbackInfo) {
        CropBlock block = ((CropBlock) (Object) this);

        float f;
        int i;
        if (world.getBaseLightLevel(pos, 0) >= 9 && (i = state.get(block.getAgeProperty())) < block.getMaxAge() && random.nextInt((int) (25.0f / (f = MixinDataHelper.getAvailableMoisture(block, world, pos))) + 1) == 0) {
            world.setBlockState(pos, block.getDefaultState().with(block.getAgeProperty(), i + 1), Block.NOTIFY_LISTENERS);
        }

        callbackInfo.cancel();
        return;
    }
}
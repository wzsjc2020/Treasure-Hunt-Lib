package com.wzssoft.treasurehuntlib.mixin;

import com.wzssoft.treasurehuntlib.block.common.Farmland;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * added since 17.0.1
 *
 * @specialNote check flower can grow on block, using instance of CanPlantFlower , method canFlowerSurvive()
 */

@Mixin(PlantBlock.class)
public class PlantBlockMixin {
    //植物能否种植在此方块上
    @Inject(method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    public void injectCanPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        //判断此方块 instance of CanPlantFlower
        if (floor.getBlock() instanceof Farmland block && block.canPlantPlant((World) world, floor, pos)) {

            if(block.hasAirOnTop((World) world, pos)){
                callbackInfoReturnable.setReturnValue(true);
                return;
            }
            boolean b = block.canPlantSurvive((World) world, floor, pos);
            callbackInfoReturnable.setReturnValue(b);
            return;
        }
        return;
    }
}
package com.wzssoft.treasurehuntlib.block.common;

import com.wzssoft.treasurehuntlib.block.TreasureHuntLibBlocks;
import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import com.wzssoft.treasurehuntlib.utils.TreasureHuntLibConstant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * added since 17.0.1
 *
 * @specialNote this block is an example for demonstrate
 * @specialNote com.wzssoft.extracraft.block.common.shoveledBlock.ShoveledStoneBlock.java
 */
public class ShoveledStoneBlock extends AbstractShoveledBlock {

    public static final int MAX_MOISTURE = 5;
    public static final IntProperty MOISTURE = IntProperty.of("moisture", 0, MAX_MOISTURE);

    public ShoveledStoneBlock(Settings settings) {
        super(settings);

        this.setDefaultState(this.stateManager.getDefaultState().with(MOISTURE, 0));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        int i = state.get(MOISTURE);

        if (this.isWaterOrLavaNearBy(world, pos) || world.hasRain(pos.up())) {
            if (i < MAX_MOISTURE) {
                world.setBlockState(pos, (BlockState) state.with(MOISTURE, MAX_MOISTURE), Block.NOTIFY_LISTENERS);
            }
        } else if (i > 0) {
            world.setBlockState(pos, (BlockState) state.with(MOISTURE, i - 1), Block.NOTIFY_LISTENERS);
        } else if (this.hasAirOnTop(world, pos)) {

            //如果此方块上面没有花，且湿度为0则转换成默认方块
            this.setToOriginalBlock(world, pos);
        }
    }

    //判断周围是否有岩浆或水
    public boolean isWaterOrLavaNearBy(World world, BlockPos pos) {
        return getNearbyFluidType(world, pos) != NearbyFluidType.EMPTY;
    }

    //这个方块返回一个enum值，用于判断该方块周围5x5区域内是否有水或者岩浆
    public NearbyFluidType getNearbyFluidType(World world, BlockPos pos) {
        boolean hasWater = false;
        boolean hasLava = false;
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-2, 0, -2), pos.add(2, 1, 2))) {
            if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                hasWater = true;
            }
            if (world.getFluidState(blockPos).isIn(FluidTags.LAVA)) {
                hasLava = true;
            }
            if (hasWater && hasLava) {
                return NearbyFluidType.WATER_AND_LAVA;
            }
        }
        if (hasWater) {
            return NearbyFluidType.WATER;
        } else if (hasLava) {
            return NearbyFluidType.LAVA;
        } else {
            return NearbyFluidType.EMPTY;
        }
    }

    /**
     * 需要重写以下3个方法
     */
    //此方块上能种植植物
    @Override
    public boolean canPlantPlant(World world, BlockState floorState, BlockPos floorPos) {
        return true;
    }

    /**
     * @specialNote 自定义种子需要重写此方法，默认使用花种子
     */
    @Override
    public ArrayList<Block> getPlantSurviveList() {
        return TreasureHuntLibConstant.STONE_BLOCK_PLANT_SURVIVE_LIST;
    }

    @Override
    public ArrayList<Block> getSeedlingSurviveList() {
        return TreasureHuntLibConstant.STONE_BLOCK_SEEDLING_SURVIVE_LIST;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }
}


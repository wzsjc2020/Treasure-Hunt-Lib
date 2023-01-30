package com.wzssoft.treasurehuntlib.block.common;

import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import com.wzssoft.treasurehuntlib.utils.TreasureHuntLibConstant;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;

import java.util.ArrayList;

/**
 * added since 17.0.1
 */
public class AbstractShoveledBlock extends Block implements Farmland {

    public AbstractShoveledBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.up());
        return !blockState.getMaterial().isSolid() || blockState.getBlock() instanceof FenceGateBlock || blockState.getBlock() instanceof PistonExtensionBlock;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            this.setToOriginalBlock(world, pos);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!this.getDefaultState().canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
            return getOriginalBlockState(ctx.getWorld(), ctx.getBlockPos());
        }

        return super.getPlacementState(ctx);
    }

    public void setToOriginalBlock(World world, BlockPos pos) {
        world.setBlockState(pos, getOriginalBlockState(world, pos), NOTIFY_LISTENERS);
    }

    private BlockState getOriginalBlockState(World world, BlockPos pos) {
        return MixinDataHelper.getReversedTreasureEnchantmentEffectBlockMap().get(this).getDefaultState();
    }

    @Override
    public boolean canPlantPlant(World world, BlockState floorState, BlockPos floorPos) {
        return false;
    }

    @Override
    public boolean canPlantSurvive(World world, BlockState floorState, BlockPos floorPos) {
        BlockState state = world.getBlockState(floorPos.up());
        return getPlantSurviveList().contains(state.getBlock()) || getSeedlingSurviveList().contains(state.getBlock());
    }

    @Override
    public ArrayList<Block> getPlantSurviveList() {
        return TreasureHuntLibConstant.DEFAULT_PLANT_SURVIVE_LIST;
    }

    @Override
    public ArrayList<Block> getSeedlingSurviveList() {
        return TreasureHuntLibConstant.DEFAULT_SEEDLING_SURVIVE_LIST;
    }

    @Override
    public boolean hasAirOnTop(World world, BlockPos floorPos) {
        BlockState state = world.getBlockState(floorPos.up());
        return state.isAir();
    }

    @Override
    public int getMoisture(BlockState floorState) {
        return 0;
    }
}
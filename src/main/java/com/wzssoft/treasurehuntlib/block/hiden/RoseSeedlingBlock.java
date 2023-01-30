package com.wzssoft.treasurehuntlib.block.hiden;

import com.wzssoft.treasurehuntlib.block.common.AbstractShoveledBlock;
import com.wzssoft.treasurehuntlib.block.common.NearbyFluidType;
import com.wzssoft.treasurehuntlib.block.common.ShoveledStoneBlock;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.ArrayList;

/**
 * added since 17.0.1
 *
 * @specialNote this block is an example for demonstrate
 */
public class RoseSeedlingBlock extends PlantBlock {

    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = Properties.AGE_4;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);

    public RoseSeedlingBlock(Settings settings) {
        super(settings);
    }

    //方块模型
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
    }


    /**
     * @specialNote 需要重写以下方法，来规定此方块能够种植在什么方块上
     * 如果这边不重写此方法，则此方块默认可种植在土方快和耕地上
     */
    //是否可以种植在特定方块上面
    @Override
    protected boolean canPlantOnTop(BlockState floorState, BlockView world, BlockPos floorPos) {
        World world1 = (World) world;
        if (floorState.getBlock() instanceof AbstractShoveledBlock block && block.canPlantPlant(world1, floorState, floorPos)) {
            if (block.hasAirOnTop(world1, floorPos)) {
                return true;
            }
            return block.canPlantSurvive(world1, floorState, floorPos);
        }
        return false;
    }

    //随机刻
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i;
            if ((i = this.getAge(state)) < this.getMaxAge()) {
                world.setBlockState(pos, state.with(AGE, i + 1), Block.NOTIFY_LISTENERS);
                return;
            }
            mature(world, state, pos);
        }
    }

    protected int getAge(BlockState state) {
        return state.get(AGE);
    }

    public int getMaxAge() {
        return FlowerSeedlingBlock.MAX_AGE;
    }

    //当作物成熟时
    private void mature(World world, BlockState state, BlockPos pos) {
        BlockPos floorPos = pos.down();
        BlockState floorState = world.getBlockState(floorPos);

        if (floorState.getBlock() instanceof ShoveledStoneBlock block && block.canPlantPlant(world, floorState, floorPos)) {
            ArrayList<Block> arrayList = block.getPlantSurviveList();
            if (!arrayList.isEmpty()) {

                //如果周围没有岩浆，则100%生成玫瑰花
                NearbyFluidType type = block.getNearbyFluidType(world, pos.down());
                if (!(type == NearbyFluidType.LAVA)) {
                    world.setBlockState(pos, Blocks.POPPY.getDefaultState(), NOTIFY_LISTENERS);
                    return;
                }

                boolean b = world.random.nextInt(10) == 0;
                if (b) {
                    world.setBlockState(pos, Blocks.WITHER_ROSE.getDefaultState(), NOTIFY_LISTENERS);
                } else {
                    world.setBlockState(pos, Blocks.POPPY.getDefaultState(), NOTIFY_LISTENERS);
                }
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}

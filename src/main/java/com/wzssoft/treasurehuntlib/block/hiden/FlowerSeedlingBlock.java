package com.wzssoft.treasurehuntlib.block.hiden;

import com.wzssoft.treasurehuntlib.block.common.AbstractShoveledBlock;
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

import java.util.ArrayList;

/**
 * added since 17.0.6
 *
 * @reference game forager
 * @reference stem block
 * @reference corpBlock
 * @reference iron temporary scaffolding block
 * @specialNote in vanilla MC, you can get flower by using bone meals. right now you can actually grow a living flower
 * @specialNote you can get random flower if the floor block instance of AbstractShoveledBlock
 * @specialNote the flower will generate at the beginning
 */
public class FlowerSeedlingBlock extends PlantBlock {

    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = Properties.AGE_4;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);

    /**
     * @specialNote 像原版花一样可以在方块上随机的位移，而不是居中放置
     */
    //*********************************************************************************方块模型的改变
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
    }


    public FlowerSeedlingBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    //是否可以种植在特定方块上面
    @Override
    protected boolean canPlantOnTop(BlockState floorState, BlockView world, BlockPos floorPos) {

        World world1 = (World) world;
        if (((world1.getBaseLightLevel(floorPos, 0) >= 8 || world1.isSkyVisible(floorPos)) && floorState.getBlock() instanceof AbstractShoveledBlock block && block.canPlantPlant(world1, floorState, floorPos))) {
            if (block.hasAirOnTop(world1, floorPos)) {
                return true;
            }
            return block.canPlantSurvive(world1, floorState, floorPos);
        }
        return false;
    }

    //********************************************************************************随机刻
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

    //**********************************************************************************工具

    protected int getAge(BlockState state) {
        return state.get(AGE);
    }

    public int getMaxAge() {
        return FlowerSeedlingBlock.MAX_AGE;
    }

    private void mature(World world, BlockState state, BlockPos pos) {
        BlockPos floorPos = pos.down();
        BlockState floorState = world.getBlockState(floorPos);

        if (floorState.getBlock() instanceof AbstractShoveledBlock block && block.canPlantSurvive(world, floorState, floorPos)) {
            ArrayList<Block> arrayList = block.getPlantSurviveList();
            if (!arrayList.isEmpty()) {
                Block flowerBlock = arrayList.get(world.random.nextInt(arrayList.size()));
                world.setBlockState(pos, flowerBlock.getDefaultState(), NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}




package com.wzssoft.treasurehuntlib.block.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * added since 17.0.1
 */
public interface Farmland {

    boolean canPlantPlant(World world, BlockState floorState, BlockPos floorPos);

    boolean canPlantSurvive(World world, BlockState floorState, BlockPos floorPos);

    ArrayList<Block> getPlantSurviveList();

    ArrayList<Block> getSeedlingSurviveList();

    boolean hasAirOnTop(World world, BlockPos floorPos);
}

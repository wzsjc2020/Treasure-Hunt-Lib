package com.wzssoft.treasurehuntlib.utils;

import com.wzssoft.treasurehuntlib.block.common.Farmland;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * added since 17.0.1
 */
public class MixinDataHelper {

    public static Map<Block, Block> REVERSED_TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP;

    public static Map<Block, Block> TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP = new HashMap<Block, Block>() {
    };

    public static PlayerActionC2SPacket.Action action = PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK;

    //https://stackoverflow.com/questions/20412354/reverse-hashmap-keys-and-values-in-java
    public static Map<Block, Block> getReversedTreasureEnchantmentEffectBlockMap() {
        if (REVERSED_TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP == null) {
            REVERSED_TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP = TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        }
        return REVERSED_TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP;
    }


    public static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
        boolean bl2;
        float f = 1.0f;
        BlockPos blockPos = pos.down();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float g = 0.0f;
                BlockState blockState = world.getBlockState(blockPos.add(i, 0, j));
                Block floorBlock = blockState.getBlock();

                if (floorBlock instanceof FarmlandBlock) {
                    g = 1.0f;
                    if (blockState.get(FarmlandBlock.MOISTURE) > 0) {
                        g = 3.0f;
                    }
                } else if (floorBlock instanceof Farmland farmland) {
                    g = 1.0f;
                    if (farmland.getMoisture(blockState) > 0) {
                        g = 3.0f;
                    }
                }
                if (i != 0 || j != 0) {
                    g /= 4.0f;
                }
                f += g;
            }
        }
        BlockPos blockPos2 = pos.north();
        BlockPos blockPos3 = pos.south();
        BlockPos blockPos4 = pos.west();
        BlockPos blockPos5 = pos.east();
        boolean bl = world.getBlockState(blockPos4).isOf(block) || world.getBlockState(blockPos5).isOf(block);
        boolean bl3 = bl2 = world.getBlockState(blockPos2).isOf(block) || world.getBlockState(blockPos3).isOf(block);
        if (bl && bl2) {
            f /= 2.0f;
        } else {
            boolean bl32;
            boolean bl4 = bl32 = world.getBlockState(blockPos4.north()).isOf(block) || world.getBlockState(blockPos5.north()).isOf(block) || world.getBlockState(blockPos5.south()).isOf(block) || world.getBlockState(blockPos4.south()).isOf(block);
            if (bl32) {
                f /= 2.0f;
            }
        }
        return f;
    }


    public static void initMixinDataHelper() {
    }
}

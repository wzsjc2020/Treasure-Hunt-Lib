package com.wzssoft.treasurehuntlib.utils;

import net.minecraft.block.Block;

import java.util.*;

/**
 * added since 17.0.1
 */
public class MixinDataHelper {


    public static Map<Block, Block> TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP = new HashMap<Block, Block>() {
    };
    public static Map<Block, Block> REVERSED_TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP = new HashMap<Block, Block>() {
    };

    public static void initMixinDataHelper() {
    }
}

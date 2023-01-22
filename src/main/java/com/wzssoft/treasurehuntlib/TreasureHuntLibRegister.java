package com.wzssoft.treasurehuntlib;

import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import net.minecraft.block.Block;

/**
 * added since 17.0.1
 */
public class TreasureHuntLibRegister {

    /**
     * added since 17.0.1
     */
    public static void registerTreasureEnchantmentEffectBlock(Block original, Block changed) {
        MixinDataHelper.TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.put(original, changed);
        MixinDataHelper.REVERSED_TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.put(changed, original);
    }
}

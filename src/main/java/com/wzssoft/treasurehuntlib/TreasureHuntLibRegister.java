package com.wzssoft.treasurehuntlib;

import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import net.minecraft.block.Block;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;

/**
 * added since 17.0.1
 * modified at 17.0.6
 */
public class TreasureHuntLibRegister {

    /**
     * added since 17.0.1
     */
    public static void registerTreasureEnchantmentEffectBlock(Block original, Block changed) {
        MixinDataHelper.TREASURE_ENCHANTMENT_EFFECT_BLOCK_MAP.put(original, changed);
    }
}
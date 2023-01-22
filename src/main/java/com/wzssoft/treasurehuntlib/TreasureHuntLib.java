package com.wzssoft.treasurehuntlib;

import com.wzssoft.treasurehuntlib.block.TreasureHuntLibBlocks;
import com.wzssoft.treasurehuntlib.enchantment.TreasureHuntLibEnchantments;
import com.wzssoft.treasurehuntlib.item.TreasureHuntLibBlockItems;
import com.wzssoft.treasurehuntlib.utils.MixinDataHelper;
import com.wzssoft.treasurehuntlib.utils.TreasureHuntLibConstant;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//treasurehunt项目创建于2023年1月20日，用来增加有关挖掘和种地的更多玩法
public class TreasureHuntLib implements ModInitializer {
    public static final String MODID = "treasurehuntlib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {

        TreasureHuntLibBlocks.registerModBlock();
        TreasureHuntLibBlockItems.registerModBlockItems();

        /**
         * enable this register if you want to modified loot table in vanilla
         */
        //TreasureHuntLibLootTableModifier.registerModModifyLootTable();

        TreasureHuntLibEnchantments.registerModEnchantment();
        MixinDataHelper.initMixinDataHelper();

        /**
         * register treasure enchantment effect block(s)
         */
        TreasureHuntLibRegister.registerTreasureEnchantmentEffectBlock(TreasureHuntLibBlocks.EXAMPLE_STONE_BLOCK,TreasureHuntLibBlocks.SHOVELED_STONE_BLOCK);

        /**
         * init constant
         */
        TreasureHuntLibConstant.initConstant();

    }
}

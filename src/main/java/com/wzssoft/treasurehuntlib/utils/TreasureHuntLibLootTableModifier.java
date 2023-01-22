package com.wzssoft.treasurehuntlib.utils;

import com.wzssoft.treasurehuntlib.enchantment.TreasureHuntLibEnchantments;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

/**
 * added since 17.0.1
 *
 * @specialNote see all conditions at LootCondition.java
 * @specialNote this is only an example, do not copy this loot table to your own mod
 */
public class TreasureHuntLibLootTableModifier {

    public static final Identifier STONE_BLOCK_ID = new Identifier("minecraft", "blocks/stone");


    public static void registerModModifyLootTable() {

        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {

            //当需要替换掉的 id 满足时
            if (STONE_BLOCK_ID.equals(id)) {

                //创建一个loot table
                LootTable.Builder table = LootTable.builder();

                //第一个条件：玩家手上的物品具有‘宝藏’附魔
                LootCondition.Builder match_item = MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(TreasureHuntLibEnchantments.TREASURE, NumberRange.IntRange.exactly(1))));

                //第二个条件：爆炸掉落（你也可以理解成一般的物品挖掘掉落，同一个方法）
                LootCondition.Builder common = SurvivesExplosionLootCondition.builder();

                //创建一个loot pool, loot pool 就是所有掉落物的一个集合
                LootPool.Builder neo_builder = LootPool.builder()

                        //只运行一次，也就是不受时运的影响
                        .rolls(ConstantLootNumberProvider.create(1.0f))

                        //这边就是设置一个多选一掉落物，当第一个条件：玩家手上的物品具有‘宝藏’附魔满足时，100%机率，掉落1-2个钻石
                        //当第二个条件：爆炸掉落满足时，100%机率，掉落1个原石
                        .with(AlternativeEntry.builder(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).conditionally(match_item), ItemEntry.builder(Items.COBBLESTONE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).conditionally(common)));

                //返回新的loot pool
                table.pool(neo_builder);
                return table.build();
            }


            return original;
        });
    }
}
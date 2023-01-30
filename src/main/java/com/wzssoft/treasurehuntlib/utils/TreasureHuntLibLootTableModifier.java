package com.wzssoft.treasurehuntlib.utils;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

/**
 * added since 17.0.1
 *
 * @specialNote see all conditions at LootCondition.java
 * @specialNote this is only an example, do not copy this loot table to your own mod
 */
public class TreasureHuntLibLootTableModifier {

    public static final Identifier STONE_BLOCK_ID = new Identifier("minecraft", "blocks/stone");


    //this loot table could be very complicated, we suggest you to use loot table generator
    public static void registerModModifyLootTable() {

        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {

            if (STONE_BLOCK_ID.equals(id)) {

                //replace vanilla loot table
                LootTable.Builder table = LootTable.builder();

                //do something here

                return table.build();
            }

            return original;
        });
    }
}
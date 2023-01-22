package com.wzssoft.treasurehuntlib.item;

import com.wzssoft.treasurehuntlib.TreasureHuntLib;
import com.wzssoft.treasurehuntlib.block.TreasureHuntLibBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class TreasureHuntLibBlockItems {


    public static final Item FLOWER_SEEDS_BLOCK_ITEM = registerBlockItem("flower_seeds",
            new AliasedBlockItem(TreasureHuntLibBlocks.FLOWER_SEEDLING_BLOCK, new FabricItemSettings().group(TreasureHuntLibItemGroup.ExtraCraft).rarity(Rarity.EPIC)));

    public static final Item ROSE_SEEDS_BLOCK_ITEM = registerBlockItem("rose_seeds",
            new AliasedBlockItem(TreasureHuntLibBlocks.ROSE_SEEDLING_BLOCK, new FabricItemSettings().group(TreasureHuntLibItemGroup.ExtraCraft).rarity(Rarity.EPIC)));

    public static final Item SHOVELED_STONE_BLOCK_ITEM = registerBlockItem("shoveled_stone_block",
            new AliasedBlockItem(TreasureHuntLibBlocks.SHOVELED_STONE_BLOCK, new FabricItemSettings().group(TreasureHuntLibItemGroup.ExtraCraft).rarity(Rarity.EPIC)));

    public static final Item EXAMPLE_STONE_BLOCK_ITEM = registerBlockItem("example_stone_block",
            new AliasedBlockItem(TreasureHuntLibBlocks.EXAMPLE_STONE_BLOCK, new FabricItemSettings().group(TreasureHuntLibItemGroup.ExtraCraft).rarity(Rarity.EPIC)));


    //因为方块和需要同时注册一个item，以便能在物品栏中找到该方块
    public static Item registerBlockItem(String name, BlockItem blockitem) {
        return Registry.register(Registry.ITEM, new Identifier(TreasureHuntLib.MODID, name), blockitem);
    }


    public static void registerModBlockItems() {
        //当执行这一行命令时，会自动执行一遍空参构造
    }
}

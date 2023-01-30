package com.wzssoft.treasurehuntlib.item;

import com.wzssoft.treasurehuntlib.TreasureHuntLib;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class TreasureHuntLibItemGroup {

    public static final ItemGroup ExtraCraft = FabricItemGroupBuilder.build(
            new Identifier(TreasureHuntLib.MODID, "tab"), () -> new ItemStack(TreasureHuntLibBlockItems.FLOWER_SEEDS_BLOCK_ITEM));
}

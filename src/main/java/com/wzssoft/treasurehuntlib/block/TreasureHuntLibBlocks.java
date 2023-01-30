package com.wzssoft.treasurehuntlib.block;

import com.wzssoft.treasurehuntlib.TreasureHuntLib;
import com.wzssoft.treasurehuntlib.block.common.ExampleStoneBlock;
import com.wzssoft.treasurehuntlib.block.common.ShoveledStoneBlock;
import com.wzssoft.treasurehuntlib.block.hiden.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TreasureHuntLibBlocks {

    public static final Block ROSE_SEEDLING_BLOCK = registerBlock("rose_seedling",
            new RoseSeedlingBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().ticksRandomly().noCollision().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ).nonOpaque()));

    public static final Block FLOWER_SEEDLING_BLOCK = registerBlock("flower_seedling",
            new FlowerSeedlingBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().ticksRandomly().noCollision().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ).nonOpaque()));

    public static final Block SHOVELED_STONE_BLOCK = registerBlock("shoveled_stone_block",
            new ShoveledStoneBlock(FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(1.5f, 6.0f)));

    public static final Block EXAMPLE_STONE_BLOCK = registerBlock("example_stone_block",
            new ExampleStoneBlock(FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(1.5f, 6.0f)));


    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(TreasureHuntLib.MODID, name), block);
    }

    public static void registerModBlock() {
        //当执行这一行命令时，会自动执行一遍空参构造
    }
}



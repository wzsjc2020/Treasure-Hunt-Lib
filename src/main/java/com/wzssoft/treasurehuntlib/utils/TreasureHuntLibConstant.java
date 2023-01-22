package com.wzssoft.treasurehuntlib.utils;

import com.wzssoft.treasurehuntlib.block.TreasureHuntLibBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.ArrayList;

/**
 * added since 17.0.1
 * @specialNote make sure this method called at the end of Main class
 */
public class TreasureHuntLibConstant {

    public static ArrayList<Block> STONE_BLOCK_PLANT_SURVIVE_LIST;
    public static ArrayList<Block> STONE_BLOCK_SEEDLING_SURVIVE_LIST ;
    public static ArrayList<Block> DEFAULT_PLANT_SURVIVE_LIST ;
    public static ArrayList<Block> DEFAULT_SEEDLING_SURVIVE_LIST ;


    public static void initConstant(){
        STONE_BLOCK_PLANT_SURVIVE_LIST = new ArrayList<>() {{
            add(Blocks.POPPY);
            add(Blocks.WITHER_ROSE);
        }};


        STONE_BLOCK_SEEDLING_SURVIVE_LIST = new ArrayList<>() {{
            add(TreasureHuntLibBlocks.ROSE_SEEDLING_BLOCK);
        }};


        DEFAULT_PLANT_SURVIVE_LIST = new ArrayList<Block>() {{
            add(Blocks.DANDELION);               //蒲公英
            add(Blocks.POPPY);                   //罂粟
            add(Blocks.BLUE_ORCHID);             //兰花
            add(Blocks.ALLIUM);                  //绒球葱
            add(Blocks.AZURE_BLUET);             //蓝花美耳草
            add(Blocks.RED_TULIP);               //红色郁金香
            add(Blocks.ORANGE_TULIP);            //橙色郁金香
            add(Blocks.WHITE_TULIP);             //白色郁金香
            add(Blocks.PINK_TULIP);              //粉色郁金香
            add(Blocks.OXEYE_DAISY);             //茜草花
            add(Blocks.CORNFLOWER);              //矢车菊
            add(Blocks.LILY_OF_THE_VALLEY);      //铃兰花
        }};

        DEFAULT_SEEDLING_SURVIVE_LIST = new ArrayList<>() {{
            add(TreasureHuntLibBlocks.FLOWER_SEEDLING_BLOCK);
        }};

    }
}


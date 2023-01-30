package com.wzssoft.treasurehuntlib;

import com.wzssoft.treasurehuntlib.block.TreasureHuntLibBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TreasureHuntLibClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(TreasureHuntLibBlocks.SHOVELED_STONE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TreasureHuntLibBlocks.FLOWER_SEEDLING_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TreasureHuntLibBlocks.ROSE_SEEDLING_BLOCK, RenderLayer.getCutout());
    }
}

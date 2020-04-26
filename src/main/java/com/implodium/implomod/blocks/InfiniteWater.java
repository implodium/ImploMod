package com.implodium.implomod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfiniteWater extends Block {
    public static final String NAME = "infinite_water";
    private static final String UNLOCALIZED_NAME = "infinite_water";
    private static final String REGISTRY_NAME = "infinite_water";

    public InfiniteWater() {
        super(Material.GLASS);

        this.setCreativeTab(CreativeTabs.BREWING);
        this.setUnlocalizedName(UNLOCALIZED_NAME);
        this.setRegistryName(REGISTRY_NAME);
    }

    @SideOnly(value = Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}

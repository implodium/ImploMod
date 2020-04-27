package com.implodium.implomod.blocks;

import com.implodium.implomod.ImploMod;
import com.implodium.implomod.init.ModBlocks;
import com.implodium.implomod.init.ModItems;
import com.implodium.implomod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.Explosion;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockBase extends Block implements IHasModel
{
    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BREWING);


        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        ImploMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
            return Item.getItemFromBlock(this);
    }

    public boolean canDropFromExplosion(Explosion explosionIn) {
        return true;
    }
}

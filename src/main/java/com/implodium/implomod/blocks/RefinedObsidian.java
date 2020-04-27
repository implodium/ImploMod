package com.implodium.implomod.blocks;

import com.implodium.implomod.init.ModBlocks;
import com.implodium.implomod.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class RefinedObsidian extends BlockBase {

    public RefinedObsidian(String name, Material material) {
        super(name, material);

        this.setLightLevel(3);
        this.setLightOpacity(5);
        this.setHarvestLevel("pickaxe", 3);
        this.setHardness(7.0f);
    }

    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        super.onBlockClicked(worldIn, pos, playerIn);

        Item item = playerIn.inventory.getCurrentItem().getItem();
        if (item == Items.DIAMOND) {
            playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);
            playerIn.dropItem(new ItemStack(ModItems.IMPLO_SHARD, 4), false);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
            return Item.getItemFromBlock(ModBlocks.REFINED_OBSIDIAN);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return this == Blocks.EMERALD_BLOCK || this == Blocks.GOLD_BLOCK || this == Blocks.DIAMOND_BLOCK || this == Blocks.IRON_BLOCK || this == ModBlocks.REFINED_OBSIDIAN;
    }
}

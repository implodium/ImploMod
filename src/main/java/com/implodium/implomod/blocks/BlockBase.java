package com.implodium.implomod.blocks;

import com.implodium.implomod.ImploMod;
import com.implodium.implomod.init.ModBlocks;
import com.implodium.implomod.init.ModItems;
import com.implodium.implomod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockBase extends Block implements IHasModel
{
    public float slipperiness;
    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ImploMod.implotab);



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

    //Slipperiness Normal: 0.6F
    @Override
    public float getSlipperiness(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity entity) {
        return 0.6F;
    }
    @Override
    public void setDefaultSlipperiness(float slipperiness) {
        this.slipperiness = slipperiness;

    }

    //Plant can grow on true/false
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        return false;
    }

}

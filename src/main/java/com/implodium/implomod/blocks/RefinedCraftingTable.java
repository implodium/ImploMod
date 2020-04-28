package com.implodium.implomod.blocks;

import com.implodium.implomod.init.ModBlocks;
import com.implodium.implomod.init.ModItems;
import javafx.scene.chart.Axis;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class RefinedCraftingTable extends BlockBase {

    public static final AxisAlignedBB REFINED_CRAFTING_TABLE_BB = new AxisAlignedBB(1.0D, 0.0D, 1.0D, 1.0D, 1.0625D, 1.0D);

    public RefinedCraftingTable(String name, Material material) {
        super(name, material);

        this.setLightOpacity(5);
        this.setHarvestLevel("axe", 3);
        this.setHardness(3.0f);
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
            return Item.getItemFromBlock(ModBlocks.REFINED_CRAFTING_TABLE);
    }

    @SideOnly(value = Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return REFINED_CRAFTING_TABLE_BB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return REFINED_CRAFTING_TABLE_BB;
    }
}

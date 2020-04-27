package com.implodium.implomod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class InfiniteWaterFull extends BlockBase {

    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 1);

    public InfiniteWaterFull(String name, Material material) {
        super(name, material);

        this.setLightOpacity(5);
        this.setHarvestLevel("axe", 1);
        this.setHardness(1.0f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0));
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

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 1;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (itemstack.isEmpty()) {
            return true;
        } else {
            int i = (Integer)state.getValue(LEVEL);
            Item item = itemstack.getItem();
            if (item == Items.BUCKET) {
                if (i == 0 && !worldIn.isRemote) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
                        } else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
                            playerIn.dropItem(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    }

                    playerIn.addStat(StatList.CAULDRON_USED);
                    this.setWaterLevel(worldIn, pos, state, 0);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }return true;
            } else {
                ItemStack itemstack1;
                if (item == Items.GLASS_BOTTLE) {
                    if (i == 0 && !worldIn.isRemote) {
                        if (!playerIn.capabilities.isCreativeMode) {
                            itemstack1 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER);
                            playerIn.addStat(StatList.CAULDRON_USED);
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                playerIn.setHeldItem(hand, itemstack1);
                            } else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
                                playerIn.dropItem(itemstack1, false);
                            } else if (playerIn instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                            }
                        }

                        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        this.setWaterLevel(worldIn, pos, state, 0);
                    } return true;
                } else {
                    return false;
                }
            }

        }
    }

    public void setWaterLevel(World worldIn, BlockPos pos, IBlockState state, int level) {
        worldIn.setBlockState(pos, state.withProperty(LEVEL, MathHelper.clamp(level, 0, 3)), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return 2;
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(LEVEL, meta);
    }

    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(LEVEL);
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{LEVEL});
    }

}
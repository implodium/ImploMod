package com.implodium.implomod.blocks;

import com.implodium.implomod.ImploMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class InfiniteWater extends Block {
    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 2);
    public static final String NAME = "infinite_water";
    private static final String UNLOCALIZED_NAME = "infinite_water";
    private static final String REGISTRY_NAME = "infinite_water";

    public InfiniteWater() {
        super(Material.GLASS);

        this.setCreativeTab(CreativeTabs.BREWING);
        this.setUnlocalizedName(UNLOCALIZED_NAME);
        this.setRegistryName(REGISTRY_NAME);
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
            if (item == Items.WATER_BUCKET) {
                if (i < 2 && !worldIn.isRemote) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                    }

                    playerIn.addStat(StatList.CAULDRON_FILLED);
                    this.setWaterLevel(worldIn, pos, state, i + 1);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return true;
            } else if (item == Items.BUCKET) {
                if (i == 1 && !worldIn.isRemote) {
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
                } else if (i == 2 && !worldIn.isRemote) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
                        } else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
                            playerIn.dropItem(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    }

                    playerIn.addStat(StatList.CAULDRON_USED);
                    this.setWaterLevel(worldIn, pos, state, 2);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }return true;
            } else {
                ItemStack itemstack1;
                if (item == Items.GLASS_BOTTLE) {
                    if (i == 2 && !worldIn.isRemote) {
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
                        this.setWaterLevel(worldIn, pos, state, 2);
                    } else if (i == 1 && !worldIn.isRemote) {
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
                        this.setWaterLevel(worldIn, pos, state, i - 1);
                    }

                    return true;
                } else if (item == Items.POTIONITEM && PotionUtils.getPotionFromItem(itemstack) == PotionTypes.WATER) {
                    if (i < 2 && !worldIn.isRemote) {
                        if (!playerIn.capabilities.isCreativeMode) {
                            itemstack1 = new ItemStack(Items.GLASS_BOTTLE);
                            playerIn.addStat(StatList.CAULDRON_USED);
                            playerIn.setHeldItem(hand, itemstack1);
                            if (playerIn instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                            }
                        }

                        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        this.setWaterLevel(worldIn, pos, state, i + 1);
                    }
                    return true;
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
        return (Integer)blockState.getValue(LEVEL);
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
package com.implodium.implomod.init;

import com.implodium.implomod.blocks.*;
import com.implodium.implomod.blocks.MachineFrame;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.List;
import java.util.ArrayList;

public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block INFINITE_WATER = new InfiniteWater("infinite_water", Material.GLASS);
    public static final Block INFINITE_WATER_FULL = new InfiniteWaterFull("infinite_water_full", Material.GLASS);
    public static final Block REFINED_OBSIDIAN = new RefinedObsidian("refined_obsidian", Material.IRON);
    public static final Block REFINED_CRAFTING_TABLE = new RefinedCraftingTable("refined_crafting_table", Material.WOOD);
    public static final Block MACHINE_FRAME = new MachineFrame("machine_frame", Material.IRON);

}

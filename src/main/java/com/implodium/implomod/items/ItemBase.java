package com.implodium.implomod.items;

import com.implodium.implomod.ImploMod;
import com.implodium.implomod.init.ModItems;
import com.implodium.implomod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BREWING);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        ImploMod.proxy.registerItemRenderer(this, 0, "inventory");
    }
}

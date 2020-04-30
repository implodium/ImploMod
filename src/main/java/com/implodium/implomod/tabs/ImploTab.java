package com.implodium.implomod.tabs;

import com.implodium.implomod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ImploTab extends CreativeTabs
{
    public ImploTab(String label) {super("implotab");
    //this.setBackgroundImageName("implotab.png");
    }
    public ItemStack getTabIconItem() {return new ItemStack(ModItems.IMPLO_SHARD);}
}

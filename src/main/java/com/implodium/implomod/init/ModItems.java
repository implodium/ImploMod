package com.implodium.implomod.init;

import com.implodium.implomod.items.ImploShard;
import com.implodium.implomod.items.ItemBase;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item IMPLO_SHARD = new ImploShard("implo_shard");
}

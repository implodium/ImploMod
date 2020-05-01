package com.implodium.implomod.init;

import com.implodium.implomod.items.ImploShard;
import com.implodium.implomod.items.RFTransmitter;
import com.implodium.implomod.items.RefinedGold;
import com.implodium.implomod.items.RefinedIron;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item IMPLO_SHARD = new ImploShard("implo_shard");
    public static final Item REFINED_GOLD = new RefinedGold("refined_gold_ingot");
    public static final Item REFINED_IRON = new RefinedIron("refined_iron_ingot");
    public static final Item RF_TRANSMITTER = new RFTransmitter("rf_transmitter");
}

package com.implodium.implomod;

import com.implodium.implomod.blocks.InfiniteWater;
import com.implodium.implomod.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashMap;
import java.util.Objects;

@Mod(modid = ImploConstants.MODID, name = ImploConstants.NAME, version = ImploConstants.VERSION)
public class ImploMod {
    public static HashMap<String, Block> blocks = new HashMap<>();
    public static HashMap<String, Item> items = new HashMap<>();

    @Mod.Instance
    private static  ImploMod instance;

    public static ImploMod getInstance() {
        return instance;
    }

    @SidedProxy(serverSide = "com.implodium.implomod.proxy.CommonProxy", clientSide = "com.implodium.implomod.proxy.ClientProxy")
    private static CommonProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preinit(event);
        preInitBlock(new InfiniteWater());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postinit(event);
    }

    private void preInitBlock(Block block) {
        blocks.put("infinite_water", block);
        ForgeRegistries.BLOCKS.register(blocks.get("infinite_water"));

        ItemBlock itemBlock = new ItemBlock(block);
        items.put("infinite_water", itemBlock);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        ForgeRegistries.ITEMS.register(itemBlock);

        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(
                ImploConstants.MODID + ":" + block.getRegistryName(),
                "normal"
        );

        ModelLoader.setCustomModelResourceLocation(itemBlock, 0, modelResourceLocation);
    }
}

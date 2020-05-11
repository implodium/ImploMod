package com.implodium.implomod;

import com.implodium.implomod.blocks.InfiniteWater;
import com.implodium.implomod.proxy.CommonProxy;
import com.implodium.implomod.tabs.ImploTab;
import com.implodium.implomod.util.ImploConstants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
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
    public static final CreativeTabs implotab = new ImploTab("implotab");
    public static HashMap<String, Block> blocks = new HashMap<>();
    public static HashMap<String, Item> items = new HashMap<>();

    @Mod.Instance
    private static  ImploMod instance;

    public static ImploMod getInstance() {
        return instance;
    }

    @SidedProxy(serverSide = "com.implodium.implomod.proxy.CommonProxy", clientSide = "com.implodium.implomod.proxy.ClientProxy")
    public static CommonProxy proxy;


    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preinit(event);
        /*    ----> slippymod code
        for(int i = 1; i < 256; i++) {
            Block.getBlockById(i).setDefaultSlipperiness(1.07F);
        }*/
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postinit(event);

    }
}

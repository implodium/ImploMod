package com.implodium.implomod;

import com.implodium.implomod.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ImploConstants.MODID, name = ImploConstants.NAME, version = ImploConstants.VERSION)
public class ImploMod {

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

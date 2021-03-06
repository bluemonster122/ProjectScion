package com.github.projectscion.common.features;

import com.github.projectscion.common.util.IProvideEvent;
import com.github.projectscion.common.util.RegistrationHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Arrays;

public class Feature {

    public void registerConfigurations(File configFile) {
        // NOOP
    }

    public void preInit() {
        // NOOP
    }

    public void init() {
        // NOOP
    }

    public void postInit() {
        // NOOP
    }

    public void registerFeature(Object n1) {
        registerFeature(n1, null);
    }

    public void registerFeature(Object n1, @Nullable Class<? extends TileEntity> clazz) {
        if (n1 instanceof Block) {
            Block block = (Block) n1;
            Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
            GameRegistry.register(block);
            GameRegistry.register(item);
            if (clazz != null) {
                GameRegistry.registerTileEntity(clazz, block.getRegistryName().getResourcePath());
            }
            RegistrationHelper.registerRender(item);
        }
        if (n1 instanceof Item) {
            Item item = (Item) n1;
            GameRegistry.register(item);
            RegistrationHelper.registerRender(item);
        }
        if (Arrays.asList(n1.getClass().getInterfaces()).contains(IProvideEvent.class)) {
            MinecraftForge.EVENT_BUS.register(n1);
        }
    }
}

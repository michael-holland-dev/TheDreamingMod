package com.mosesposes.thedreaming.item;

import com.mosesposes.thedreaming.TheDreaming;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheDreaming.MOD_ID);

    public static RegistryObject<Item> ALEXANDRITE =
            ITEMS.register("alexandrite", () -> new Item(new Item.Properties()));

    public static RegistryObject<Item> RAW_ALEXANDRITE =
            ITEMS.register("raw_alexandrite", () -> new Item(new Item.Properties()));

    public static void register(IEventBus event) {
        ITEMS.register(event);
    }
}

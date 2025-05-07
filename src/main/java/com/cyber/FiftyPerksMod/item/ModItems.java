package com.cyber.FiftyPerksMod.item;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FiftyPerksMod.MOD_ID);

    public static final DeferredItem<Item> GALLIUM_INGOT = ITEMS.register("gallium_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_GALLIUM = ITEMS.register("raw_gallium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PERK_HOLDER = ITEMS.register("perk_holder", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

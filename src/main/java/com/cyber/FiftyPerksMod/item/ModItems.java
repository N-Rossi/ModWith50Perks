package com.cyber.FiftyPerksMod.item;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.item.custom.PerkHolderItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FiftyPerksMod.MOD_ID);

    public static final DeferredItem<Item> ELEMENT115_INGOT = ITEMS.register("element115_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ELEMENT115 = ITEMS.register("raw_element115", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PERK_HOLDER = ITEMS.register("perk_holder", () -> new PerkHolderItem(new Item.Properties()));

    /** Perks */
    public static final DeferredItem<Item> JUGGERNOG_PERK = ITEMS.register("juggernog_perk", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPEEDCOLA_PERK = ITEMS.register("speedcola_perk", () -> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

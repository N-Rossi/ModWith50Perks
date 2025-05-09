package com.cyber.FiftyPerksMod.item;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.item.custom.PerkHolderItem;
import com.cyber.FiftyPerksMod.item.custom.PerkItem;
import com.cyber.FiftyPerksMod.item.custom.Tier2PerkHolderItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FiftyPerksMod.MOD_ID);

    public static final DeferredItem<Item> ELEMENT115_INGOT = ITEMS.register("element115_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ELEMENT115 = ITEMS.register("raw_element115", () -> new Item(new Item.Properties()));

    /** Crystals */
    public static final DeferredItem<Item> ELEMENT115_CRYSTAL = ITEMS.register("element115_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BASIC_PERK_CRYSTAL = ITEMS.register("basic_perk_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JUGGERNOG_CRYSTAL = ITEMS.register("juggernog_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPEEDCOLA_CRYSTAL = ITEMS.register("speedcola_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STAMINUP_CRYSTAL = ITEMS.register("staminup_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VICTORIOUSTORTOISE_CRYSTAL = ITEMS.register("victorioustortoise_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DOUBLETAP_CRYSTAL = ITEMS.register("doubletap_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PHDFLOPPER_CRYSTAL = ITEMS.register("phdflopper_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> QUICKREVIVE_CRYSTAL = ITEMS.register("quickrevive_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WHOSWHO_CRYSTAL = ITEMS.register("whoswho_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WINTERSWAIL_CRYSTAL = ITEMS.register("winterswail_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DEATHPERCEPTION_CRYSTAL = ITEMS.register("deathperception_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DEADSHOT_CRYSTAL = ITEMS.register("deadshot_crystal", () -> new Item(new Item.Properties()));

    /** Perk Holders */
    public static final DeferredItem<Item> PERK_HOLDER = ITEMS.register("perk_holder", () -> new PerkHolderItem(new Item.Properties()));
    public static final DeferredItem<Item> PERK_HOLDER_TIER2 = ITEMS.register("perk_holder_tier2", () -> new Tier2PerkHolderItem(new Item.Properties()));

    /** Perks */
    public static final DeferredItem<Item> JUGGERNOG_PERK = ITEMS.register("juggernog_perk", () -> new PerkItem(new Item.Properties(), "Extra Health"));
    public static final DeferredItem<Item> SPEEDCOLA_PERK = ITEMS.register("speedcola_perk", () -> new PerkItem(new Item.Properties(), "Mining Speed"));
    public static final DeferredItem<Item> STAMINUP_PERK = ITEMS.register("staminup_perk", () -> new PerkItem(new Item.Properties(), "Swiftness"));
    public static final DeferredItem<Item> DOUBLETAP_PERK = ITEMS.register("doubletap_perk", () -> new PerkItem(new Item.Properties(), "Attack Speed"));
    public static final DeferredItem<Item> VICTORIOUSTORTOISE_PERK = ITEMS.register("victorioustortoise_perk", () -> new PerkItem(new Item.Properties(), "Absorption"));
    public static final DeferredItem<Item> PHDFLOPPER_PERK = ITEMS.register("phdflopper_perk", () -> new PerkItem(new Item.Properties(), "Fire Resistance"));
    public static final DeferredItem<Item> QUICKREVIVE_PERK = ITEMS.register("quickrevive_perk", () -> new PerkItem(new Item.Properties(), "Regeneration"));
    public static final DeferredItem<Item> WHOSWHO_PERK = ITEMS.register("whoswho_perk", () -> new PerkItem(new Item.Properties(), "Luck"));
    public static final DeferredItem<Item> WINTERSWAIL_PERK = ITEMS.register("winterswail_perk", () -> new PerkItem(new Item.Properties(), "Jump"));
    public static final DeferredItem<Item> DEATHPERCEPTION_PERK = ITEMS.register("deathperception_perk", () -> new PerkItem(new Item.Properties(), "Night Vision"));
    public static final DeferredItem<Item> DEADSHOT_PERK = ITEMS.register("deadshot_perk", () -> new PerkItem(new Item.Properties(), "Strength"));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

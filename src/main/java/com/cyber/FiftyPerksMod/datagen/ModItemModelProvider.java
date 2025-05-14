package com.cyber.FiftyPerksMod.datagen;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FiftyPerksMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ELEMENT115_INGOT.get());
        basicItem(ModItems.RAW_ELEMENT115.get());

        /** Perk Holder Items */
        basicItem(ModItems.PERK_HOLDER.get());
        basicItem(ModItems.PERK_HOLDER_TIER2.get());
        basicItem(ModItems.PERK_HOLDER_TIER3.get());
        basicItem(ModItems.TIER2_PERK_UPGRADE_KIT.get());
        basicItem(ModItems.TIER3_PERK_UPGRADE_KIT.get());

        /** Crystals */
        basicItem(ModItems.ELEMENT115_CRYSTAL.get());
        basicItem(ModItems.BASIC_PERK_CRYSTAL.get());
        basicItem(ModItems.ADVANCED_PERK_CRYSTAL.get());
        basicItem(ModItems.SUPER_PERK_CRYSTAL.get());
        basicItem(ModItems.JUGGERNOG_CRYSTAL.get());
        basicItem(ModItems.SPEEDCOLA_CRYSTAL.get());
        basicItem(ModItems.STAMINUP_CRYSTAL.get());
        basicItem(ModItems.VICTORIOUSTORTOISE_CRYSTAL.get());
        basicItem(ModItems.DOUBLETAP_CRYSTAL.get());
        basicItem(ModItems.PHDFLOPPER_CRYSTAL.get());
        basicItem(ModItems.QUICKREVIVE_CRYSTAL.get());
        basicItem(ModItems.WHOSWHO_CRYSTAL.get());
        basicItem(ModItems.WINTERSWAIL_CRYSTAL.get());
        basicItem(ModItems.DEATHPERCEPTION_CRYSTAL.get());
        basicItem(ModItems.DEADSHOT_CRYSTAL.get());
        basicItem(ModItems.STRONGHOLD_CRYSTAL.get());

        /** Perks */
        basicItem(ModItems.JUGGERNOG_PERK.get());
        basicItem(ModItems.SPEEDCOLA_PERK.get());
        basicItem(ModItems.STAMINUP_PERK.get());
        basicItem(ModItems.VICTORIOUSTORTOISE_PERK.get());
        basicItem(ModItems.DOUBLETAP_PERK.get());
        basicItem(ModItems.PHDFLOPPER_PERK.get());
        basicItem(ModItems.QUICKREVIVE_PERK.get());
        basicItem(ModItems.WHOSWHO_PERK.get());
        basicItem(ModItems.WINTERSWAIL_PERK.get());
        basicItem(ModItems.DEATHPERCEPTION_PERK.get());
        basicItem(ModItems.DEADSHOT_PERK.get());
        basicItem(ModItems.STRONGHOLD_PERK.get());
    }
}

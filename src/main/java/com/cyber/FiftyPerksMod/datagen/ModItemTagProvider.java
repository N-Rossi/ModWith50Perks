package com.cyber.FiftyPerksMod.datagen;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.item.ModItems;
import com.cyber.FiftyPerksMod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, FiftyPerksMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.PERK_HOLDER_TAG)
                .add(ModItems.PERK_HOLDER.get())
                .add(ModItems.PERK_HOLDER_TIER3.get())
                .add(ModItems.PERK_HOLDER_TIER2.get());

        tag(ModTags.Items.CRYSTALS_TAG)
                .add(ModItems.ELEMENT115_CRYSTAL.get())
                .add(ModItems.BASIC_PERK_CRYSTAL.get())
                .add(ModItems.ADVANCED_PERK_CRYSTAL.get())
                .add(ModItems.SUPER_PERK_CRYSTAL.get())
                .add(ModItems.STAMINUP_CRYSTAL.get())
                .add(ModItems.SPEEDCOLA_CRYSTAL.get())
                .add(ModItems.JUGGERNOG_CRYSTAL.get())
                .add(ModItems.DOUBLETAP_CRYSTAL.get())
                .add(ModItems.VICTORIOUSTORTOISE_CRYSTAL.get())
                .add(ModItems.PHDFLOPPER_CRYSTAL.get())
                .add(ModItems.QUICKREVIVE_CRYSTAL.get())
                .add(ModItems.WINTERSWAIL_CRYSTAL.get())
                .add(ModItems.DEATHPERCEPTION_CRYSTAL.get())
                .add(ModItems.DEADSHOT_CRYSTAL.get())
                .add(ModItems.STRONGHOLD_CRYSTAL.get())
                .add(ModItems.WHOSWHO_CRYSTAL.get());

        tag(ModTags.Items.PERKS_TAG)
                .add(ModItems.JUGGERNOG_PERK.get())
                .add(ModItems.SPEEDCOLA_PERK.get())
                .add(ModItems.VICTORIOUSTORTOISE_PERK.get())
                .add(ModItems.DOUBLETAP_PERK.get())
                .add(ModItems.PHDFLOPPER_PERK.get())
                .add(ModItems.QUICKREVIVE_PERK.get())
                .add(ModItems.WHOSWHO_PERK.get())
                .add(ModItems.WINTERSWAIL_PERK.get())
                .add(ModItems.DEATHPERCEPTION_PERK.get())
                .add(ModItems.DEADSHOT_PERK.get())
                .add(ModItems.STRONGHOLD_PERK.get())
                .add(ModItems.STAMINUP_PERK.get());

    }
}

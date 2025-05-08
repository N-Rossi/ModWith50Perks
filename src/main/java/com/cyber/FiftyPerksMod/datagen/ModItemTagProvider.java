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
                .add(ModItems.PERK_HOLDER.get());

        tag(ModTags.Items.CRYSTALS_TAG)
                .add(ModItems.ELEMENT115_CRYSTAL.get())
                .add(ModItems.STAMINUP_CRYSTAL.get())
                .add(ModItems.SPEEDCOLA_CRYSTAL.get())
                .add(ModItems.JUGGERNOG_CRYSTAL.get());

        tag(ModTags.Items.PERKS_TAG)
                .add(ModItems.JUGGERNOG_PERK.get())
                .add(ModItems.SPEEDCOLA_PERK.get())
                .add(ModItems.STAMINUP_PERK.get());

    }
}

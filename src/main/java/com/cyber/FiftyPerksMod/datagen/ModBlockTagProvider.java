package com.cyber.FiftyPerksMod.datagen;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import com.cyber.FiftyPerksMod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FiftyPerksMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ELEMENT115_BLOCK.get())
                .add(ModBlocks.RAW_ELEMENT115_BLOCK.get())
                .add(ModBlocks.ELEMENT115_ORE.get())
                .add(ModBlocks.NETHER_ELEMENT115_ORE.get())
                .add(ModBlocks.UPGRADE_STATION.get())
                .add(ModBlocks.DEEPSLATE_ELEMENT115_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ELEMENT115_ORE.get())
                .add(ModBlocks.DEEPSLATE_ELEMENT115_ORE.get())
                .add(ModBlocks.NETHER_ELEMENT115_ORE.get());

    }
}

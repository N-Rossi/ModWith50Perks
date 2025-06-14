package com.cyber.FiftyPerksMod.datagen;

import com.cyber.FiftyPerksMod.block.ModBlocks;
import com.cyber.FiftyPerksMod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ELEMENT115_BLOCK.get());
        dropSelf(ModBlocks.UPGRADE_STATION.get());
        dropSelf(ModBlocks.RAW_ELEMENT115_BLOCK.get());

        add(ModBlocks.ELEMENT115_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ELEMENT115_ORE.get(), ModItems.RAW_ELEMENT115.get(), 1, 5));
        add(ModBlocks.NETHER_ELEMENT115_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.NETHER_ELEMENT115_ORE.get(), ModItems.RAW_ELEMENT115.get(), 3, 7));
        add(ModBlocks.DEEPSLATE_ELEMENT115_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_ELEMENT115_ORE.get(), ModItems.RAW_ELEMENT115.get(), 1, 5));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}

package com.cyber.FiftyPerksMod.util;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PERK_HOLDER_TAG = createTag("perk_holder");
        public static final TagKey<Item> PERKS_TAG = createTag("perks");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, name));
        }
    }
}

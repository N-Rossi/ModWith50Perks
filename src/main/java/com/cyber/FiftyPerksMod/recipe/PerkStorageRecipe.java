package com.cyber.FiftyPerksMod.recipe;

import com.cyber.FiftyPerksMod.item.custom.BasicPerkHolderItem;
import com.cyber.FiftyPerksMod.item.custom.PerkHolderItem;
import com.cyber.FiftyPerksMod.util.ModDataComponents;
import com.cyber.FiftyPerksMod.util.ModTags;
import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;


public class PerkStorageRecipe extends CustomRecipe {
    public PerkStorageRecipe(CraftingBookCategory category) {
        super(category);
    }


    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack itemstack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemstack1 = input.getItem(i);
            if (!itemstack1.isEmpty()) {
                if (itemstack1.is(ModTags.Items.PERKS_TAG)) {
                    if (!itemstack.isEmpty()) {
                        return false;
                    }

                    itemstack = itemstack1;
                } else {
                    if (!(itemstack1.getItem() instanceof PerkHolderItem)) {
                        return false;
                    }

                    list.add(itemstack1);
                }
            }
        }

        return !itemstack.isEmpty() && !list.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        ItemStack perkItem = ItemStack.EMPTY;
        ItemStack perkHolder = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.is(ModTags.Items.PERKS_TAG)) {
                    if (!perkItem.isEmpty()) {
                        return ItemStack.EMPTY; // Only 1 perk allowed
                    }
                    perkItem = stack;
                } else if (stack.getItem() instanceof BasicPerkHolderItem) {
                    if (!perkHolder.isEmpty()) {
                        return ItemStack.EMPTY; // Only 1 holder allowed
                    }
                    perkHolder = stack;
                }
            }
        }

        if (!perkItem.isEmpty() && !perkHolder.isEmpty()) {
            ItemStack result = perkHolder.copy();
            result.setCount(1);

            BasicPerkHolderItem holderItem = (BasicPerkHolderItem) result.getItem();
            ItemStackHandler handler = holderItem.getHandler(result, provider);

            for (int slot = 0; slot < handler.getSlots(); slot++) {
                if (handler.getStackInSlot(slot).isEmpty()) {
                    handler.setStackInSlot(slot, perkItem.copy());
                    break;
                }
            }

            holderItem.saveHandler(result, handler, provider);


            return result;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PERK_STORAGE.get();
    }
}

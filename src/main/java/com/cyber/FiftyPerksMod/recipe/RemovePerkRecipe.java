package com.cyber.FiftyPerksMod.recipe;

import com.cyber.FiftyPerksMod.item.custom.BasicPerkHolderItem;
import com.cyber.FiftyPerksMod.util.ModDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class RemovePerkRecipe extends CustomRecipe {

    public static final ThreadLocal<ItemStack> REMOVED_PERK = new ThreadLocal<>();


    public RemovePerkRecipe(CraftingBookCategory category) {
        super(category);
    }


    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack perkHolder = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (!(stack.getItem() instanceof BasicPerkHolderItem)) return false;
                if (!perkHolder.isEmpty()) return false; // Only 1 holder
                perkHolder = stack;
            }
        }

        return !perkHolder.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BasicPerkHolderItem holderItem) {
                ItemStack result = stack.copy();
                ItemStackHandler handler = holderItem.getHandler(result, provider);

                // Remove the *last* filled slot
                for (int slot = handler.getSlots() - 1; slot >= 0; slot--) {
                    if (!handler.getStackInSlot(slot).isEmpty()) {
                        ItemStack removed = handler.getStackInSlot(slot);
                        REMOVED_PERK.set(removed.copy());
                        handler.setStackInSlot(slot, ItemStack.EMPTY);
                        break;
                    }
                }

                holderItem.saveHandler(result, handler, provider);
                result.set(ModDataComponents.PERK_WAS_REMOVED.get(), true);
                result.setCount(1);
                return result;
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.REMOVE_PERK.get();
    }
}

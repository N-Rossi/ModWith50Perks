package com.cyber.FiftyPerksMod.recipe;

import com.cyber.FiftyPerksMod.item.ModItems;
import com.cyber.FiftyPerksMod.item.custom.BasicPerkHolderItem;
import com.cyber.FiftyPerksMod.util.ModDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class PerkHolderUpgradeRecipe extends CustomRecipe {

    public static final ThreadLocal<List<ItemStack>> DROPPED_PERKS = ThreadLocal.withInitial(ArrayList::new);


    public PerkHolderUpgradeRecipe(net.minecraft.world.item.crafting.CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean foundHolder = false;
        boolean foundDiamond = false;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof BasicPerkHolderItem) {
                    if (foundHolder) return false;
                    foundHolder = true;
                } else if (stack.getItem() == Items.DIAMOND) {
                    if (foundDiamond) return false;
                    foundDiamond = true;
                } else {
                    return false;
                }
            }
        }

        return foundHolder && foundDiamond;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BasicPerkHolderItem holderItem) {
                ItemStackHandler handler = holderItem.getHandler(stack, provider);

                List<ItemStack> removedPerks = new ArrayList<>();
                for (int slot = 0; slot < handler.getSlots(); slot++) {
                    ItemStack perk = handler.getStackInSlot(slot);
                    if (!perk.isEmpty()) {
                        removedPerks.add(perk.copy());
                        handler.setStackInSlot(slot, ItemStack.EMPTY);
                    }
                }

                DROPPED_PERKS.set(removedPerks);

                // Create upgraded holder (e.g., Tier 2)
                ItemStack result = new ItemStack(ModItems.PERK_HOLDER_TIER2.get());
                result.set(ModDataComponents.PERK_HOLDER_UPGRADED, true);
                result.setCount(1);

                return result;
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PERK_HOLDER_UPGRADE.get();
    }
}

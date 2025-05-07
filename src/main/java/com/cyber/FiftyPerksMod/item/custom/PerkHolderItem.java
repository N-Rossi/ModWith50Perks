package com.cyber.FiftyPerksMod.item.custom;

import com.cyber.FiftyPerksMod.util.ModDataComponents;
import com.cyber.FiftyPerksMod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.List;

/** TODO Make the item able to hold perks. on right click open gui OR place in crafting table with perks. Place holder in table alone to extract all perks from it. */
public class PerkHolderItem extends Item {
    private static final int SLOT_COUNT = 4;

    public PerkHolderItem(Properties properties) {
        super(properties);
    }

    public static ItemStackHandler getHandler(ItemStack stack, HolderLookup.Provider provider) {
        CompoundTag tag = stack.get(ModDataComponents.PERK_INVENTORY.get());
        ItemStackHandler handler = new ItemStackHandler(SLOT_COUNT) {
            @Override
            protected void onContentsChanged(int slot) {
                saveHandler(stack, this, provider);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack perkItem) {
                return perkItem.is(ModTags.Items.PERKS_TAG);
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };

        if (tag != null) {
            handler.deserializeNBT(provider, tag);
        }

        return handler;
    }

    private static void saveHandler(ItemStack stack, ItemStackHandler handler, HolderLookup.Provider provider) {
        CompoundTag serialized = handler.serializeNBT(provider);
        stack.set(ModDataComponents.PERK_INVENTORY.get(), serialized);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        // Get the item handler for the perk holder
        ItemStackHandler handler = getHandler(stack, context.registries());

        tooltipComponents.add(Component.literal("Stored Perks:"));

        // Iterate through the handler slots to add stored perks to the tooltip
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack perk = handler.getStackInSlot(i);
            if (!perk.isEmpty()) {
                tooltipComponents.add(Component.literal("- " + perk.getDisplayName().getString()));
            }
        }
    }
}

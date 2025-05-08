package com.cyber.FiftyPerksMod.item.custom;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.effect.ModEffects;
import com.cyber.FiftyPerksMod.util.ModDataComponents;
import com.cyber.FiftyPerksMod.util.ModTags;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.Optional;

/** TODO Make the item able to hold perks. on right click open gui OR place in crafting table with perks. Place holder in table alone to extract all perks from it. */
public class PerkHolderItem extends Item implements ICurioItem {
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

    public static void saveHandler(ItemStack stack, ItemStackHandler handler, HolderLookup.Provider provider) {
        CompoundTag serialized = handler.serializeNBT(provider);
        stack.set(ModDataComponents.PERK_INVENTORY.get(), serialized);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        // Get the item handler for the perk holder
        ItemStackHandler handler = getHandler(stack, context.registries());
        String storedPerks = stack.get(ModDataComponents.STORED_PERK);
        tooltipComponents.add(Component.literal("Stored Perks:"));

        for(int i=0; i < handler.getSlots(); i++) {
            ItemStack perkStack = handler.getStackInSlot(i);

            if(!perkStack.isEmpty()) {
                tooltipComponents.add(Component.literal("- " + perkStack.getHoverName().getString()));
            }
        }
        tooltipComponents.add(Component.literal("Max Perks: 4"));
    }



    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        ItemStackHandler handler = getHandler(stack, player.level().registryAccess());
//        String storedPerk = stack.get(ModDataComponents.STORED_PERK);

        for(int i=0; i < handler.getSlots(); i++) {
            ItemStack perkStack = handler.getStackInSlot(i);
            if(perkStack.isEmpty()) continue;

            String perkId = BuiltInRegistries.ITEM.getKey(perkStack.getItem()).toString();

            switch (perkId) {
                case "fiftyperksmod:juggernog_perk" -> {
                    if (!player.hasEffect(ModEffects.JUG_PERK_EFFECT)) {
                        player.addEffect(new MobEffectInstance(ModEffects.JUG_PERK_EFFECT, 210, 0, true, false));
                    }
                }
                case "fiftyperksmod:speedcola_perk" -> {
                    if (!player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 210, 0, true, false));
                    }
                }
                default -> {
                    // Optional: log or ignore unknown perks
                    System.out.println("");
                }
            }
        }


    }

}

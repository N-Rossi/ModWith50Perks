package com.cyber.FiftyPerksMod.item.custom;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.effect.ModEffects;
import com.cyber.FiftyPerksMod.util.ModDataComponents;
import com.cyber.FiftyPerksMod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

/**
 * @Author Cyber
 * Perk Holder Item extends Item and is a Curio Item
 * It holds up to 4 perks and when the Curio is equipped it will give the stored perks' effects to the player
 */
public class PerkHolderItem extends Item implements ICurioItem {
    private static final int SLOT_COUNT = 4;
    private static final ResourceLocation DOUBLE_TAP_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "doubletap_attack_speed");


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
        tooltipComponents.add(Component.literal("Stored Perks:"));

        for(int i=0; i < handler.getSlots(); i++) {
            ItemStack perkStack = handler.getStackInSlot(i);

            if(!perkStack.isEmpty()) {
                tooltipComponents.add(Component.literal("- " + perkStack.getHoverName().getString()));
            }
        }
        tooltipComponents.add(Component.literal("Max Perks: 4"));
    }


    /**
     * Runs on tick when curio is equipped
     * Adds effects for each perk that is equipped.
     *
     * @param slotContext The context for the slot that the ItemStack is in
     * @param stack       The ItemStack in question
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        ItemStackHandler handler = getHandler(stack, player.level().registryAccess());

        for(int i=0; i < handler.getSlots(); i++) {
            ItemStack perkStack = handler.getStackInSlot(i);
            if(perkStack.isEmpty()) continue;

            String perkId = BuiltInRegistries.ITEM.getKey(perkStack.getItem()).toString();

            switch (perkId) {
                case "fiftyperksmod:juggernog_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(ModEffects.JUG_PERK_EFFECT);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(ModEffects.JUG_PERK_EFFECT, 210, 0, true, false));
                    }
                }
                case "fiftyperksmod:speedcola_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.DIG_SPEED);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 210, 0, true, false));
                    }
                }
                case "fiftyperksmod:staminup_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.MOVEMENT_SPEED);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 210, 0, true, false));
                    }
                }
                case "fiftyperksmod:victorioustortoise_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.ABSORPTION);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 210, 0, true, false));
                    }
                }
                case "fiftyperksmod:doubletap_perk" -> {
                    AttributeInstance attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);
                    if(attackSpeed != null && attackSpeed.getModifier(DOUBLE_TAP_MODIFIER_ID) == null) {
                        attackSpeed.addTransientModifier(new AttributeModifier(
                                DOUBLE_TAP_MODIFIER_ID,
                                2,
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ));
                    }
                }
                default -> {
                    System.out.println("Unknown Perk");
                }
            }
        }
    }

    /**
     * Remove Double-Tap on Unequip
     * @param slotContext Context about the slot that the ItemStack was just unequipped from
     * @param newStack    The new ItemStack in the slot
     * @param stack       The ItemStack in question
     */
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        Player player = (Player) slotContext.entity();

        AttributeInstance attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeed != null && attackSpeed.getModifier(DOUBLE_TAP_MODIFIER_ID) != null) {
            attackSpeed.removeModifier(DOUBLE_TAP_MODIFIER_ID);
        }
    }

}

package com.cyber.FiftyPerksMod.item.custom;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.effect.ModEffects;
import com.cyber.FiftyPerksMod.recipe.RemovePerkRecipe;
import com.cyber.FiftyPerksMod.util.ModDataComponents;
import com.cyber.FiftyPerksMod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicPerkHolderItem extends Item implements ICurioItem {

    private final int slotcount;

    protected abstract ResourceLocation getDoubleTapModifierId();
    protected abstract ResourceLocation getStrongholdModifierId();

    protected BasicPerkHolderItem(Properties properties, int slotcount) {
        super(properties);
        this.slotcount = slotcount;
    }

    public ItemStackHandler getHandler(ItemStack stack, HolderLookup.Provider provider) {
        CompoundTag tag = stack.get(ModDataComponents.PERK_INVENTORY.get());
        ItemStackHandler handler = new ItemStackHandler(this.slotcount) {
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

    public void saveHandler(ItemStack stack, ItemStackHandler handler, HolderLookup.Provider provider) {
        CompoundTag serialized = handler.serializeNBT(provider);
        stack.set(ModDataComponents.PERK_INVENTORY.get(), serialized);
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);

        checkPerkRemoval(level, stack, player);
    }

    private void checkPerkRemoval(Level level, ItemStack stack, Player player) {
        if (!level.isClientSide && Boolean.TRUE.equals(stack.get(ModDataComponents.PERK_WAS_REMOVED))) {
            ItemStack removed = RemovePerkRecipe.REMOVED_PERK.get();
            if (removed != null && !removed.isEmpty()) {
                player.drop(removed, false);
            }
            RemovePerkRecipe.REMOVED_PERK.remove(); // Clean it up
            stack.remove(ModDataComponents.PERK_WAS_REMOVED); // Clean flag
        }
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
        tooltipComponents.add(Component.literal("Max Perks: " + slotcount));
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
                case "fiftyperksmod:phdflopper_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.FIRE_RESISTANCE);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 210, 0, true, false));
                    }
                }
                case "fiftyperksmod:quickrevive_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.REGENERATION);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 210, 0, true, false));
                    }
                }
                case "fiftyperksmod:whoswho_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.LUCK);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.LUCK, 210, 1, true, false));
                    }
                }
                case "fiftyperksmod:winterswail_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.JUMP);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 210, 1, true, false));
                    }
                }
                case "fiftyperksmod:deathperception_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.NIGHT_VISION);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 210, 1, true, false));
                    }
                }
                case "fiftyperksmod:deadshot_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.DAMAGE_BOOST);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 210, 0, true, false));
                    }
                }
//                case "fiftyperksmod:stronghold_perk" -> {
//                    MobEffectInstance currentEffect = player.getEffect(ModEffects.STRONGHOLD_PERK_EFFECT);
//                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
//                        player.addEffect(new MobEffectInstance(ModEffects.STRONGHOLD_PERK_EFFECT, 210, 0, true, false));
//                    }
//                }
                case "fiftyperksmod:doubletap_perk" -> {
                    AttributeInstance attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);
                    if(attackSpeed != null && attackSpeed.getModifier(getDoubleTapModifierId()) == null) {
                        attackSpeed.addTransientModifier(new AttributeModifier(
                                getDoubleTapModifierId(),
                                2,
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ));
                    }
                }
                case "fiftyperksmod:stronghold_perk" -> {
                    AttributeInstance toughness = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
                    if(toughness != null && toughness.getModifier(getStrongholdModifierId()) == null) {
                        toughness.addTransientModifier(new AttributeModifier(
                                getStrongholdModifierId(),
                                1.25,
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

        /** Double Tap */
        AttributeInstance attackSpeed = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeed != null && attackSpeed.getModifier(getDoubleTapModifierId()) != null) {
            attackSpeed.removeModifier(getDoubleTapModifierId());
        }
        /** Stone Cold Stronghold */
        AttributeInstance toughness = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
        if (toughness != null && toughness.getModifier(getStrongholdModifierId()) != null) {
            toughness.removeModifier(getStrongholdModifierId());
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        // Check if the player already has another perk holder equipped
        return top.theillusivec4.curios.api.CuriosApi.getCuriosInventory(player)
                .map(curiosInventory -> {
                    for (var result : curiosInventory.findCurios(item -> item.getItem() instanceof BasicPerkHolderItem)) {
                        // If the equipped item isn't the same as the one trying to equip, deny it
                        if (!ItemStack.isSameItemSameComponents(result.stack(), stack)) {
                            return false;
                        }
                    }
                    return true; // No other perk holder found, allow equip
                })
                .orElse(true); // If no curios inventory is found, allow equip just in case
    }

}

//package com.cyber.FiftyPerksMod.util;
//
//import com.cyber.FiftyPerksMod.FiftyPerksMod;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.ai.attributes.AttributeModifier;
//import net.minecraft.world.item.ItemStack;
//import top.theillusivec4.curios.api.SlotContext;
//import top.theillusivec4.curios.api.type.capability.ICurio;
//
//import javax.annotation.Nonnull;
//
//public class PerkHolderSlot implements ICurio {
//    public static final AttributeModifier PERKHOLDER_CURIO_MODIFIER = new AttributeModifier(
//            ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "perkholder"), 1.0D,
//            AttributeModifier.Operation.ADD_VALUE);
//
//    private final ItemStack stack;
//
//    public PerkHolderSlot(ItemStack stack) {
//        this.stack = stack;
//    }
//
//    @Override
//    public ItemStack getStack() {
//        return this.stack;
//    }
//
//    @Override
//    public void curioTick(SlotContext slotContext) {
//        // Maybe add effects here
        /** TODO Use this class and make it give perk effects */
//    }
//
////    @Override
////    public boolean canEquip(SlotContext slotContext) {
////        return ElytraSlotCommonMod.canEquip(slotContext.entity());
////    }
//
//    @Nonnull
//    @Override
//    public ICurio.SoundInfo getEquipSound(SlotContext slotContext) {
//        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_LEATHER.value(), 1.0F, 1.0F);
//    }
//
//    @Override
//    public boolean canEquipFromUse(SlotContext slotContext) {
//        return true;
//    }
//}

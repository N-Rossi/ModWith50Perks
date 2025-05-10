package com.cyber.FiftyPerksMod.effect;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ToughnessEffect extends MobEffect {

    ResourceLocation TOUGHNESS_MODIFIER_UUID = ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "stronghold_toughness_modifier");
    protected ToughnessEffect(net.minecraft.world.effect.MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.getAttribute(Attributes.ARMOR_TOUGHNESS).hasModifier(TOUGHNESS_MODIFIER_UUID)) {
            AttributeModifier modifier = new AttributeModifier(
                    TOUGHNESS_MODIFIER_UUID,
                    1.25D, // 25% boost
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );
            livingEntity.getAttribute(Attributes.ARMOR_TOUGHNESS).addPermanentModifier(modifier);
        }
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

}

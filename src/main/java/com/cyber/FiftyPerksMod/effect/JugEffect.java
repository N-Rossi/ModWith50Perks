package com.cyber.FiftyPerksMod.effect;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class JugEffect extends MobEffect {

    private static final UUID JUG_HEALTH_MODIFIED_UUID = UUID.fromString("def8c045-721f-4002-abd9-2182a47e3f82");

    protected JugEffect(MobEffectCategory category, int color) {
        super(category, color);

        this.addAttributeModifier(
                Attributes.MAX_HEALTH,
                ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "juggernog_health_boost"),
                8.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // No per-tick behavior needed for Jug
        return false;
    }

}

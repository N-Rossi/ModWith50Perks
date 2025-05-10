package com.cyber.FiftyPerksMod.effect;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, FiftyPerksMod.MOD_ID);

    public static final Holder<MobEffect> SLIMEY_EFFECT = MOB_EFFECTS.register("slimey",
            () -> new SlimeyEffect(MobEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "slimey"), -0.25f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> JUG_PERK_EFFECT = MOB_EFFECTS.register("jug_perk",
            () -> new JugEffect(MobEffectCategory.BENEFICIAL, 0xff2222));
    public static final Holder<MobEffect> STRONGHOLD_PERK_EFFECT = MOB_EFFECTS.register("stronghold_perk",
            () -> new ToughnessEffect(MobEffectCategory.BENEFICIAL, 0x70591a));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

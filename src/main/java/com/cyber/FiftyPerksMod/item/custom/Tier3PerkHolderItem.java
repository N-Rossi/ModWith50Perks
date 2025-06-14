package com.cyber.FiftyPerksMod.item.custom;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.resources.ResourceLocation;

public class Tier3PerkHolderItem extends BasicPerkHolderItem{

    public Tier3PerkHolderItem(Properties properties) {
        super(properties, 16);
    }

    @Override
    protected ResourceLocation getDoubleTapModifierId() {
        return ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "doubletap_attack_speed");
    }

    @Override
    protected ResourceLocation getStrongholdModifierId() {
        return ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "stronghold_armor_toughness");
    }
}

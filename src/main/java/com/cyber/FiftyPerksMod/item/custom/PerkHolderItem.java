package com.cyber.FiftyPerksMod.item.custom;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.resources.ResourceLocation;

/**
 * @Author Cyber
 * Perk Holder Item extends Item and is a Curio Item
 * It holds up to 4 perks and when the Curio is equipped it will give the stored perks' effects to the player
 */
public class PerkHolderItem extends BasicPerkHolderItem {

    public PerkHolderItem(Properties properties) {
        super(properties, 4);
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

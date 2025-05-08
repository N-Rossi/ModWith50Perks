package com.cyber.FiftyPerksMod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class PerkItem extends Item {
    private final String effectString;
    public PerkItem(Properties properties, String effect) {
        super(properties);
        this.effectString = effect;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.literal("Gives: " + effectString));
    }

    public String getEffect() {
        return effectString;
    }
}

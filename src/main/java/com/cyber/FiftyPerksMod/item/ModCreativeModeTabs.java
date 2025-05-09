package com.cyber.FiftyPerksMod.item;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FiftyPerksMod.MOD_ID);

    public static final Supplier<CreativeModeTab> FIFTY_PERKS_TAB = CREATIVE_MODE_TAB.register("fifty_perks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ELEMENT115_INGOT.get()))
                    .title(Component.translatable("creativetab.fiftyperksmod.fifty_perks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ELEMENT115_INGOT);
                        output.accept(ModItems.RAW_ELEMENT115);
                        output.accept(ModBlocks.ELEMENT115_BLOCK);
                        output.accept(ModBlocks.RAW_ELEMENT115_BLOCK);
                        output.accept(ModBlocks.ELEMENT115_ORE);
                        output.accept(ModBlocks.NETHER_ELEMENT115_ORE);
                        output.accept(ModBlocks.DEEPSLATE_ELEMENT115_ORE);

                        output.accept(ModItems.PERK_HOLDER);
                        output.accept(ModItems.PERK_HOLDER_TIER2);

                        output.accept(ModItems.ELEMENT115_CRYSTAL);
                        output.accept(ModItems.BASIC_PERK_CRYSTAL);
                        output.accept(ModItems.JUGGERNOG_CRYSTAL);
                        output.accept(ModItems.SPEEDCOLA_CRYSTAL);
                        output.accept(ModItems.DOUBLETAP_CRYSTAL);
                        output.accept(ModItems.STAMINUP_CRYSTAL);
                        output.accept(ModItems.PHDFLOPPER_CRYSTAL);
                        output.accept(ModItems.VICTORIOUSTORTOISE_CRYSTAL);
                        output.accept(ModItems.QUICKREVIVE_CRYSTAL);
                        output.accept(ModItems.WHOSWHO_CRYSTAL);
                        output.accept(ModItems.WINTERSWAIL_CRYSTAL);

                        output.accept(ModItems.JUGGERNOG_PERK);
                        output.accept(ModItems.SPEEDCOLA_PERK);
                        output.accept(ModItems.STAMINUP_PERK);
                        output.accept(ModItems.VICTORIOUSTORTOISE_PERK);
                        output.accept(ModItems.DOUBLETAP_PERK);
                        output.accept(ModItems.PHDFLOPPER_PERK);
                        output.accept(ModItems.QUICKREVIVE_PERK);
                        output.accept(ModItems.WHOSWHO_PERK);
                        output.accept(ModItems.WINTERSWAIL_PERK);

                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

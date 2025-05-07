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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GALLIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.fiftyperksmod.fifty_perks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GALLIUM_INGOT);
                        output.accept(ModItems.RAW_GALLIUM);
                        output.accept(ModBlocks.GALLIUM_BLOCK);
                        output.accept(ModBlocks.GALLIUM_ORE);
                        output.accept(ModBlocks.NETHER_GALLIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_GALLIUM_ORE);
                        output.accept(ModItems.PERK_HOLDER);

                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

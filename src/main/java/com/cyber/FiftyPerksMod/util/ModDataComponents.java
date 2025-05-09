package com.cyber.FiftyPerksMod.util;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.util.ExtraCodecs.*;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, FiftyPerksMod.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> PERK_INVENTORY =
            DATA_COMPONENTS.register("perk_inventory", () ->
                    DataComponentType.<CompoundTag>builder()
                            .persistent(CompoundTag.CODEC)
                            .networkSynchronized(ByteBufCodecs.COMPOUND_TAG)
                            .build()
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> STORED_PERK =
            DATA_COMPONENTS.register("stored_perk", () ->
                    new DataComponentType.Builder<String>()
                            .persistent(Codec.STRING)
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                            .build()
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> PERK_WAS_REMOVED =
            DATA_COMPONENTS.register("perk_was_removed", () ->
                    DataComponentType.<Boolean>builder()
                            .persistent(Codec.BOOL)
                            .networkSynchronized(ByteBufCodecs.BOOL)
                            .build()
            );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }

}

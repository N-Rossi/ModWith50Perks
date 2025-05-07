package com.cyber.FiftyPerksMod.util;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, FiftyPerksMod.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> PERK_INVENTORY =
            DATA_COMPONENTS.register("perk_inventory", () ->
                    DataComponentType.<CompoundTag>builder()
                            .persistent(CompoundTag.CODEC)
                            .networkSynchronized(new StreamCodec<RegistryFriendlyByteBuf, CompoundTag>() {
                                @Override
                                public CompoundTag decode(RegistryFriendlyByteBuf buf) {
                                    return buf.readNbt();
                                }

                                @Override
                                public void encode(RegistryFriendlyByteBuf buf, CompoundTag value) {
                                    buf.writeNbt(value);
                                }
                            })
                            .build()
            );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }

}

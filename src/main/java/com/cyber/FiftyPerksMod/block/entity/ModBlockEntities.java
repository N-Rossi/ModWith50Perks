package com.cyber.FiftyPerksMod.block.entity;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FiftyPerksMod.MOD_ID);


    public static final Supplier<BlockEntityType<UpgradeStationBlockEntity>> UPGRADE_STATION_BE =
            BLOCK_ENTITIES.register("upgrade_station_be", () -> BlockEntityType.Builder.of(
                    UpgradeStationBlockEntity::new, ModBlocks.UPGRADE_STATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

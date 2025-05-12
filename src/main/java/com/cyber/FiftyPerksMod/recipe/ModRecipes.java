package com.cyber.FiftyPerksMod.recipe;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FiftyPerksMod.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, FiftyPerksMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PerkStorageRecipe>> PERK_STORAGE =
            RECIPE_SERIALIZERS.register("perk_storage", () ->
                    new SimpleCraftingRecipeSerializer<>(PerkStorageRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RemovePerkRecipe>> REMOVE_PERK =
            RECIPE_SERIALIZERS.register("remove_perk", () ->
                    new SimpleCraftingRecipeSerializer<>(RemovePerkRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<UpgradeStationRecipe>> UPGRADE_STATION_SERIALIZER =
            RECIPE_SERIALIZERS.register("upgrade_station", UpgradeStationRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<UpgradeStationRecipe>> UPGRADE_STATION_TYPE =
            TYPES.register("upgrade_station", () -> new RecipeType<UpgradeStationRecipe>() {
                @Override
                public String toString() {
                    return "upgrade_station";
                }
            });



    public static void register(net.neoforged.bus.api.IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);


    }
}

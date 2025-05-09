package com.cyber.FiftyPerksMod.recipe;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FiftyPerksMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PerkStorageRecipe>> PERK_STORAGE =
            RECIPE_SERIALIZERS.register("perk_storage", () ->
                    new SimpleCraftingRecipeSerializer<>(PerkStorageRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RemovePerkRecipe>> REMOVE_PERK =
            RECIPE_SERIALIZERS.register("remove_perk", () ->
                    new SimpleCraftingRecipeSerializer<>(RemovePerkRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PerkHolderUpgradeRecipe>> PERK_HOLDER_UPGRADE =
            RECIPE_SERIALIZERS.register("perk_holder_upgrade", () ->
                    new SimpleCraftingRecipeSerializer<>(PerkHolderUpgradeRecipe::new));

    public static void register(net.neoforged.bus.api.IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}

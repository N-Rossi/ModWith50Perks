package com.cyber.FiftyPerksMod.compat;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.recipe.ModRecipes;
import com.cyber.FiftyPerksMod.recipe.UpgradeStationRecipe;
import com.cyber.FiftyPerksMod.screen.custom.UpgradeStationScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

@JeiPlugin
public class JEIFiftyPerksModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new UpgradeStationRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<UpgradeStationRecipe> growthChamberRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.UPGRADE_STATION_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(UpgradeStationRecipeCategory.UPGRADE_STATION_RECIPE_RECIPE_TYPE, growthChamberRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(UpgradeStationScreen.class, 74, 30, 22, 20,
                UpgradeStationRecipeCategory.UPGRADE_STATION_RECIPE_RECIPE_TYPE);
    }
}

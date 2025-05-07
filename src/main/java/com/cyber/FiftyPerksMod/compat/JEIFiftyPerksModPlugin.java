package com.cyber.FiftyPerksMod.compat;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JEIFiftyPerksModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "jei_plugin");
    }

//    @Override
//    public void registerCategories(IRecipeCategoryRegistration registration) {
//        registration.addRecipeCategories(new GrowthChamberRecipeCategory(
//                registration.getJeiHelpers().getGuiHelper()));
//    }

//    @Override
//    public void registerRecipes(IRecipeRegistration registration) {
//        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
//
//        List<GrowthChamberRecipe> growthChamberRecipes = recipeManager
//                .getAllRecipesFor(ModRecipes.GROWTH_CHAMBER_TYPE.get()).stream().map(RecipeHolder::value).toList();
//        registration.addRecipes(GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_RECIPE_TYPE, growthChamberRecipes);
//    }

//    @Override
//    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
//        registration.addRecipeClickArea(GrowthChamberScreen.class, 74, 30, 22, 20,
//                GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_RECIPE_TYPE);
//    }
}

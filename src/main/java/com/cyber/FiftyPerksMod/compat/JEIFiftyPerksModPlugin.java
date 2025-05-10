package com.cyber.FiftyPerksMod.compat;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.recipe.ModRecipes;
import com.cyber.FiftyPerksMod.recipe.PerkHolderUpgradeRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIFiftyPerksModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PerkHolderUpgradeRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<PerkHolderUpgradeRecipe> perkHolderUpgradeRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.PERK_HOLDER_UPGRADE_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(PerkHolderUpgradeRecipeCategory.PERK_HOLDER_UPGRADE_RECIPE_TYPE, perkHolderUpgradeRecipes);
    }

//    @Override
//    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
//        registration.addRecipeClickArea(CraftingTableScreen.class, 74, 30, 22, 20,
//                GrowthChamberRecipeCategory.GROWTH_CHAMBER_RECIPE_RECIPE_TYPE);
//    }
}

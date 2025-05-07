package com.cyber.FiftyPerksMod.datagen;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import com.cyber.FiftyPerksMod.item.ModItems;
import com.cyber.FiftyPerksMod.recipe.PerkStorageRecipe;
import io.netty.util.internal.SuppressJava6Requirement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> GALLIUM_SMELTABLES = List.of(ModItems.RAW_GALLIUM,
                ModBlocks.GALLIUM_ORE, ModBlocks.NETHER_GALLIUM_ORE, ModBlocks.DEEPSLATE_GALLIUM_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GALLIUM_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.GALLIUM_INGOT.get())
                .unlockedBy("has_gallium", has(ModItems.GALLIUM_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GALLIUM_INGOT.get(), 9)
                .requires(ModBlocks.GALLIUM_BLOCK)
                .unlockedBy("has_gallium_block", has(ModBlocks.GALLIUM_BLOCK)).save(recipeOutput);

        oreSmelting(recipeOutput, GALLIUM_SMELTABLES, RecipeCategory.MISC, ModItems.GALLIUM_INGOT.get(), 0.25f, 200, "GALLIUM");
        oreBlasting(recipeOutput, GALLIUM_SMELTABLES, RecipeCategory.MISC, ModItems.GALLIUM_INGOT.get(), 0.25f, 100, "GALLIUM");

        SpecialRecipeBuilder.special(PerkStorageRecipe::new)
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "perk_storage"));
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, FiftyPerksMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

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
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder{
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> ELEMENT115_SMELTABLES = List.of(ModItems.RAW_ELEMENT115,
                ModBlocks.ELEMENT115_ORE, ModBlocks.NETHER_ELEMENT115_ORE, ModBlocks.DEEPSLATE_ELEMENT115_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ELEMENT115_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.ELEMENT115_INGOT.get())
                .unlockedBy("has_element115", has(ModItems.ELEMENT115_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ELEMENT115_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ELEMENT115.get())
                .unlockedBy("has_element115", has(ModItems.RAW_ELEMENT115)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ELEMENT115_INGOT.get(), 9)
                .requires(ModBlocks.ELEMENT115_BLOCK)
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ELEMENT115.get(), 9)
                .requires(ModBlocks.RAW_ELEMENT115_BLOCK)
                .unlockedBy("has_raw_element115_block", has(ModBlocks.RAW_ELEMENT115_BLOCK)).save(recipeOutput);

        oreSmelting(recipeOutput, ELEMENT115_SMELTABLES, RecipeCategory.MISC, ModItems.ELEMENT115_INGOT.get(), 0.25f, 200, "ELEMENT115");
        oreBlasting(recipeOutput, ELEMENT115_SMELTABLES, RecipeCategory.MISC, ModItems.ELEMENT115_INGOT.get(), 0.25f, 100, "ELEMENT115");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELEMETNT115_CRYSTAL.get())
                .pattern("GEG")
                .pattern("EDE")
                .pattern("GEG")
                .define('E', ModItems.ELEMENT115_INGOT.get())
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('D', Blocks.DIAMOND_BLOCK)
                .unlockedBy("has_element115", has(ModItems.ELEMENT115_INGOT)).save(recipeOutput);

        /** Perk Recipes */


        /** Recipe for Perk Holder */
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

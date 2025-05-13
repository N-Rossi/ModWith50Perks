package com.cyber.FiftyPerksMod.datagen;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import com.cyber.FiftyPerksMod.item.ModItems;
import com.cyber.FiftyPerksMod.recipe.PerkStorageRecipe;
import com.cyber.FiftyPerksMod.recipe.RemovePerkRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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

        /** Perk Holders */
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PERK_HOLDER.get())
                .pattern(" E ")
                .pattern("LLL")
                .pattern("LGL")
                .define('E', ModItems.ELEMENT115_INGOT.get())
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('L', Items.LEATHER)
                .unlockedBy("has_element115_ingot", has(ModItems.ELEMENT115_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TIER2_PERK_UPGRADE_KIT.get())
                .pattern("EDE")
                .pattern("MCM")
                .pattern("EDE")
                .define('E', ModItems.ELEMENT115_INGOT.get())
                .define('D', Items.DIAMOND)
                .define('C', ModItems.ELEMENT115_CRYSTAL)
                .define('M', Items.EMERALD)
                .unlockedBy("has_element115_crystal", has(ModItems.ELEMENT115_CRYSTAL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TIER3_PERK_UPGRADE_KIT.get())
                .pattern("EDE")
                .pattern("MCM")
                .pattern("EDE")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('D', Blocks.DIAMOND_BLOCK)
                .define('C', ModItems.TIER2_PERK_UPGRADE_KIT)
                .define('M', Blocks.EMERALD_BLOCK)
                .unlockedBy("has_tier2_perk_upgrade_kit", has(ModItems.TIER2_PERK_UPGRADE_KIT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.UPGRADE_STATION.get())
                .pattern(" E ")
                .pattern("PCP")
                .pattern("P P")
                .define('E', ModItems.ELEMENT115_INGOT.get())
                .define('P', Blocks.OAK_PLANKS)
                .define('C', Blocks.CRAFTING_TABLE)
                .unlockedBy("has_element115_ingot", has(ModItems.ELEMENT115_INGOT.get())).save(recipeOutput);

        /** Crystals */
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELEMENT115_CRYSTAL.get())
                .pattern(" E ")
                .pattern("EDE")
                .pattern(" E ")
                .define('E', ModItems.ELEMENT115_INGOT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_element115_ingot", has(ModItems.ELEMENT115_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASIC_PERK_CRYSTAL.get())
                .pattern("GDG")
                .pattern("DCD")
                .pattern("GDG")
                .define('D', Items.DIAMOND)
                .define('G', Tags.Items.GLASS_BLOCKS)
                .define('C', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_crystal", has(ModItems.ELEMENT115_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADVANCED_PERK_CRYSTAL.get())
                .pattern("ENE")
                .pattern("QCQ")
                .pattern("ENE")
                .define('E', ModItems.ELEMENT115_INGOT.get())
                .define('N', Items.NETHERITE_INGOT)
                .define('Q', Items.QUARTZ)
                .define('C', ModItems.BASIC_PERK_CRYSTAL.get())
                .unlockedBy("has_basic_perk_crystal", has(ModItems.BASIC_PERK_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUPER_PERK_CRYSTAL.get())
                .pattern("ENE")
                .pattern("QCQ")
                .pattern("ENE")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('N', Items.DRAGON_BREATH)
                .define('Q', Items.NETHER_STAR)
                .define('C', ModItems.ADVANCED_PERK_CRYSTAL.get())
                .unlockedBy("has_advanced_perk_crystal", has(ModItems.ADVANCED_PERK_CRYSTAL.get())).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JUGGERNOG_CRYSTAL.get())
                .pattern("DRD")
                .pattern("EXE")
                .pattern("DCD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('R', Items.RED_DYE)
                .define('D', Items.DIAMOND)
                .define('C', Items.NETHERITE_CHESTPLATE)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPEEDCOLA_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DSD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.LIME_DYE)
                .define('D', Items.DIAMOND)
                .define('S', Items.SUGAR)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAMINUP_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DPD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.ORANGE_DYE)
                .define('D', Items.DIAMOND)
                .define('P', Items.NETHERITE_PICKAXE)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VICTORIOUSTORTOISE_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DAD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.LIME_DYE)
                .define('D', Items.DIAMOND)
                .define('A', Items.ENCHANTED_GOLDEN_APPLE)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DOUBLETAP_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DND")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.ORANGE_DYE)
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_SWORD)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PHDFLOPPER_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DFD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.PURPLE_DYE)
                .define('D', Items.DIAMOND)
                .define('F', Items.FIRE_CHARGE)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.QUICKREVIVE_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DFD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.LIGHT_BLUE_DYE)
                .define('D', Items.DIAMOND)
                .define('F', Items.GHAST_TEAR)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WHOSWHO_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DFD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.CYAN_DYE)
                .define('D', Items.DIAMOND)
                .define('F', Blocks.LAPIS_BLOCK)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WINTERSWAIL_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DFD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.LIGHT_BLUE_DYE)
                .define('D', Items.DIAMOND)
                .define('F', Items.RABBIT_FOOT)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEATHPERCEPTION_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DFD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.BROWN_DYE)
                .define('D', Items.DIAMOND)
                .define('F', Items.GOLDEN_CARROT)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEADSHOT_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DFD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.GRAY_DYE)
                .define('D', Items.DIAMOND)
                .define('F', Items.BLAZE_ROD)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STRONGHOLD_CRYSTAL.get())
                .pattern("DGD")
                .pattern("EXE")
                .pattern("DFD")
                .define('E', ModBlocks.ELEMENT115_BLOCK.get())
                .define('G', Items.BROWN_DYE)
                .define('D', Items.DIAMOND)
                .define('F', Blocks.EMERALD_BLOCK)
                .define('X', ModItems.ELEMENT115_CRYSTAL.get())
                .unlockedBy("has_element115_block", has(ModBlocks.ELEMENT115_BLOCK)).save(recipeOutput);


        /** Perk Recipes */
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JUGGERNOG_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.JUGGERNOG_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_juggernog_crystal", has(ModItems.JUGGERNOG_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPEEDCOLA_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.SPEEDCOLA_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_speedcola_crystal", has(ModItems.SPEEDCOLA_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAMINUP_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.STAMINUP_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_staminup_crystal", has(ModItems.STAMINUP_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VICTORIOUSTORTOISE_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.VICTORIOUSTORTOISE_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_victorioustortoise_crystal", has(ModItems.VICTORIOUSTORTOISE_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DOUBLETAP_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.DOUBLETAP_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_doubletap_crystal", has(ModItems.DOUBLETAP_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PHDFLOPPER_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.PHDFLOPPER_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_phdflopper_crystal", has(ModItems.PHDFLOPPER_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.QUICKREVIVE_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.QUICKREVIVE_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_quickrevive_crystal", has(ModItems.QUICKREVIVE_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WHOSWHO_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.WHOSWHO_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_whoswho_crystal", has(ModItems.WHOSWHO_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WINTERSWAIL_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.WINTERSWAIL_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_winterswail_crystal", has(ModItems.WINTERSWAIL_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEATHPERCEPTION_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.DEATHPERCEPTION_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_deathperception_crystal", has(ModItems.DEATHPERCEPTION_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DEADSHOT_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.DEADSHOT_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_deadshot_crystal", has(ModItems.DEADSHOT_CRYSTAL.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STRONGHOLD_PERK.get())
                .pattern("ENE")
                .pattern("CWC")
                .pattern("CCC")
                .define('E', Blocks.EMERALD_BLOCK)
                .define('N', Blocks.NETHERITE_BLOCK)
                .define('C', ModItems.STRONGHOLD_CRYSTAL)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy("has_strognghold_crystal", has(ModItems.STRONGHOLD_CRYSTAL.get())).save(recipeOutput);


        /** Recipe for Perk Holder */
        SpecialRecipeBuilder.special(PerkStorageRecipe::new)
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "perk_storage"));

        /** Recipe for Perk Holder (Removing perks) */
        SpecialRecipeBuilder.special(RemovePerkRecipe::new)
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "remove_perk"));
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

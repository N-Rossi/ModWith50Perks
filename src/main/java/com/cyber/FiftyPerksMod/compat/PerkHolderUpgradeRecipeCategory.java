package com.cyber.FiftyPerksMod.compat;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import com.cyber.FiftyPerksMod.item.ModItems;
import com.cyber.FiftyPerksMod.recipe.ModRecipes;
import com.cyber.FiftyPerksMod.recipe.PerkHolderUpgradeRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;

public class PerkHolderUpgradeRecipeCategory implements IRecipeCategory<PerkHolderUpgradeRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "perk_holder_upgrade");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft",
            "textures/gui/container/crafting_table.png");

    public static final RecipeType<PerkHolderUpgradeRecipe> PERK_HOLDER_UPGRADE_RECIPE_TYPE =
            new RecipeType<>(UID, PerkHolderUpgradeRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PerkHolderUpgradeRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,29 ,16, 116, 54);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.CRAFTING_TABLE));
    }

    @Override
    public RecipeType<PerkHolderUpgradeRecipe> getRecipeType() {
        return PERK_HOLDER_UPGRADE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.minecraft.crafting_table");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PerkHolderUpgradeRecipe recipe, IFocusGroup focuses) {
        // Example for 2 inputs (left and center), one output
        builder.addSlot(RecipeIngredientRole.INPUT, 30, 17)
                .addIngredients(recipe.getIngredients().get(0));

        if (recipe.getIngredients().size() > 1) {
            builder.addSlot(RecipeIngredientRole.INPUT, 48, 17)
                    .addIngredients(recipe.getIngredients().get(1));
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 35)
                .addItemStack(recipe.getResultItem(null));
    }
}

package com.cyber.FiftyPerksMod.compat;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import com.cyber.FiftyPerksMod.recipe.UpgradeStationRecipe;
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

import javax.annotation.Nullable;

public class UpgradeStationRecipeCategory implements IRecipeCategory<UpgradeStationRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, "upgrade_station");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID,
            "textures/gui/upgrade_station/upgrade_station_gui.png");

    public static final RecipeType<UpgradeStationRecipe> UPGRADE_STATION_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, UpgradeStationRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public UpgradeStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,25 ,10, 120, 60);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.UPGRADE_STATION));
    }

    @Override
    public RecipeType<UpgradeStationRecipe> getRecipeType() {
        return UPGRADE_STATION_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.fiftyperksmod.upgrade_station");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, UpgradeStationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 6, 24).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 24).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 24).addItemStack(recipe.getResultItem(null));
    }
}

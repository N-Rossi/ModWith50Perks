package com.cyber.FiftyPerksMod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record UpgradeStationRecipe(Ingredient inputItem1, Ingredient inputItem2, ItemStack output) implements Recipe<UpgradeStationRecipeInput> {
    // inputItem & output ==> Read From JSON File!
    // GrowthChamberRecipeInput --> INVENTORY of the Block Entity

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem1);
        list.add(inputItem2);
        return list;
    }

    @Override
    public boolean matches(UpgradeStationRecipeInput upgradeStationRecipeInput, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return inputItem1.test(upgradeStationRecipeInput.getItem(0)) && inputItem2.test(upgradeStationRecipeInput.getItem(1));
    }

    @Override
    public ItemStack assemble(UpgradeStationRecipeInput upgradeStationRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.UPGRADE_STATION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.UPGRADE_STATION_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<UpgradeStationRecipe> {
        public static final MapCodec<UpgradeStationRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient1").forGetter(UpgradeStationRecipe::inputItem1),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient2").forGetter(UpgradeStationRecipe::inputItem2),
                ItemStack.CODEC.fieldOf("result").forGetter(UpgradeStationRecipe::output)
        ).apply(inst, UpgradeStationRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, UpgradeStationRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, UpgradeStationRecipe::inputItem1,
                        Ingredient.CONTENTS_STREAM_CODEC, UpgradeStationRecipe::inputItem2,
                        ItemStack.STREAM_CODEC, UpgradeStationRecipe::output,
                        UpgradeStationRecipe::new
                );

        @Override
        public MapCodec<UpgradeStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, UpgradeStationRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}

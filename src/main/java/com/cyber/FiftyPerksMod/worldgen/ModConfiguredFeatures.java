package com.cyber.FiftyPerksMod.worldgen;

import com.cyber.FiftyPerksMod.FiftyPerksMod;
import com.cyber.FiftyPerksMod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
//    CF -> PF -> BM

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ELEMENT115_ORE_KEY = registerKey("element115_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ELEMENT115_ORE_KEY = registerKey("nether_element115_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
//        Ore Gen
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState> overworldELEMENT115Ores = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ELEMENT115_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ELEMENT115_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_ELEMENT115_ORE_KEY, Feature.ORE, new OreConfiguration(overworldELEMENT115Ores, 9));

        register(context, NETHER_ELEMENT115_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.NETHER_ELEMENT115_ORE.get().defaultBlockState(), 9));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FiftyPerksMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

package net.superfeda.no_dimension.mixin;

import com.mojang.serialization.Lifecycle;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.MappedRegistry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldDimensions;

import net.superfeda.no_dimension.Constants;
import net.superfeda.no_dimension.NoDimensionsConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import java.util.Map;

@Mixin(WorldDimensions.class)
public class DimensionOptionsRegistryHolderMixin {

    @ModifyVariable(
            method = "<init>(Lnet/minecraft/core/Registry;)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    private static Registry<LevelStem> filterDimensionsRegistry(Registry<LevelStem> registry) {
        MappedRegistry<LevelStem> filteredRegistry = new MappedRegistry<>(registry.key(), Lifecycle.stable());
        List<? extends String> blacklistedDimensions = NoDimensionsConfig.TARGET_DIMENSIONS.get();

        for (Map.Entry<ResourceKey<LevelStem>, LevelStem> entry : registry.entrySet()) {
            ResourceKey<LevelStem> key = entry.getKey();
            String dimID = key.location().toString();

            if (blacklistedDimensions.contains(key.location().toString())) {
                Constants.LOG.info("Ignored registration of dimension: " + dimID);
                continue;
            }

            filteredRegistry.register(key, entry.getValue(), Lifecycle.stable());
        }

        return filteredRegistry;
    }
}

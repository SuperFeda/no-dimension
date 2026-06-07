package net.superfeda.no_dimension;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;

import net.fabricmc.api.ModInitializer;

import net.minecraftforge.fml.config.ModConfig;

public class NoDimension implements ModInitializer {
    
    @Override
    public void onInitialize() {
        NoDimensionsConfig.init();
        ForgeConfigRegistry.INSTANCE.register(
                Constants.MOD_ID,
                ModConfig.Type.COMMON,
                NoDimensionsConfig.COMMON_SPEC
        );
        CommonClass.init();
    }
}

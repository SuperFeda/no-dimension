package net.superfeda.no_dimension;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class NoDimension {
    
    public NoDimension() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NoDimensionsConfig.COMMON_SPEC);
        CommonClass.init();
    }
}
package net.superfeda.no_dimension;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class NoDimensionsConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> TARGET_DIMENSIONS;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Settings for removing dimensions from the game.").push("general");

        TARGET_DIMENSIONS = builder
                .comment("A list of dimension identifiers that will be completely removed from the registry when the world starts.",
                        "For example: [\"minecraft:the_nether\", \"minecraft:the_end\"]")
                .defineListAllowEmpty(
                        Arrays.asList("target_dimension"),
                        () -> List.of(),
                        obj -> obj instanceof String
                );

        builder.pop();
        COMMON_SPEC = builder.build();
    }

    // for fabric
    public static void init() {}
}

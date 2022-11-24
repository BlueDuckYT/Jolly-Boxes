package blueduck.jollyboxes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JollyBoxesConfig {


    public ConfigHelper.ConfigValueListener<Double> PRESENT_CHANCE;
    public ConfigHelper.ConfigValueListener<Integer> MINIMUM_PRESENTS;
    public ConfigHelper.ConfigValueListener<Integer> MAXIMUM_PRESENTS;
    public ConfigHelper.ConfigValueListener<Boolean> ONLY_IN_SNOWY_BIOMES;
    public ConfigHelper.ConfigValueListener<Boolean> ONLY_IN_DECEMBER;

    public ConfigHelper.ConfigValueListener<Boolean> PARTY_HORN_TOGGLE;
    public ConfigHelper.ConfigValueListener<Boolean> PARTY_HORN_DECEMBER_ONLY;



    public JollyBoxesConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        builder.push("General");
        this.PRESENT_CHANCE= subscriber.subscribe(builder
                .comment("Chance of Jolly Boxes to spawn when you sleep (Set to 0 to disable spawning)")
                .defineInRange("present_chance", 1.0, 0.0, 1.0));
        this.MINIMUM_PRESENTS= subscriber.subscribe(builder
                .comment("Minimum amount of presents that can spawn")
                .defineInRange("present_minimum", 1, 0, 32));
        this.MAXIMUM_PRESENTS= subscriber.subscribe(builder
                .comment("Maximum amount of presents that can spawn")
                .defineInRange("present_maximum", 5, 0, 32));
        this.ONLY_IN_SNOWY_BIOMES= subscriber.subscribe(builder
                .comment("Should Presents only spawn in snowy biomes?")
                .define("only_in_snowy_biomes", false, o -> o instanceof Boolean));
        this.ONLY_IN_DECEMBER= subscriber.subscribe(builder
                .comment("Should Presents only spawn during December?")
                .define("only_in_december", false, o -> o instanceof Boolean));
        this.PARTY_HORN_TOGGLE= subscriber.subscribe(builder
                .comment("Replace the Sleigh Bell sound with a Party Horn sound?")
                .define("party_horn_replace_bells", false, o -> o instanceof Boolean));
        this.PARTY_HORN_DECEMBER_ONLY= subscriber.subscribe(builder
                .comment("Replace the Sleigh Bell sound with a Party Horn sound (December excluded)?")
                .define("party_horn_december", false, o -> o instanceof Boolean));

        builder.pop();
    }

}

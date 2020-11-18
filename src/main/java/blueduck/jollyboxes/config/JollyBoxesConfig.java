package blueduck.jollyboxes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JollyBoxesConfig {


    public ConfigHelper.ConfigValueListener<Double> PRESENT_CHANCE;
    public ConfigHelper.ConfigValueListener<Integer> MINIMUM_PRESENTS;
    public ConfigHelper.ConfigValueListener<Integer> MAXIMUM_PRESENTS;


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
        builder.pop();
    }

}

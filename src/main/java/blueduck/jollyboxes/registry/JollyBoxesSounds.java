package blueduck.jollyboxes.registry;

import blueduck.jollyboxes.JollyBoxesMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JollyBoxesSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JollyBoxesMod.MODID);

    public static final RegistryObject<SoundEvent> SLEIGH_BELLS = SOUNDS.register("sleigh_bells", () -> new SoundEvent(new ResourceLocation("jolly_boxes", "ambient.bells")));
    public static final RegistryObject<SoundEvent> PARTY_HORN = SOUNDS.register("party_horn", () -> new SoundEvent(new ResourceLocation("jolly_boxes", "ambient.horn")));


    public static void init() {
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}

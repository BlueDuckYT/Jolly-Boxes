package blueduck.jollyboxes;

import blueduck.jollyboxes.config.ConfigHelper;
import blueduck.jollyboxes.config.JollyBoxesConfig;
import blueduck.jollyboxes.registry.JollyBoxesBlocks;
import blueduck.jollyboxes.registry.JollyBoxesSounds;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("jolly_boxes")
public class JollyBoxesMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static JollyBoxesConfig CONFIG;

    public static String MODID = "jolly_boxes";

    public JollyBoxesMod() {

        CONFIG = ConfigHelper.register(ModConfig.Type.COMMON, JollyBoxesConfig::new);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.addListener(this::onPlayerWakeUp);

        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);

        JollyBoxesBlocks.init();
        JollyBoxesSounds.init();
        //JollyBoxesLootModifier.init();
        //addBoxTables();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onPlayerWakeUp(final PlayerWakeUpEvent event) {
        if ((!CONFIG.ONLY_IN_SNOWY_BIOMES.get() || (CONFIG.ONLY_IN_SNOWY_BIOMES.get() && event.getPlayer().getLevel().getBiome(event.getPlayer().getOnPos()).value().getBaseTemperature() <= 0.15) && (!CONFIG.ONLY_IN_DECEMBER.get() || (CONFIG.ONLY_IN_DECEMBER.get() && isDecember())))) {
            event.getPlayer().getPersistentData().putBoolean("slept", true);
        }
    }

    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.getPersistentData().getBoolean("slept") && event.player.getLevel().isDay()) {
            event.player.getPersistentData().putBoolean("slept", false);
            BlockPos pos = event.player.getOnPos();
            if (!event.player.getLevel().isClientSide() && event.player.getLevel().getRandom().nextDouble() < CONFIG.PRESENT_CHANCE.get()) {
                for (int i = 0; i < event.player.getLevel().getRandom().nextInt(CONFIG.MAXIMUM_PRESENTS.get() + CONFIG.MINIMUM_PRESENTS.get()) + CONFIG.MINIMUM_PRESENTS.get(); i++) {
                    BlockPos pos2 = new BlockPos((pos.getX() + (event.player.getLevel().getRandom().nextDouble() * 32) - 16), pos.getY() + 100, (double) (pos.getZ() + (event.player.getLevel().getRandom().nextDouble() * 32) - 16));
                    if (getGroundPos(pos2, event.player.getLevel()) != null) {
                        event.player.getLevel().setBlock(getGroundPos(pos2, event.player.getLevel()), JollyBoxesBlocks.SMALL_JOLLY_BOX.get().defaultBlockState(), 3);
                    }
                }
                event.player.playNotifySound(getSound().get(), SoundSource.AMBIENT, 1F, 1F);
            }
        }
    }

    public static RegistryObject<SoundEvent> getSound() {
        if (CONFIG.PARTY_HORN_TOGGLE.get()) {
            return JollyBoxesSounds.PARTY_HORN;
        }
        else if (CONFIG.PARTY_HORN_DECEMBER_ONLY.get()) {
            if (isDecember()) {
                return JollyBoxesSounds.SLEIGH_BELLS;
            }
            else {
                return JollyBoxesSounds.PARTY_HORN;
            }
        }
        else {
            return JollyBoxesSounds.SLEIGH_BELLS;
        }
    }

    public BlockPos getGroundPos(BlockPos pos, Level world) {
        for (int i = pos.getY(); i > 0; i--) {
            if (isValidPos(pos.below(pos.getY() - i), world) && (world.getBlockState(pos).equals(Blocks.AIR.defaultBlockState()) || world.getBlockState(pos).equals(Blocks.SNOW.defaultBlockState()))) {
                return pos.below(pos.getY() - i);
            }
        }
        return null;
    }

    public boolean isValidPos(BlockPos pos, Level world) {
        return ((world.getBlockState(pos).equals(Blocks.AIR.defaultBlockState()) || world.getBlockState(pos).equals(Blocks.SNOW.defaultBlockState())) && world.getBlockState(pos.below()).canOcclude());
    }

    public static boolean isDecember() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.get(ChronoField.MONTH_OF_YEAR);
        return month == 12;
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client

        ItemBlockRenderTypes.setRenderLayer(JollyBoxesBlocks.SMALL_JOLLY_BOX.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(JollyBoxesBlocks.MEDIUM_JOLLY_BOX.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(JollyBoxesBlocks.LARGE_JOLLY_BOX.get(), RenderType.cutoutMipped());

    }




    // You can use SubscribeEvent and let the Event Bus discover methods to call


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)


}

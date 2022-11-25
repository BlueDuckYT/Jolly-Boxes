package blueduck.jollyboxes.registry;

import blueduck.jollyboxes.JollyBoxesMod;
import blueduck.jollyboxes.blocks.JollyBoxBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JollyBoxesBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JollyBoxesMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JollyBoxesMod.MODID);

    public static final RegistryObject<Block> SMALL_JOLLY_BOX = BLOCKS.register("small_box", () -> new JollyBoxBlock(Block.Properties.of(Material.WOOL, MaterialColor.COLOR_RED).sound(SoundType.WOOL).strength(.6F, .4F).noOcclusion()));
    public static final RegistryObject<Item> SMALL_JOLLY_BOX_ITEM = ITEMS.register("small_box", () -> new BlockItem(SMALL_JOLLY_BOX.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> MEDIUM_JOLLY_BOX = BLOCKS.register("medium_box", () -> new JollyBoxBlock(Block.Properties.of(Material.WOOL, MaterialColor.COLOR_RED).sound(SoundType.WOOL).strength(.6F, .4F).noOcclusion()));
    public static final RegistryObject<Item> MEDIUM_JOLLY_BOX_ITEM = ITEMS.register("medium_box", () -> new BlockItem(MEDIUM_JOLLY_BOX.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Block> LARGE_JOLLY_BOX = BLOCKS.register("large_box", () -> new JollyBoxBlock(Block.Properties.of(Material.WOOL, MaterialColor.COLOR_RED).sound(SoundType.WOOL).strength(.6F, .4F).noOcclusion()));
    public static final RegistryObject<Item> LARGE_JOLLY_BOX_ITEM = ITEMS.register("large_box", () -> new BlockItem(LARGE_JOLLY_BOX.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}

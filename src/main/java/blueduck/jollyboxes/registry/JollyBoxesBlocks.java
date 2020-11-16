package blueduck.jollyboxes.registry;

import blueduck.jollyboxes.JollyBoxesMod;
import blueduck.jollyboxes.blocks.JollyBoxBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JollyBoxesBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JollyBoxesMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JollyBoxesMod.MODID);

    public static final RegistryObject<Block> SMALL_JOLLY_BOX = BLOCKS.register("small_box", () -> new JollyBoxBlock(Block.Properties.create(Material.WOOL, MaterialColor.RED).sound(SoundType.HYPHAE).hardnessAndResistance(.6F, .4F).notSolid().harvestLevel(0)));
    public static final RegistryObject<Item> SMALL_JOLLY_BOX_ITEM = ITEMS.register("small_box", () -> new BlockItem(SMALL_JOLLY_BOX.get(), new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Block> MEDIUM_JOLLY_BOX = BLOCKS.register("medium_box", () -> new JollyBoxBlock(Block.Properties.create(Material.WOOL, MaterialColor.RED).sound(SoundType.HYPHAE).hardnessAndResistance(.6F, .4F).notSolid().harvestLevel(0)));
    public static final RegistryObject<Item> MEDIUM_JOLLY_BOX_ITEM = ITEMS.register("medium_box", () -> new BlockItem(MEDIUM_JOLLY_BOX.get(), new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Block> LARGE_JOLLY_BOX = BLOCKS.register("large_box", () -> new JollyBoxBlock(Block.Properties.create(Material.WOOL, MaterialColor.RED).sound(SoundType.HYPHAE).hardnessAndResistance(.6F, .4F).notSolid().harvestLevel(0)));
    public static final RegistryObject<Item> LARGE_JOLLY_BOX_ITEM = ITEMS.register("large_box", () -> new BlockItem(LARGE_JOLLY_BOX.get(), new Item.Properties().group(ItemGroup.MISC)));

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}

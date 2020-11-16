package blueduck.jollyboxes.util;


import blueduck.jollyboxes.JollyBoxesMod;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class JollyBoxesLootModifier extends LootModifier {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, JollyBoxesMod.MODID);
    public static final RegistryObject<Serializer> MY_GLM = GLM.register("box_loot", JollyBoxesLootModifier.Serializer::new);

    public JollyBoxesLootModifier(ILootCondition[] c) { super(c); }

    public static void init() {
        GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        return null;
    }
    // override 'doApply'

    static class Serializer extends GlobalLootModifierSerializer<JollyBoxesLootModifier> {
        @Override
        public JollyBoxesLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return null;
        }

        @Override
        public JsonObject write(JollyBoxesLootModifier instance) {
            return null;
        }
        //override read with new MyGLM(conditions);
        // override write with makeConditions(instance.conditions);
    }


    private static LootEntry getInjectEntry(ResourceLocation location, int weight, int quality) {
        return TableLootEntry.builder(location).weight(weight).quality(quality).build();
    }



    private static void addEntry(LootPool pool, LootEntry entry) throws IllegalAccessException {
        List<LootEntry> lootEntries = (List<LootEntry>) ObfuscationReflectionHelper.findField(LootPool.class, "field_186453_a").get(pool);
        if (lootEntries.stream().anyMatch(e -> e == entry)) {
            throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
        }
        lootEntries.add(entry);
    }
}
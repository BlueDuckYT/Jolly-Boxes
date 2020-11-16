package blueduck.jollyboxes.util;

import blueduck.jollyboxes.JollyBoxesMod;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;

import static blueduck.jollyboxes.JollyBoxesMod.MODID;

public class LootUtil {

    public static ArrayList<ResourceLocation> SMALL_LOOT_POOLS = new ArrayList<ResourceLocation>();
    public static ArrayList<ResourceLocation> MEDIUM_LOOT_POOLS = new ArrayList<ResourceLocation>();
    public static ArrayList<ResourceLocation> LARGE_LOOT_POOLS = new ArrayList<ResourceLocation>();

    public static synchronized void AddSmallLootPool(ResourceLocation table) {
        SMALL_LOOT_POOLS.add(table);
    }
    public static synchronized void AddMediumLootPool(ResourceLocation table) {
        MEDIUM_LOOT_POOLS.add(table);
    }
    public static synchronized void AddLargeLootPool(ResourceLocation table) {
        LARGE_LOOT_POOLS.add(table);
    }

    @Mod.EventBusSubscriber(modid = "jolly_boxes")
    public static class LootEvents {

        @SubscribeEvent
        public static void onLootLoad(LootTableLoadEvent event) throws IllegalAccessException {


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
}

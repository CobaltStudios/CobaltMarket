package org.cobaltstudios.cobaltmarket.utils;

import org.cobaltstudios.cobaltmarket.CobaltMarket;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.custom.CustomInventory;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.world.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iTidez on 10/25/15.
 */
public class SerializeUtils {
    public static String list(List<String> list) {

        if(list.isEmpty()) return "";

        StringBuilder serialized = new StringBuilder();
        for(String s : list) serialized.append(s + ",");
        serialized.deleteCharAt(serialized.length() - 1);

        return serialized.toString();

    }

    public static String messages(List<String> messages) {

        if(messages.isEmpty()) return "";

        StringBuilder serialized = new StringBuilder();
        for(String s : messages) serialized.append(s + "-;;");
        for(int i = 1; i <= 3; i++) serialized.deleteCharAt(serialized.length() - 1);

        return serialized.toString();

    }

    public static String location(String world, double x, double y, double z, double yaw, double pitch) {
        return world + ":" + x + ":" + y + ":" + z + ":" + yaw + ":" + pitch;
    }

    public static String members(HashMap<String, Double> members) {

        if(members.isEmpty()) return "";

        StringBuilder serialized = new StringBuilder();

        for(Map.Entry<String, Double> e : members.entrySet()) {
            serialized.append(e.getKey() + ":" + String.valueOf(e.getValue()));
            serialized.append("-;;");
        }

        for(int i = 1; i <= 3; i++) serialized.deleteCharAt(serialized.length() - 1);

        return serialized.toString();

    }

    public static String settings(HashMap<String, String> settings) {

        if(settings.isEmpty()) return "";

        StringBuilder serialized = new StringBuilder();

        for(Map.Entry<String, String> e : settings.entrySet()) {
            serialized.append(e.getKey() + ":" + e.getValue());
            serialized.append("-;;");
        }

        for(int i = 1; i <= 3; i++) serialized.deleteCharAt(serialized.length() - 1);

        return serialized.toString();

    }

    public static String location(Location location) {
        String sep = "-;;";
        StringBuilder serialized = new StringBuilder();
        serialized.append(CobaltMarket.getInstance().getGame().getServer().getDefaultWorld().get().getWorldName());
        serialized.append(sep);
        serialized.append(location.getBlockX());
        serialized.append(sep);
        serialized.append(location.getBlockY());
        serialized.append(sep);
        serialized.append(location.getBlockZ());

        for(int i = 1; i<=3; i++) serialized.deleteCharAt(serialized.length() - 1);

        return serialized.toString();
    }

    public static String inventory(CustomInventory inventory) {
        String sep = "-;;";
        StringBuilder serialized = new StringBuilder();
        serialized.append(inventory.getName());
        serialized.append(sep);
        for(int i = 0; i < inventory.size(); i++) {
            ItemStack is = inventory.poll(SlotIndex.of(i)).get();
            if(!(is == null)) {
                serialized.append(is.getItem().getId()+"-;;;"+is.getQuantity());
                serialized.append(sep);
            }
        }
        return serialized.toString();
    }
}

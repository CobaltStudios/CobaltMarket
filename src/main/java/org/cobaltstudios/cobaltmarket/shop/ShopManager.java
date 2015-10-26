package org.cobaltstudios.cobaltmarket.shop;

import org.cobaltstudios.cobaltmarket.utils.DatabaseUtils;
import org.cobaltstudios.cobaltmarket.utils.SerializeUtils;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventories;
import org.spongepowered.api.item.inventory.custom.CustomInventory;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by iTidez on 10/25/15.
 */
public class ShopManager {
    private static Game game;
    private static HashMap<UUID, Object> stores = new HashMap<>();

    public ShopManager(Game game) {
        this.game = game;
    }

    public void createShop(Player owner, Location loc, Direction direction, double price, int quantity, ShopTypes type) {
        switch(type) {
            case ADMIN:
                AdminShop adminShop = new AdminShop(owner.getUniqueId(), price, loc, direction);
                addShop(adminShop.getID(), adminShop);
                DatabaseUtils.execute("INSERT INTO stores WHERE('"+adminShop.getID()+"', '"+owner.getUniqueId()+"', '"+price+"', '"+SerializeUtils.inventory(adminShop.getShopInventory())+"', '"+adminShop.getDirection().toString()+"', '0')");
                break;
            case PLAYER:
                PlayerShop playerShop = new PlayerShop(owner.getUniqueId(), price, quantity, loc, direction);
                addShop(playerShop.getID(), playerShop);
                DatabaseUtils.execute("INSERT INTO stores WHERE('"+playerShop.getID()+"', '"+owner.getUniqueId()+"', '"+price+"', '"+SerializeUtils.inventory(playerShop.getShopInventory())+"', '"+playerShop.getDirection().toString()+"', '1')");
                break;
        }
    }

    public void deleteShop(Shop shop) {
        DatabaseUtils.execute("DELETE FROM stores WHERE('"+shop.getID()+"')");
        stores.remove(shop.getID());
    }

    public void updateShop(Shop shop) {
        DatabaseUtils.execute("UPDATE stores SET cost = '"+shop.getPrice()+"', inventory = '"+shop.getShopInventory()+"' WHERE uuid = '"+shop.getID()+"'");
        stores.replace(shop.getID(), shop);
    }

    public void addShop(UUID id, Object store) {
        stores.put(id, store);
    }

    public ArrayList<Object> getShops() {
        ArrayList<Object> result = new ArrayList<>();
        for(Object obj : stores.values()) {
            result.add(obj);
        }
        return result;
    }

    public ArrayList<Object> getShopsByType(ShopTypes type) {
        ArrayList<Object> result = new ArrayList<>();
        switch(type) {
            case ADMIN:
                for(Object obj: stores.values()) {
                    if(obj instanceof AdminShop) result.add(obj);
                }
                break;
            case PLAYER:
                for(Object obj: stores.values()) {
                    if(obj instanceof PlayerShop) result.add(obj);
                }
                break;
        }
        return result;
    }
}

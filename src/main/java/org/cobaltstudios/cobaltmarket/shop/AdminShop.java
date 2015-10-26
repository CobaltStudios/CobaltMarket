/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cobaltstudios.cobaltmarket.shop;

import java.util.UUID;

import org.cobaltstudios.cobaltmarket.utils.SerializeUtils;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.custom.CustomInventory;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;

/**
 *
 * @author iTidez
 */
public class AdminShop extends Shop {
    
    public AdminShop(CustomInventory item, double price, UUID owner, Location location, Direction direction) {
        setShopInventory(item);
        setPrice(price);
        setOwner(owner);
        setLocation(location);
        setDirection(direction);
        setID(UUID.fromString(SerializeUtils.location(location)));
        init();
    }

    public AdminShop(UUID id, CustomInventory item, double price, UUID owner, Location location, Direction direction) {
        setShopInventory(item);
        setPrice(price);
        setOwner(owner);
        setLocation(location);
        setDirection(direction);
        setID(id);
        init();
    }
    
    private void init() { setPermission("admin"); }
}

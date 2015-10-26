/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cobaltstudios.cobaltmarket.shop;

import java.util.UUID;

import org.spongepowered.api.event.entity.SpawnEntityEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.custom.CustomInventory;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;

/**
 *
 * @author iTidez
 */
public abstract class Shop {
    private UUID id;
    private CustomInventory shopInventory;
    private double price;
    private UUID owner;
    private String permissionString = "cobalt.market.shop";
    private Location location;
    private Direction direction;
    
    Direction getDirection() { return direction; }

    UUID getID() { return id; }

    double getPrice() {
        return price;
    }
    
    CustomInventory getShopInventory() {
        return shopInventory;
    }

    Location getLocation() { return location; }
    
    UUID getOwner() {
        return owner;
    }
    
    String getPermission() {
        return permissionString;
    }

    void setDirection(Direction direction) { this.direction = direction; }

    void setID(UUID id) { this.id = id; }
    
    void setPrice(double price) {
        this.price = price;
    }
    
    void setShopInventory(CustomInventory shopIInventory) {
        this.shopInventory = shopInventory;
    }

    void setLocation(Location location) { this.location = location; }
    
    void setOwner(UUID owner) {
        this.owner = owner;
    }
    
    void setPermission(String permissionString) {
        this.permissionString = this.permissionString+"."+permissionString;
    }
}

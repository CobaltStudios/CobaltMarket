/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cobaltstudios.cobaltmarket.shop;

import java.util.UUID;
import org.spongepowered.api.item.inventory.ItemStack;

/**
 *
 * @author iTidez
 */
public abstract class Shop {
    private ItemStack shopItem;
    private double price;
    private UUID owner;
    private String permissionString = "cobalt.market.shop";
    
    double getPrice() {
        return price;
    }
    
    ItemStack getItemSold() {
        return shopItem;
    }
    
    UUID getOwner() {
        return owner;
    }
    
    String getPermission() {
        return permissionString;
    }
    
    void setPrice(double price) {
        this.price = price;
    }
    
    void setItemSold(ItemStack shopItem) {
        this.shopItem = shopItem;
    }
    
    void setOwner(UUID owner) {
        this.owner = owner;
    }
    
    void setPermission(String permissionString) {
        this.permissionString = this.permissionString+"."+permissionString;
    }
}

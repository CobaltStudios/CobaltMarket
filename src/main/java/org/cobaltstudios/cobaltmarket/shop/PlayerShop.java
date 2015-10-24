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
public class PlayerShop extends Shop {
    private int itemStock;
    
    public PlayerShop(ItemStack item, double price, UUID owner, int stock) {
        setItemSold(item);
        setPrice(price);
        setOwner(owner);
        this.itemStock = stock;
    }
    
    public void setStock(int stock) {
        this.itemStock = stock;
    }
    
    public int getStock() {
        return itemStock;
    }
}

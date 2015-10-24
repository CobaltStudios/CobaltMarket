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
public class AdminShop extends Shop {
    
    public AdminShop(ItemStack item, double price, UUID owner) {
        setItemSold(item);
        setPrice(price);
        setOwner(owner);
        
        init();
    }
    
    private void init() {
        setPermission("admin");
    }
}

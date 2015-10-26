package org.cobaltstudios.cobaltmarket.shop;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;

/**
 * Created by iTidez on 10/25/15.
 */
public class ShopManager {
    private static Game game;

    public ShopManager(Game game) {
        this.game = game;
    }

    public void createShop(Player owner, Location loc, Direction direction, double price, double quantity) {

    }
}

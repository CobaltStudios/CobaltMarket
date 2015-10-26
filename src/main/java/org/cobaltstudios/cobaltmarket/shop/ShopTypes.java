package org.cobaltstudios.cobaltmarket.shop;

/**
 * Created by iTidez on 10/25/15.
 */
public enum ShopTypes {
    ADMIN(0), PLAYER(1);

    private int id;

    private ShopTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

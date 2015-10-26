package org.cobaltstudios.cobaltmarket.events;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;

/**
 * Created by iTidez on 10/25/15.
 */
public class InteractListener {

    @Listener
    public void onEntityInteract(InteractEntityEvent event) {
        Entity entity = event.getTargetEntity();
        if(entity.getType().equals(EntityTypes.ITEM_FRAME)) {
            //if(entity.)
            //event.getCause()
        }
    }
}

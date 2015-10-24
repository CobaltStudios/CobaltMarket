/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cobaltstudios.cobaltmarket;

import com.google.inject.Inject;
import java.io.File;
import java.util.Optional;
import me.creepsterlgc.core.api.CoreAPI;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.DefaultConfig;

/**
 *
 * @author iTidez
 */
@Plugin(id = "cobaltmarket", name = "CobaltMarket", version = "0.1", dependencies = "required-after:Core")
public class CobaltMarket {
    private CoreAPI coreAPI;
    
    @Inject
    @DefaultConfig(sharedRoot = false)
    private File defaultConfig;
    
    @Inject
    private Game game;
    
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Optional<CoreAPI> optional = game.getServiceManager().provide(CoreAPI.class);
        if(optional.isPresent()) coreAPI = optional.get();
    }
    
    @Listener
    public void onServerStop(GameStoppingServerEvent event) {
        
    }
}

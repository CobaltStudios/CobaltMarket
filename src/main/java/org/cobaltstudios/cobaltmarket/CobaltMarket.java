/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cobaltstudios.cobaltmarket;

import com.google.inject.Inject;
import java.io.File;

import me.creepsterlgc.core.api.CoreAPI;
import org.cobaltstudios.cobaltmarket.commands.CommandManager;
import org.cobaltstudios.cobaltmarket.shop.ShopManager;
import org.cobaltstudios.cobaltmarket.utils.DatabaseUtils;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
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
    private static String version;
    
    @Inject
    @DefaultConfig(sharedRoot = false)
    private File defaultConfig;

    private Game game;
    @Inject
    private static Logger logger;

    private static ShopManager shopManager;
    private CommandManager commandManager;
    private static CobaltMarket market;
    
    @Listener
    public void onServerStart(GameStartingServerEvent event) {
        //Optional<CoreAPI> optional = game.getServiceManager().provide(CoreAPI.class);
        //if(optional.isPresent()) coreAPI = optional.get();
        shopManager = new ShopManager(game);
        DatabaseUtils.setup(game);
        commandManager = new CommandManager(game, this);
        market = this;
    }

    public static CobaltMarket getInstance() {
        return market;
    }
    
    @Listener
    public void onServerStop(GameStoppingServerEvent event) {
        market = null;
        shopManager = null;
    }

    @Inject
    public void setGame(Game game) {
        this.game = game;
    }

    private void initDatabase() {

    }

    public Game getGame() {
        return game;
    }

    public static ShopManager getShopManager() {
        return shopManager;
    }

    public static String getVersion() {
        return version;
    }

    public static Logger getLogger() {
        return logger;
    }

}

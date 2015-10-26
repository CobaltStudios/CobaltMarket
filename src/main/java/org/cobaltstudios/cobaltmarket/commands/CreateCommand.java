package org.cobaltstudios.cobaltmarket.commands;

import org.cobaltstudios.cobaltmarket.CobaltMarket;
import org.cobaltstudios.cobaltmarket.shop.ShopTypes;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

/**
 * Created by iTidez on 10/25/15.
 */
public class CreateCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        boolean canCreate = args.getOne("create").isPresent();
        if(canCreate) {
            boolean infoPresent = (args.hasAny("item") && args.hasAny("quantity") && args.hasAny("price"));
            boolean isAdmin = args.hasAny("admin");
            if(!(src instanceof Player)) {
                canCreate = false;
            }
            if(!infoPresent) {
                canCreate = false;
            }

            if(canCreate) {
                Player owner = (Player)src;
                int itemId = Integer.parseInt(args.<String>getOne("item").get());
                double price = Double.parseDouble(args.<String>getOne("price").get());
                int quantity = Integer.parseInt(args.<String>getOne("quantity").get());
                ShopTypes type = ShopTypes.ADMIN;
                if(!isAdmin) type = ShopTypes.PLAYER;
                CobaltMarket.getShopManager().createShop(owner, owner.get(Keys.TARGETED_LOCATION).get(), Direction.getClosestHorizonal(owner.getRotation()), price, quantity, type);
            }
        }
        if(canCreate) return CommandResult.success();
        else CommandResult.empty();
        return CommandResult.empty();
    }
}

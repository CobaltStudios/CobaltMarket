package org.cobaltstudios.cobaltmarket.commands;

import org.cobaltstudios.cobaltmarket.CobaltMarket;
import org.spongepowered.api.service.pagination.PaginationBuilder;
import org.spongepowered.api.service.pagination.PaginationService;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

import java.util.ArrayList;

/**
 * Created by iTidez on 10/25/15.
 */
public class InfoCommand implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        PaginationService paginationService = CobaltMarket.getGame().getServiceManager().provide(PaginationService.class).get();
        PaginationBuilder builder = paginationService.builder();
        builder.title(Texts.of(TextColors.DARK_AQUA, "CobaltMarket - V"+CobaltMarket.getVersion()))
            .contents(
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "Author - Tyler Ludka (SirTidez)"),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "GitHub - https://github.com/CobaltStudios/CobaltMarket"),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "Post - https://forums.spongepowered.org/t/cobaltmarket/9681"),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, ""),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "Commands"),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "- /market info - (/market) Shows this help window"),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "- /market create [admin] (item_id) (quantity) (price)"),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "Starts the creation of a new store. Gives you a feather to hold" +
                                                                "that allows you to control the newly placed shop. ** This command automatically" +
                                                                "spawns the store on whichever block is currently targeted! **"),
                    Texts.of(TextColors.BLUE, TextStyles.ITALIC, "- /market delete - Deletes the targeted shop from the world")
            )
            .header(Texts.of("Plugin Info"))
            .paddingString("=")
            .sendTo(src);
        return CommandResult.success();
    }
}

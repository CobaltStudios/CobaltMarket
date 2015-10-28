package org.cobaltstudios.cobaltmarket.commands;

import org.cobaltstudios.cobaltmarket.CobaltMarket;
import org.spongepowered.api.Game;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.args.GenericArguments;
import org.spongepowered.api.util.command.spec.CommandExecutor;
import org.spongepowered.api.util.command.spec.CommandSpec;

/**
 * Created by iTidez on 10/25/15.
 */
public class CommandManager {
    private Game game;
    private CobaltMarket cm;

    public CommandManager(Game game, CobaltMarket cm) {
        this.game = game;
        this.cm = cm;
        registerCommands();
    }

    public void registerCommands() {
        CommandSpec marketCommand = CommandSpec.builder()
                .description(Texts.of("Info/Help for CobaltMarket"))
                .executor(new InfoCommand())
                .child(CommandSpec.builder()
                                .executor(new InfoCommand())
                                .build(), "info"
                )
                .child(CommandSpec.builder()
                                .executor(new CreateCommand())
                                .build(), "create"
                )
                .child(CommandSpec.builder()
                                .executor(new SqlCommand())
                                .arguments(
                                        GenericArguments.remainingJoinedStrings(Texts.of("query"))
                                )
                                .build(), "sql")
                .child(CommandSpec.builder()
                                .executor(new ListCommand())
                                .build(), "list", "li", "l")
                .build();

        game.getCommandDispatcher().register(cm, marketCommand, "market", "mk");
    }

}

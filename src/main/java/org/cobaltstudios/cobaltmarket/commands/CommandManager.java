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
public class CommandManager implements CommandExecutor {
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
                .executor(this)
                .arguments(
                        GenericArguments.literal(Texts.of("info"))
                )
                .build();

        game.getCommandDispatcher().register(cm, marketCommand, "market", "mk");
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        boolean hasNoArgs = false;
        boolean info = args.getOne("info").isPresent();
        if(info) hasNoArgs = true;
        if(info || hasNoArgs) {
            return new InfoCommand().execute(src, args);
        }
        return CommandResult.success();
    }

}

package org.cobaltstudios.cobaltmarket.commands;

import org.cobaltstudios.cobaltmarket.utils.DatabaseUtils;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by iTidez on 10/27/15.
 */
public class SqlCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String query = "";
        if(args.getOne("query").isPresent()) {
            query = args.<String>getOne("query").get();
        } else {
            query = "";
        }
        try {
            Connection c = DatabaseUtils.dataSource.getConnection();
            Statement stmt = c.createStatement();

            String[] queryParts = query.split(" ");
            int i = 0;
            switch(queryParts[0]) {
                case "SELECT":
                    ArrayList<String> response = new ArrayList<>();
                    ResultSet rs = stmt.executeQuery(query);
                    do {
                        response.add(rs.getObject(i)+"");
                    } while(rs.next());
                    for(String s : (String[])response.toArray()) {
                        src.sendMessage(Texts.of(s));
                    }
                    break;
                case "UPDATE":
                case "INSERT":
                case "DELETE":
                    DatabaseUtils.execute(query);
                    break;
                default:
                    DatabaseUtils.execute(query);
                    break;
            }
            stmt.close();
            c.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return CommandResult.success();
    }
}

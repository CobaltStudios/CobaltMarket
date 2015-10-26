package org.cobaltstudios.cobaltmarket.utils;

import org.cobaltstudios.cobaltmarket.shop.AdminShop;
import org.cobaltstudios.cobaltmarket.shop.Shop;
import org.spongepowered.api.Game;
import org.spongepowered.api.service.sql.SqlService;
import org.spongepowered.api.util.Direction;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.sql.DataSource;

/**
 * Created by iTidez on 10/25/15.
 */
public class DatabaseUtils {
    public static SqlService sql;
    public static DataSource dataSource;

    public static List<String> queue = new ArrayList<>();

    public static void setup(Game game) {
        try {
            sql = game.getServiceManager().provide(SqlService.class).get();

            File folder = new File("config/cobalt/market/data");
            if(!folder.exists())    folder.mkdir();

            dataSource = sql.getDataSource("jdbc:sqlite:config/cobalt/market/data/shops.db");
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", null);

            List<String> tables = new ArrayList<String>();
            while(resultSet.next()) {
                tables.add(resultSet.getString(3));
            }

            if(!tables.contains("stores")) {
                execute("CREATE TABLE stores (uuid TEXT, owner TEXT, x DOUBLE, y DOUBLE, z DOUBLE, yaw DOUBLE, pitch DOUBLE, cost DOUBLE, inventory TEXT, type TINYINT)");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void load(Game game){
        try {
            Connection c = dataSource.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM stores");
            while(rs.next()) {
                if(rs.getInt("type") == 0) { //Admin Store
                    AdminShop adminShop = new AdminShop(DeserializeUtils.inventory(rs.getString("inventory")), rs.getDouble("price"), UUID.fromString(rs.getString("owner")), DeserializeUtils.location(rs.getString("direction")), Direction.valueOf(rs.getString("direction")));

                }
            }
            s.close();
            c.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String execute) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(execute);
            statement.close();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void execute(List<String> execute) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            for(String e : execute) statement.execute(e);
            statement.close();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit() {
        if(queue.isEmpty()) return;
        execute(queue);
        queue.clear();
    }

    public static void queue(String queue) { DatabaseUtils.queue.add(queue); }

    private static HashMap<String, Object> shops = new HashMap<>();
    public static void addShop(String uuid, Object shop) {
        shops.put(uuid, shop);
    }
}

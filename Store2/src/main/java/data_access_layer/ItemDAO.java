package data_access_layer;

import entities.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connection;

    public ItemDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectionUlti.getConnection();
    }

    public List<Item> findAll() throws SQLException {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM `items`";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            items.add(new Item(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),
                    resultSet.getDouble(4),resultSet.getString(5)));
        }
        return items;
    }
}

package services;

import data_access_layer.ItemDAO;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class ItemService {
    ItemDAO itemDAO;

    public ItemService() throws SQLException, ClassNotFoundException {
        itemDAO = new ItemDAO();
    }

    public ObservableList<Item> findAll() throws SQLException {
//        System.out.print(itemDAO.findAll());
//        System.out.print(itemDAO.findAll().get(0).toString());
        return FXCollections.observableArrayList(itemDAO.findAll());
    }
}

package controller;

import entities.Item;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.ItemService;

import java.sql.SQLException;

public class MainController {

    @FXML
    private TableColumn<Item, String> columnOrderName;

    @FXML
    private TableColumn<Item, String> columnWareQuantity;

    @FXML
    private Button BtnDelete;

    @FXML
    private TableColumn<Item, String> columnWareName;

    @FXML
    private TableView<Item> tableWarehouse;

    @FXML
    private ImageView imageView;

    @FXML
    private TableColumn<Item, String> columnOrderQuantity;

    @FXML
    private TableColumn<Item, String> columnOrderPrice;

    @FXML
    private TableColumn<Item, String> columnWarePrice;

    @FXML
    private TableView<Item> tableOrder;

    @FXML
    private Button btnAdd;

    @FXML
    private Text TotalMoney;

    @FXML
    private TableColumn<Item, String> columnOrderNumber;

    @FXML
    private TableColumn<Item, String> columnWareNumber;

    @FXML
    private TextField itemFilter;

    private ItemService itemService;
    private ObservableList<Item> itemObservableList;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        itemService = new ItemService();

        columnWareName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnWarePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnWareQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        itemObservableList = itemService.findAll();
//        System.out.println(itemService.findAll());
//        System.out.print(itemObservableList.get(0).toString());
        tableWarehouse.setItems(itemObservableList);

        //làm cột số thứ tự
        columnWareNumber.setCellValueFactory(param -> new ReadOnlyObjectWrapper(String.valueOf(tableWarehouse.getItems().indexOf(param.getValue()) + 1)));
        //Sự kiện filter bảng
        ObservableList<Item> data = tableWarehouse.getItems();
        itemFilter.textProperty().
                addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                {
                    if (oldValue != null && (newValue.length() < oldValue.length())) {
                        tableWarehouse.setItems(data);
                    }
                    String value = newValue.toLowerCase();
                    ObservableList<Item> subentries = FXCollections.observableArrayList();
                    long count = (long) tableWarehouse.getColumns().size();
                    for (int i = 0; i < tableWarehouse.getItems().size(); i++) {
                        for (int j = 0; j < count; j++) {
                            String entry = String.valueOf(tableWarehouse.getColumns().get(j).getCellData(i));
                            if (entry.toLowerCase().contains(value)) {
                                subentries.add(tableWarehouse.getItems().get(i));
                                break;
                            }
                        }
                    }
                    tableWarehouse.setItems(subentries);
                });
    }

}

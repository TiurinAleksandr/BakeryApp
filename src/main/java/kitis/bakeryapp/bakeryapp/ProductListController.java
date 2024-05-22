package kitis.bakeryapp.bakeryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static kitis.bakeryapp.bakeryapp.WorkDB.getProductsList;

public class ProductListController implements Initializable {

    @FXML private TableView<Product> catalog_table;
    @FXML private TableColumn<Product, String> name;
    @FXML private TableColumn<Product, Integer> count;
    @FXML private TableColumn<Product, Integer> price;

    @FXML private TextField currentCount;
    @FXML private TextField currentName;
    @FXML private TextField currentPrice;

    Product currentProduct;
    String oldName;

    ObservableList catalog = FXCollections.observableArrayList(getProductsList());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        count.setCellValueFactory(new PropertyValueFactory<Product,Integer>("count"));
        price.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
        catalog_table.setItems(catalog);
    }

    @FXML
    void selectItem(MouseEvent event) {
        currentProduct = catalog_table.getSelectionModel().getSelectedItem();
        currentName.setText(currentProduct.getName());
        currentCount.setText(String.valueOf(currentProduct.getCount()));
        currentPrice.setText(String.valueOf(currentProduct.getPrice()));
        oldName = currentName.getText();
    }

    @FXML
    void addProductButton(ActionEvent event) throws IOException {
        App.callWindow("add-product.fxml", "Добавление товара - ПЕКАРНЯ");
        updateTable();
    }

    @FXML
    void deleteProductButton(ActionEvent event) {
        Product selectedProduct = catalog_table.getSelectionModel().getSelectedItem();
        WorkDB.delete(selectedProduct);
        updateTable();
    }

    @FXML
    void changeProductButton(ActionEvent event) throws IOException {
        String name = currentName.getText();
        Integer count = Integer.parseInt(currentCount.getText());
        Integer price = Integer.parseInt(currentPrice.getText());

        WorkDB.change(new Product(name,count,price), oldName);
        updateTable();
    }

    void updateTable() {
        catalog = FXCollections.observableArrayList(getProductsList());
        System.out.println("Обновление: " + catalog.size());
        catalog_table.setItems(catalog);
    }
}
package kitis.bakeryapp.bakeryapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController {

    @FXML private TextField countField;
    @FXML private TextField nameField;
    @FXML private TextField priceField;

    @FXML
    void addProduct(ActionEvent event) {
        String name = nameField.getText();
        int count = Integer.parseInt(countField.getText());
        int price = Integer.parseInt(priceField.getText());

        Product newProduct = new Product(name,count,price);
        WorkDB.add(newProduct);
        Stage stage = (Stage) countField.getScene().getWindow();
        stage.close();
    }
}

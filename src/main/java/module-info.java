module kitis.bakeryapp.bakeryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kitis.bakeryapp.bakeryapp to javafx.fxml;
    exports kitis.bakeryapp.bakeryapp;
}
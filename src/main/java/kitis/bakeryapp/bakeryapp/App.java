package kitis.bakeryapp.bakeryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        FXMLLoader catalogWindow = new FXMLLoader(App.class.getResource("product-list.fxml"));
        Scene scene = new Scene(catalogWindow.load());
        mainStage.setTitle("Каталог товаров - ПЕКАРНЯ");
        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void callWindow(String nameFXML, String title) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxml = new FXMLLoader(App.class.getResource(nameFXML));
        Scene scene = new Scene(fxml.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.showAndWait();
    }
}

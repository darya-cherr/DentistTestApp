package com.example.dentisttestapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.burningwave.core.classes.Modules;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Прогнозирование вероятности развития болезней периодонта");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("icon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        Modules.create().exportAllToAll();
        launch();
    }

}
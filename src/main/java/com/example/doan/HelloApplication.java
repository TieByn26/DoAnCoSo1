package com.example.doan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));

        Scene scene = new Scene(loader.load());
        stage.setHeight(800);
        stage.setWidth(1200);
        stage.show();
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}
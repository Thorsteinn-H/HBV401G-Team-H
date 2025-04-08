package com.hopur7h.hotels.hopur7h;

import com.hopur7h.hotels.hopur7h.storage.DatabaseInit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hotel Booking System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        DatabaseInit.initDatabase();

        launch(args);
    }
}

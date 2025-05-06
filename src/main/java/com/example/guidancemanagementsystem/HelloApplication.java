package com.example.guidancemanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PEÑARANDA OFF-CAMPUS - SCHOOL GUINDANCE MANAGEMENT SYSTEM - LOGIN");
        stage.setScene(scene);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/com/example/guidancemanagementsystem/images/logo.png")));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    public static void main(String[] args) {
        try {
            launch(args); // Or whatever your main entry point is
        } catch (Exception e) {
            e.printStackTrace(); // This will print the full error in the console
        }
    }
}
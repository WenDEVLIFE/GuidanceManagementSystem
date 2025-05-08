package com.example.guidancemanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AgreementController {

    private Stage stage;


    private  String role;

    @FXML
    private void agree() throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("guidanceUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PEÑARANDA OFF-CAMPUS - SCHOOL GUINDANCE MANAGEMENT SYSTEM");
        stage.setScene(scene);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/com/example/guidancemanagementsystem/images/logo.png")));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        GuidanceSystemController controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setRole(role);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

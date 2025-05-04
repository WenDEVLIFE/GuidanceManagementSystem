package com.example.guidancemanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloController {

    private Stage stage;


    @FXML
    protected void onLogin() {

        loginNow();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void loginNow(){
      try{

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


      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }
}
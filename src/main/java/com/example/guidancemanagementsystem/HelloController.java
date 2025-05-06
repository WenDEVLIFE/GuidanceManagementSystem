package com.example.guidancemanagementsystem;

import database.LoginSQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloController {

    private Stage stage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;


    @FXML
    protected void onLogin() {

        loginNow();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void loginNow(){
      try{

          String username = usernameField.getText();

            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;

            }

          LoginSQL.getInstance().LoginUser(username, password, stage);

      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }
}
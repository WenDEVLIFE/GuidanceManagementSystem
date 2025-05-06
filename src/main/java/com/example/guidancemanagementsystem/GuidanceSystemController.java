package com.example.guidancemanagementsystem;

import database.AccountManagerSQL;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GuidanceSystemController {

    private Stage stage;

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private JComboBox<String> roleComboBox;

    public void initialize(){

        DefaultComboBoxModel<String> roles = new DefaultComboBoxModel<>();
        roles.addElement("Admin");
        roles.addElement("Student");
        roleComboBox.setModel(roles);

    }

    void setStage(Stage stage) {
        this.stage = stage;
    }



    @FXML
    public void onAddAccount(){

        String username = usernameField.getText();

        String fullname = nameField.getText();

        String password = passwordField1.getText();

        String confirm_password = passwordField2.getText();

        String role = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || fullname.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirm_password)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("fullname", fullname);
        userData.put("password", password);
        userData.put("role", role);
        AccountManagerSQL.getInstance().AddAccount(userData);
        usernameField.clear();
        nameField.clear();
        passwordField1.clear();
        passwordField2.clear();
        roleComboBox.setSelectedIndex(0);

    }
}

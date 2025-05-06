package com.example.guidancemanagementsystem;

import database.AccountManagerSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;


import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.*;
import javafx.scene.control.Tab;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;

public class GuidanceSystemController {

    private Stage stage;

    private  String role;

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
    private ComboBox<String> roleComboBox;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab dashboardTab;

    @FXML
    private Tab addAccountTab;

    @FXML
    private Tab studentTab;

    @FXML
    private Tab appointmentTab;

    @FXML
    private Tab violationTab;

    @FXML
    private Tab reportTab;

    @FXML
    private Tab accountTab;



    public void initialize(){

        ObservableList<String> roles = FXCollections.observableArrayList("Admin", "Student");
        roleComboBox.setItems(roles);

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void navigateToDashboard() {
        tabPane.getSelectionModel().select(dashboardTab);
    }

    @FXML
    public void navigateToAddAccount() {
        tabPane.getSelectionModel().select(addAccountTab);
    }

    @FXML
    public void navigateToStudent() {
        tabPane.getSelectionModel().select(studentTab);
    }

    @FXML
    public void navigateToAppointment() {
        tabPane.getSelectionModel().select(appointmentTab);
    }

    @FXML
    public void navigateToViolation() {
        tabPane.getSelectionModel().select(violationTab);
    }

    @FXML
    public void navigateToReport() {
        tabPane.getSelectionModel().select(reportTab);
    }

    @FXML
    public void navigateToAccount() {
        tabPane.getSelectionModel().select(accountTab);
    }



    @FXML
    public void onLogout() {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            // Handle logout logic here
            System.out.println("User logged out.");
            stage.close();
        }
    }



    @FXML
    public void onAddAccount(){

        String username = usernameField.getText();

        String fullname = nameField.getText();

        String password = passwordField1.getText();

        String confirm_password = passwordField2.getText();

        String role =  roleComboBox.getValue();

        if (username.isEmpty() || fullname.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirm_password)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (role == null || role.equals("Select a role")) {
            JOptionPane.showMessageDialog(null, "Please select a role.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.length() < 5) {
            JOptionPane.showMessageDialog(null, "Username must be at least 5 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.equals(username)) {
            JOptionPane.showMessageDialog(null, "Password cannot be the same as username.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fullname.length() < 3) {
            JOptionPane.showMessageDialog(null, "Full name must be at least 3 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
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
        roleComboBox.setValue("Select a role");
    }

    public void setRole(String role) {
        this.role = role;
        System.out.println("Role set to: " + role);
        if (role.equals("Admin")) {
            // Show admin-specific UI elements
            System.out.println("Admin UI elements shown.");
        } else if (role.equals("Student")) {
            // Show student-specific UI elements
            System.out.println("Student UI elements shown.");
        } else {
            // Handle other roles or show a default UI
            System.out.println("Default UI elements shown.");
        }
    }
}

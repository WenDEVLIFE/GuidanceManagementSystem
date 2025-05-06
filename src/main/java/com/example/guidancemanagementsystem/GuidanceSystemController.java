package com.example.guidancemanagementsystem;

import database.AccountManagerSQL;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import model.AccountModel;
import utils.ManageButton;

public class GuidanceSystemController {

    private Stage stage;

    private  String role;

    @FXML
    private TextField nameField;

    @FXML
    private TextField editNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField editUsernameField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField editPasswordField;


    @FXML
    private PasswordField passwordField2;

    @FXML
    private PasswordField editPasswordField2;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private ComboBox<String> editRoleComboBox;

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

    @FXML
    private Tab editAccountTab;

    @FXML
    private Button accountBtn;

    @FXML
    private TableView<AccountModel> accountTable;

    @FXML
    private TableColumn<AccountModel, String> userIdColumn;

    @FXML
    private TableColumn<AccountModel, String> userNameColumn;

    @FXML
    private TableColumn<AccountModel, String> userRoleColumn;

    @FXML
    private TableColumn<AccountModel, String> userFullNameColumn;

    @FXML
    private TableColumn<AccountModel, String> userPasswordColumn;

    @FXML
    private TableColumn<AccountModel, Void> EditButtonColumn;

    @FXML
    private TableColumn<AccountModel, Void> DeleteButtonColumn;

    ObservableList <AccountModel> accountModelObservableList = FXCollections.observableArrayList();



    public void initialize(){

        ObservableList<String> roles = FXCollections.observableArrayList("Admin", "Student");
        roleComboBox.setItems(roles);
        editRoleComboBox.setItems(roles);

        accountTable.getColumns().clear();
        accountTable.getItems().clear();

        userNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        userIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        userFullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        userPasswordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        userRoleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        EditButtonColumn.setCellFactory(ManageButton.forTableColumn("Edit", accountTable, accountModelObservableList, this));
        DeleteButtonColumn.setCellFactory(ManageButton.forTableColumn("Delete", accountTable, accountModelObservableList, this));

        accountTable.getColumns().addAll(userIdColumn, userNameColumn, userPasswordColumn, userFullNameColumn, userRoleColumn, EditButtonColumn, DeleteButtonColumn);
         loadAccoountData();

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

    @FXML
    public void navigateToEditAccount(AccountModel account) {
        // Navigate to the edit account tab
        tabPane.getSelectionModel().select(editAccountTab);

        // Populate the fields with the account data
        editUsernameField.setText(account.getUsername());

        editNameField.setText(account.getName());

        editPasswordField.setText(account.getPassword());

        editPasswordField2.setText(account.getPassword());

        editRoleComboBox.setValue(account.getRole());

    }

    @FXML
    public void updateAccount(){
        String username = editUsernameField.getText();

        String fullname = editNameField.getText();

        String password = editPasswordField.getText();

        String confirm_password = editPasswordField2.getText();

        String role =  editRoleComboBox.getValue();

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

        AccountManagerSQL.getInstance().updateAccount(userData);
        editUsernameField.clear();
        editNameField.clear();
        editPasswordField.clear();
        editPasswordField2.clear();
        editRoleComboBox.setValue("Select a role");
        tabPane.getSelectionModel().select(accountTab);
    }

    public void setRole(String role) {
        this.role = role;
        System.out.println("Role set to: " + role);
        if (role.equals("Admin")) {
            // Show admin-specific UI elements
            System.out.println("Admin UI elements shown.");

            accountBtn.setVisible(true);
        } else {
            // Handle other roles or show a default UI
            System.out.println("Default UI elements shown.");
            accountBtn.setVisible(false);
        }
    }

    // This will load the user data
    public void loadAccoountData() {
        accountTable.getItems().clear();
        accountModelObservableList.clear();
        accountTable.refresh();

        try {
            ObservableList<Map<String, Object>> accounts = AccountManagerSQL.getInstance().getAllAccounts();
            if (accounts != null) {
                for (Map<String, Object> account : accounts) {
                    String id = (String) account.get("user_id");
                    String username = (String) account.get("username");
                    String password = (String) account.get("password");
                    String name = (String) account.get("name");
                    String role = (String) account.get("role");

                    AccountModel accountModel = new AccountModel(id, username, password, name, role);
                    accountModelObservableList.add(accountModel);
                }
                accountTable.setItems(accountModelObservableList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

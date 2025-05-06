package com.example.guidancemanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CustomJDialog {
    private static volatile CustomJDialog instance;

    public static CustomJDialog getInstance() {
        if (instance == null) {
            instance = new CustomJDialog();
        }
        return instance;
    }

    public void showDialog(String title, String message) {
        // Create an Alert dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.showAndWait(); // Show the dialog and wait for user interaction
    }
}
package com.example.guidancemanagementsystem;

public class CustomJDialog {
    private static volatile CustomJDialog instance;

    public static CustomJDialog getInstance() {
        if (instance == null) {
            instance = new CustomJDialog();
        }
        return instance;
    }

    public void showDialog(String title, String message) {

        // Create a new JDialog
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle(title);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Create a JLabel to display the message
        javax.swing.JLabel label = new javax.swing.JLabel(message);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // Add the label to the dialog
        dialog.getContentPane().add(label, java.awt.BorderLayout.CENTER);

        // Set the size and location of the dialog
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);

        // Show the dialog
        dialog.setVisible(true);
    }
}

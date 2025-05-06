package database;

import com.example.guidancemanagementsystem.CustomJDialog;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class AccountManagerSQL {

    private static  volatile AccountManagerSQL instance;

    public static AccountManagerSQL getInstance() {
        if (instance == null) {
            synchronized (AccountManagerSQL.class) {
                if (instance == null) {
                    instance = new AccountManagerSQL();
                }
            }
        }
        return instance;
    }

    // This will be used to add a new account
    public void AddAccount(Map<String, Object> userData){
        String sql = "INSERT INTO users (username, password, name, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, (String) userData.get("username"));
            pstmt.setString(2, (String) userData.get("password"));
            pstmt.setString(3, (String) userData.get("fullname"));
            pstmt.setString(4, (String) userData.get("role"));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account created successfully.");
                CustomJDialog.getInstance().showDialog( "Account Created", "Account created successfully.");
            } else {
                System.out.println("Failed to create account.");
                CustomJDialog.getInstance().showDialog( "Account Creation Failed", "Failed to create account.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

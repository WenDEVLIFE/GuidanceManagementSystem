package database;

import com.example.guidancemanagementsystem.CustomJDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

    public ObservableList <Map<String, Object>> getAllAccounts() {
        String sql = "SELECT * FROM users";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            ObservableList<Map<String, Object>> accounts = FXCollections.observableArrayList();
            while (rs.next()) {
                Map<String, Object> account = new HashMap<>();
                account.put("user_id", rs.getString("user_id"));
                account.put("username", rs.getString("username"));
                account.put("password", rs.getString("password"));
                account.put("name", rs.getString("name"));
                account.put("role", rs.getString("role"));
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

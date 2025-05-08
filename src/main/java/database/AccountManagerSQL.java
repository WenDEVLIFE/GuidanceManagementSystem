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
        String insertReports = "INSERT INTO reports  (description, date, time) VALUES (?, ?, ?)";
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

                try (PreparedStatement reportPstmt = conn.prepareStatement(insertReports)) {
                    reportPstmt.setString(1, "Account with username " + userData.get("username") + " was created.");
                    reportPstmt.setString(2, String.valueOf(java.time.LocalDate.now()));
                    reportPstmt.setString(3, String.valueOf(java.time.LocalTime.now()));
                    reportPstmt.executeUpdate();
                }
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

    public void deleteUser(String id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        String insertReports = "INSERT INTO reports  (description, date, time) VALUES (?, ?, ?)";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
                CustomJDialog.getInstance().showDialog( "User Deleted", "User deleted successfully.");

                try (PreparedStatement reportPstmt = conn.prepareStatement(insertReports)) {
                    reportPstmt.setString(1, "User with ID " + id + " was deleted.");
                    reportPstmt.setString(2, String.valueOf(java.time.LocalDate.now()));
                    reportPstmt.setString(3, String.valueOf(java.time.LocalTime.now()));
                    reportPstmt.executeUpdate();
                }
            } else {
                System.out.println("Failed to delete user.");
                CustomJDialog.getInstance().showDialog( "User Deletion Failed", "Failed to delete user.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAccount(Map<String, Object> userData) {
        String sql = "UPDATE users SET username = ?, password = ?, name = ?, role = ? WHERE user_id = ?";
        int userId = Integer.parseInt((String) userData.get("user_id"));
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, (String) userData.get("username"));
            pstmt.setString(2, (String) userData.get("password"));
            pstmt.setString(3, (String) userData.get("fullname"));
            pstmt.setString(4, (String) userData.get("role"));
            pstmt.setInt(5, userId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account updated successfully.");
                CustomJDialog.getInstance().showDialog( "Account Updated", "Account updated successfully.");
            } else {
                System.out.println("Failed to update account.");
                CustomJDialog.getInstance().showDialog( "Account Update Failed", "Failed to update account.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

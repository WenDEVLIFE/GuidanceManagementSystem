package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {

    public static String database_url = "jdbc:sqlite:./database/guidance.db";
    private static volatile  MYSQLConnection instance;

    public static MYSQLConnection getInstance() {
        if (instance == null) {
            synchronized (MYSQLConnection.class) {
                if (instance == null) {
                    instance = new MYSQLConnection();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        testConnection();
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(database_url);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }

    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Connection to SQLite has been established.");
            JOptionPane.showMessageDialog(null, "Connection to SQLite has been established.", "Connection Successful", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }


}

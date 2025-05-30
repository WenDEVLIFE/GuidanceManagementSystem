package database;

import com.example.guidancemanagementsystem.CustomJDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ViolationManagerSQL {

    private static volatile ViolationManagerSQL instance;

    public static ViolationManagerSQL getInstance() {
        if (instance == null) {
            synchronized (ViolationManagerSQL.class) {
                if (instance == null) {
                    instance = new ViolationManagerSQL();
                }
            }
        }
        return instance;
    }

    public void InsertViolation(Map<String, Object> violationData) {
        String sql = "INSERT INTO violation_table (student_name, date_submitted,description, violation_type, date_of_violation) VALUES (?, ?, ?, ?, ?)";
        String insertReports = "INSERT INTO reports  (description, date, time) VALUES (?, ?, ?)";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, (String) violationData.get("student_name"));

            LocalDate datestr = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            String formattedDate = datestr.format(formatter);
            pstmt.setString(2, formattedDate);
            pstmt.setString(3, (String) violationData.get("description"));
            pstmt.setString(4, (String) violationData.get("violation_type"));
            pstmt.setString(5, (String) violationData.get("date_of_violation"));


            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {

                try (var generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Violation recorded successfully with ID: " + generatedId);
                        CustomJDialog.getInstance().showDialog("Violation Recorded", "Violation recorded successfully with ID: " + generatedId);

                        // Insert into reports
                        try (var pstmtReports = conn.prepareStatement(insertReports)) {
                            pstmtReports.setString(1, "Appointment with ID " + generatedId + " was created.");
                            LocalDate datestrReport = LocalDate.now();
                            DateTimeFormatter formatterReport = DateTimeFormatter.ofPattern("M/d/yyyy");
                            String formattedDateReport = datestrReport.format(formatterReport);
                            pstmtReports.setString(2, formattedDateReport);
                            pstmtReports.setString(3, java.time.LocalTime.now().toString());
                            pstmtReports.executeUpdate();
                        }
                    }
                }
            } else {
                System.out.println("Failed to record violation.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public ObservableList<Map<String, Object>> getAllViolations() {
         String sql = "SELECT * FROM violation_table";
        ObservableList<Map<String, Object>> violations = FXCollections.observableArrayList();
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            var resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> violationData = new HashMap<>();
                violationData.put("violation_id", resultSet.getInt("violation_id"));
                violationData.put("student_name", resultSet.getString("student_name"));
                violationData.put("date_submitted", resultSet.getString("date_submitted"));
                violationData.put("description", resultSet.getString("description"));
                violationData.put("violation_type", resultSet.getString("violation_type"));
                violationData.put("date_of_violation", resultSet.getString("date_of_violation"));

                violations.add(violationData);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return violations;
    }

    public void deleteViolation(String id) {
        String sql = "DELETE FROM violation_table WHERE violation_id = ?";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Violation deleted successfully.");
                CustomJDialog.getInstance().showDialog("Violation Deleted", "Violation deleted successfully.");
            } else {
                System.out.println("Failed to delete violation.");
                CustomJDialog.getInstance().showDialog("Violation Deletion Failed", "Failed to delete violation.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateViolation(Map<String, Object> violationData) {
        String sql = "UPDATE violation_table SET student_name = ?, description = ?, violation_type = ?, date_of_violation = ? WHERE violation_id = ?";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, (String) violationData.get("student_name"));
            pstmt.setString(2, (String) violationData.get("description"));
            pstmt.setString(3, (String) violationData.get("violation_type"));
            pstmt.setString(4, (String) violationData.get("date_of_violation"));
            pstmt.setInt(5, Integer.parseInt((String) violationData.get("violationId")));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Violation updated successfully.");
                CustomJDialog.getInstance().showDialog("Violation Updated", "Violation updated successfully.");
            } else {
                System.out.println("Failed to update violation.");
                CustomJDialog.getInstance().showDialog("Violation Update Failed", "Failed to update violation.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getViolationCount() {
        String sql = "SELECT COUNT(*) AS count FROM violation_table";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            var resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}

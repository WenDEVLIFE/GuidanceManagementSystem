package database;

import com.example.guidancemanagementsystem.CustomJDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}

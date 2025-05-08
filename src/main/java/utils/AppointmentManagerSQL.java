package utils;

import com.example.guidancemanagementsystem.CustomJDialog;
import database.MYSQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

public class AppointmentManagerSQL {

    private  static volatile  AppointmentManagerSQL instance;

    public static AppointmentManagerSQL getInstance(){
        if (instance == null) {
            synchronized (AppointmentManagerSQL.class) {
                if (instance == null) {
                    instance = new AppointmentManagerSQL();
                }
            }
        }

        return instance;
    }


    public void InsertAppointment(Map<String, Object> appointmentData) {
        String insertAppointment = "INSERT INTO appointment_table (student_name, date_submitted, date_of_appointment, time) VALUES (?, ?, ?, ?)";
        try (var conn = MYSQLConnection.getConnection();
             var pstmt = conn.prepareStatement(insertAppointment)) {
            pstmt.setString(1, (String) appointmentData.get("studentName"));

            LocalDate datestr = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            String formattedDate = datestr.format(formatter);
            pstmt.setString(2, formattedDate);
            pstmt.setString(3, (String) appointmentData.get("dateOfAppointment"));
            pstmt.setString(4, (String) appointmentData.get("time"));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment created successfully.");
                CustomJDialog.getInstance().showDialog("Appointment Created", "Appointment created successfully.");
            } else {
                System.out.println("Failed to create appointment.");
                CustomJDialog.getInstance().showDialog("Appointment Creation Failed", "Failed to create appointment.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Map<String, Object>> getAllAppointments() {
        String selectAllAppointments = "SELECT * FROM appointment_table";
        try (var conn = MYSQLConnection.getConnection();
             var pstmt = conn.prepareStatement(selectAllAppointments);
             var rs = pstmt.executeQuery()) {

            ObservableList<Map<String, Object>> appointmentList = FXCollections.observableArrayList();

            while (rs.next()) {
                Map<String, Object> appointmentData = new HashMap<>();
                appointmentData.put("appointment_id", rs.getString("appointment_id"));
                appointmentData.put("student_name", rs.getString("student_name"));
                appointmentData.put("date_submitted", rs.getString("date_submitted"));
                appointmentData.put("date_of_appointment", rs.getString("date_of_appointment"));
                appointmentData.put("time", rs.getString("time"));

                appointmentList.add(appointmentData);
            }
            return appointmentList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateAppointment(Map<String, Object> appointmentData) {
        String updateAppointment = "UPDATE appointment_table SET student_name = ?, date_of_appointment = ?, time = ? WHERE appointment_id = ?";
        try (var conn = MYSQLConnection.getConnection();
             var pstmt = conn.prepareStatement(updateAppointment)) {
            pstmt.setString(1, (String) appointmentData.get("studentName"));
            pstmt.setString(2, (String) appointmentData.get("dateOfAppointment"));
            pstmt.setString(3, (String) appointmentData.get("time"));
            pstmt.setInt(4, (Integer) appointmentData.get("appointmentId"));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment updated successfully.");
                CustomJDialog.getInstance().showDialog("Appointment Updated", "Appointment updated successfully.");
            } else {
                System.out.println("Failed to update appointment.");
                CustomJDialog.getInstance().showDialog("Appointment Update Failed", "Failed to update appointment.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAppointment(String id) {
        String deleteAppointment = "DELETE FROM appointment_table WHERE appointment_id = ?";
        try (var conn = MYSQLConnection.getConnection();
             var pstmt = conn.prepareStatement(deleteAppointment)) {
            pstmt.setString(1, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment deleted successfully.");
                CustomJDialog.getInstance().showDialog("Appointment Deleted", "Appointment deleted successfully.");
            } else {
                System.out.println("Failed to delete appointment.");
                CustomJDialog.getInstance().showDialog("Appointment Deletion Failed", "Failed to delete appointment.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

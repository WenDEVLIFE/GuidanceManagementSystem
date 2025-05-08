package utils;

import com.example.guidancemanagementsystem.CustomJDialog;
import database.MYSQLConnection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}

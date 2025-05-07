package database;

import com.example.guidancemanagementsystem.CustomJDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class StudentManagerSQL {

    private static volatile  StudentManagerSQL instance;

    public static StudentManagerSQL getInstance(){
        if (instance == null) {
            synchronized (StudentManagerSQL.class) {
                if (instance == null) {
                    instance = new StudentManagerSQL();
                }
            }
        }

        return instance;
    }

     public void InsertStudent(Map<String, Object> studentData){
        String insertStudent = "INSERT INTO students (student_name, birthdate, guardian, contact_number) VALUES (?, ?, ?, ?)";
         try (Connection conn = MYSQLConnection.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(insertStudent)) {
             pstmt.setString(1, (String) studentData.get("studentName"));
             pstmt.setString(2, (String) studentData.get("birthdate"));
             pstmt.setString(3, (String) studentData.get("guardian"));
             pstmt.setString(4, (String) studentData.get("phone"));

             int rowsAffected = pstmt.executeUpdate();

             if (rowsAffected > 0) {
                 System.out.println("Account created successfully.");
                 CustomJDialog.getInstance().showDialog( "Student Created", "Student created successfully.");
             } else {
                 System.out.println("Failed to create account.");
                 CustomJDialog.getInstance().showDialog( "Student Creation Failed", "Failed to create student.");
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }

     }
}

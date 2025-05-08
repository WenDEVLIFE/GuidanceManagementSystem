package database;

import com.example.guidancemanagementsystem.CustomJDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.StudentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
        String insertStudent = "INSERT INTO students (student_name, birthdate, guardian, contact_number, year_and_section) VALUES (?, ?, ?, ?, ?)";
         try (Connection conn = MYSQLConnection.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(insertStudent)) {
             pstmt.setString(1, (String) studentData.get("studentName"));
             pstmt.setString(2, (String) studentData.get("birthdate"));
             pstmt.setString(3, (String) studentData.get("guardian"));
             pstmt.setString(4, (String) studentData.get("phone"));
                pstmt.setString(5, (String) studentData.get("yearAndSection"));

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

     public ObservableList <Map<String, Object>> getStudent(){
         String sql = "SELECT * FROM students";
         try (Connection conn = MYSQLConnection.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
             ResultSet rs = pstmt.executeQuery();
             ObservableList<Map<String, Object>> accounts = FXCollections.observableArrayList();
             while (rs.next()) {
                 Map<String, Object> account = new HashMap<>();
                 account.put("student_id", rs.getString("student_id"));
                 account.put("student_name", rs.getString("student_name"));
                 account.put("guardian", rs.getString("guardian"));
                 account.put("contact_number", rs.getString("contact_number"));
                 account.put("birthdate", rs.getString("birthdate"));
                 account.put("year_and_section", rs.getString("year_and_section"));
                 accounts.add(account);
             }
             return accounts;
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return null;
     }

    public void updateStudent(Map<String, Object> studentData) {
        String updateStudent = "UPDATE students SET student_name = ?, birthdate = ?, guardian = ?, contact_number = ?, year_and_section = ? WHERE student_id = ?";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateStudent)) {
            pstmt.setString(1, (String) studentData.get("studentName"));
            pstmt.setString(2, (String) studentData.get("birthdate"));
            pstmt.setString(3, (String) studentData.get("guardian"));
            pstmt.setString(4, (String) studentData.get("phone"));
            pstmt.setString(5, (String) studentData.get("yearAndSection"));
            pstmt.setInt(6, Integer.parseInt((String) studentData.get("studentId")));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account updated successfully.");
                CustomJDialog.getInstance().showDialog( "Student Updated", "Student updated successfully.");
            } else {
                System.out.println("Failed to update account.");
                CustomJDialog.getInstance().showDialog( "Student Update Failed", "Failed to update student.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

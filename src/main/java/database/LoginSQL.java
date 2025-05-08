package database;

import com.example.guidancemanagementsystem.AgreementController;
import com.example.guidancemanagementsystem.CustomJDialog;
import com.example.guidancemanagementsystem.GuidanceSystemController;
import com.example.guidancemanagementsystem.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginSQL {

    private static volatile LoginSQL instance;

    public static LoginSQL getInstance() {
        if (instance == null) {
            synchronized (LoginSQL.class) {
                if (instance == null) {
                    instance = new LoginSQL();
                }
            }
        }
        return instance;
    }

    // This will be used to check if the user is in the database
    public void LoginUser(String username, String password, Stage stage){

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String role = rs.getString("role");
                System.out.println("Login successful.");
                CustomJDialog.getInstance().showDialog( "Login Successful", "Login successful.");

                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("agreementUI.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("PEÑARANDA OFF-CAMPUS - SCHOOL GUINDANCE MANAGEMENT SYSTEM");
                stage.setScene(scene);
                stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/com/example/guidancemanagementsystem/images/logo.png")));
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();

                AgreementController controller = fxmlLoader.getController();
                controller.setStage(stage);
                controller.setRole(role);


                // Handle successful login
            } else {
                System.out.println("Invalid username or password.");
                CustomJDialog.getInstance().showDialog( "Login Failed", "Invalid username or password.");
                // Handle failed login
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

}

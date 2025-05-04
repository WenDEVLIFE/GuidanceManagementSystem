module com.example.guidancemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guidancemanagementsystem to javafx.fxml;
    exports com.example.guidancemanagementsystem;
}
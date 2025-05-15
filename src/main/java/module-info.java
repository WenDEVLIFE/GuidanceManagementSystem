module com.example.guidancemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.xerial.sqlitejdbc;


    opens com.example.guidancemanagementsystem to javafx.fxml;
    exports com.example.guidancemanagementsystem;
}
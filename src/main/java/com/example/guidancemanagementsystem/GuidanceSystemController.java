package com.example.guidancemanagementsystem;

import database.AccountManagerSQL;
import database.StudentManagerSQL;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;


import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.*;
import javafx.scene.control.Tab;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import model.AccountModel;
import model.AppointmentModel;
import model.StudentModel;
import model.ViolationModel;
import utils.AppointmentButton;
import utils.AppointmentManagerSQL;
import utils.ManageButton;
import utils.StudentButton;

public class GuidanceSystemController {

    private Stage stage;

    private  String role;

    private String userId;

    private String studentId;

    private String  violationId;

    private  String appointmentId;

    @FXML
    private TextField nameField;

    @FXML
    private TextField editNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField editUsernameField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField editPasswordField;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private PasswordField editPasswordField2;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private ComboBox<String> editRoleComboBox;

    @FXML
    private TextField studentName;

    @FXML
    private TextField guardianName;

    @FXML
    private DatePicker birthDate;

    @FXML
    private TextField studentPhoneNumber;

    @FXML
    private TextField yearAndSection;

    @FXML
    private  TextField editStudentName;

    @FXML
    private  TextField editGuardianName;

    @FXML
    private  DatePicker editBirthDate;

    @FXML
    private  TextField editStudentPhoneNumber;

    @FXML
    private  TextField editYearAndSection;

    @FXML
    private ComboBox<String> studentComboBox;

    @FXML
    private DatePicker dateOfAppointment;

    @FXML
    private TextField timeOfAppointment;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab dashboardTab;

    @FXML
    private Tab addAccountTab;

    @FXML
    private Tab studentTab;

    @FXML
    private Tab appointmentTab;

    @FXML
    private Tab violationTab;

    @FXML
    private Tab reportTab;

    @FXML
    private Tab accountTab;

    @FXML
    private Tab editAccountTab;

    @FXML
    private Tab addAppointmentTab;

    @FXML
    private Tab editAppointmentTab;

    @FXML
    private Tab addViolationTab;

    @FXML
    private Tab editViolationTab;

    @FXML
    private Tab addStudentTab;

    @FXML
    private Tab editStudentTab;

    @FXML
    private Button accountBtn;

    @FXML
    private TableView<AccountModel> accountTable;

    @FXML
    private TableColumn<AccountModel, String> userIdColumn;

    @FXML
    private TableColumn<AccountModel, String> userNameColumn;

    @FXML
    private TableColumn<AccountModel, String> userRoleColumn;

    @FXML
    private TableColumn<AccountModel, String> userFullNameColumn;

    @FXML
    private TableColumn<AccountModel, String> userPasswordColumn;

    @FXML
    private TableColumn<AccountModel, Void> EditButtonColumn;

    @FXML
    private TableColumn<AccountModel, Void> DeleteButtonColumn;

    @FXML
    private TableView<StudentModel> studentTable;

    @FXML
            private TableColumn<StudentModel, String> studentIdColumn;

    @FXML
            private TableColumn <StudentModel, String> studentNameColumn;


    @FXML
    private TableColumn <StudentModel, String> studentBdateColumn;

    @FXML
    private TableColumn <StudentModel, String> guardianColumn;

    @FXML
    private TableColumn <StudentModel, String> studentPnumColumn;

    @FXML
    private TableColumn <StudentModel, String> yearAndSectionColumn;

    @FXML
    private TableColumn <StudentModel, Void> studentEditColumn;

    @FXML
    private TableColumn <StudentModel, Void> studentDeleteColumn;

    @FXML
            private TableView<AppointmentModel> appointmentTable;

    @FXML
            private TableColumn<AppointmentModel, String> appointmentIdColumn;

    @FXML
            private TableColumn<AppointmentModel, String> appointmentStudentNameColumn;

    @FXML
            private TableColumn<AppointmentModel, String> appointmentDateColumn;

    @FXML
    private TableColumn<AppointmentModel, String> dateSubmittedColumn;

    @FXML
            private TableColumn<AppointmentModel, String> appointmentTimeColumn;

    @FXML
            private TableColumn<AppointmentModel, Void> appointmentEditColumn;

    @FXML
            private TableColumn<AppointmentModel, Void> appointmentDeleteColumn;

    ObservableList <AccountModel> accountModelObservableList = FXCollections.observableArrayList();

    ObservableList <StudentModel> studentModelObservableList = FXCollections.observableArrayList();

    ObservableList <String> studentObservableList = FXCollections.observableArrayList();

    ObservableList <AppointmentModel> appointmentModelObservableList = FXCollections.observableArrayList();

    public void initialize(){

        ObservableList<String> roles = FXCollections.observableArrayList("Admin", "Student");
        roleComboBox.setItems(roles);
        editRoleComboBox.setItems(roles);

        accountTable.getColumns().clear();
        accountTable.getItems().clear();

        userNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        userIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        userFullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        userPasswordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        userRoleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));EditButtonColumn.setCellFactory(ManageButton.forTableColumn("Edit", accountTable, accountModelObservableList, this));
        DeleteButtonColumn.setCellFactory(ManageButton.forTableColumn("Delete", accountTable, accountModelObservableList, this));

        accountTable.getColumns().addAll(userIdColumn, userNameColumn, userPasswordColumn, userFullNameColumn, userRoleColumn, EditButtonColumn, DeleteButtonColumn);
         loadAccoountData();

         studentTable.getColumns().clear();
        studentTable.getItems().clear();

        studentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        studentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
        studentBdateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthdate()));
        guardianColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGuardianName()));
        studentPnumColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        yearAndSectionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getYearAndSection()));
        studentEditColumn.setCellFactory(StudentButton.forTableColumn("Edit", studentTable, studentModelObservableList, this));
        studentDeleteColumn.setCellFactory(StudentButton.forTableColumn("Delete", studentTable, studentModelObservableList, this));

        studentTable.getColumns().addAll(studentIdColumn, studentNameColumn, studentBdateColumn, guardianColumn, studentPnumColumn, yearAndSectionColumn, studentEditColumn, studentDeleteColumn);
        loadStudent();

        studentComboBox.setItems(studentObservableList);

        appointmentTable.getColumns().clear();
        appointmentTable.getItems().clear();
        appointmentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        appointmentStudentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
        dateSubmittedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateSubmitted()));
        appointmentDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAppointmentDate()));
        appointmentTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAppointmentTime()));
        appointmentEditColumn.setCellFactory(AppointmentButton.forTableColumn("Edit", appointmentTable, appointmentModelObservableList, this));
        appointmentDeleteColumn.setCellFactory(AppointmentButton.forTableColumn("Delete", appointmentTable, appointmentModelObservableList, this));

        appointmentTable.getColumns().addAll(appointmentIdColumn, appointmentStudentNameColumn, dateSubmittedColumn, appointmentDateColumn, appointmentTimeColumn, appointmentEditColumn, appointmentDeleteColumn);

        loadAppointment();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void navigateToDashboard() {
        tabPane.getSelectionModel().select(dashboardTab);
    }

    @FXML
    public void navigateToAddAccount() {
        tabPane.getSelectionModel().select(addAccountTab);
    }

    @FXML
    public void navigateToStudent() {
        tabPane.getSelectionModel().select(studentTab);
    }

    @FXML
    public void navigateToAppointment() {
        tabPane.getSelectionModel().select(appointmentTab);
    }

    @FXML
    public void navigateToViolation() {
        tabPane.getSelectionModel().select(violationTab);
    }

    @FXML
    public void navigateToReport() {
        tabPane.getSelectionModel().select(reportTab);
    }

    @FXML
    public void navigateToAccount() {
        tabPane.getSelectionModel().select(accountTab);
    }

    @FXML
    public void navigateToAddAppointment() {
        tabPane.getSelectionModel().select(addAppointmentTab);
    }

    @FXML
    public void addAppointment(){
        String date = dateOfAppointment.getEditor().getText(); // Get the date as a string from the DatePicker editor
        String time = timeOfAppointment.getText();
        String studentName = studentComboBox.getValue();

        if (date.isEmpty() || time.isEmpty() || studentName == null) {
            CustomJDialog.getInstance().showDialog("Error", "Please fill all the blanks");
            return;
        }

        if (!studentObservableList.contains(studentName)) {
            CustomJDialog.getInstance().showDialog("Error", "Please select a valid student");
            return;
        }

        Map<String, Object> appointmentData = new HashMap<>();
        appointmentData.put("dateOfAppointment", date);
        appointmentData.put("time", time);
        appointmentData.put("studentName", studentName);

        AppointmentManagerSQL.getInstance().InsertAppointment(appointmentData);

        // Clear the fields
        dateOfAppointment.getEditor().clear(); // Clear the DatePicker editor
        timeOfAppointment.clear();
        studentComboBox.setValue("Select a student");

        loadAppointment();
    }
    @FXML
    public void navigateToEditAppointment(AppointmentModel appointment) {
        tabPane.getSelectionModel().select(editAppointmentTab);
    }

    @FXML
    public void navigateToAddViolation() {
        tabPane.getSelectionModel().select(addViolationTab);
    }

    @FXML
    public void navigateToEditViolation(ViolationModel violation) {
        tabPane.getSelectionModel().select(editViolationTab);
    }

    @FXML
    public void navigateToAddStudent() {
        tabPane.getSelectionModel().select(addStudentTab);
    }

    @FXML
    public void navigateToEditStudent(StudentModel students) {
        tabPane.getSelectionModel().select(editStudentTab);

        studentId = students.getId();

        editStudentName.setText(students.getStudentName());

        editGuardianName.setText(students.getGuardianName());

        editStudentPhoneNumber.setText(students.getPhoneNumber());

        editYearAndSection.setText(students.getYearAndSection());

        // Parse the date in M/d/yyyy format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        LocalDate parsedDate = LocalDate.parse(students.getBirthdate(), formatter);

        editBirthDate.setValue(parsedDate); // Set the parsed date to the DatePicker

    }

    @FXML
    public void navigateToEditAccount(AccountModel account) {
        // Navigate to the edit account tab
        tabPane.getSelectionModel().select(editAccountTab);

        // Populate the fields with the account data
        editUsernameField.setText(account.getUsername());

        editNameField.setText(account.getName());

        editPasswordField.setText(account.getPassword());

        editPasswordField2.setText(account.getPassword());

        editRoleComboBox.setValue(account.getRole());

        // Store the user ID for later use
        userId = account.getId();

    }

    @FXML
    public void updateStudent() {
        String student_name = editStudentName.getText();
        String date = editBirthDate.getEditor().getText(); // Get the date as a string from the DatePicker editor
        String guardian_name = editGuardianName.getText();
        String student_phoneNumber = editStudentPhoneNumber.getText();
        String year_and_section = editYearAndSection.getText();

        if (student_name.isEmpty() || date.isEmpty() || guardian_name.isEmpty() || student_phoneNumber.isEmpty() || year_and_section.isEmpty()) {
            CustomJDialog.getInstance().showDialog("Error", "Please fill all the blanks");
            return;
        }

        // Parse the date in M/d/yyyy format
        LocalDate parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            CustomJDialog.getInstance().showDialog("Error", "Invalid date format. Please use MM/dd/yyyy.");
            return;
        }

        Map<String, Object> studentData = new HashMap<>();
        studentData.put("studentName", student_name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        studentData.put("birthdate", parsedDate.format(formatter));
        studentData.put("guardian", guardian_name);
        studentData.put("phone", student_phoneNumber);
        studentData.put("yearAndSection", year_and_section);
        studentData.put("studentId", studentId);

        StudentManagerSQL.getInstance().updateStudent(studentData);

        // Clear the fields
        editStudentName.setText("");
        editGuardianName.setText("");
        editStudentPhoneNumber.setText("");
        editBirthDate.getEditor().clear(); // Clear the DatePicker editor
        editYearAndSection.setText("");
        loadStudent();

        // Navigate back to the student tab
        tabPane.getSelectionModel().select(studentTab);
    }

    @FXML
    public void onLogout() {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            // Handle logout logic here
            System.out.println("User logged out.");
            stage.close();
        }
    }



    @FXML
    public void onAddAccount(){

        String username = usernameField.getText();

        String fullname = nameField.getText();

        String password = passwordField1.getText();

        String confirm_password = passwordField2.getText();

        String role =  roleComboBox.getValue();

        if (username.isEmpty() || fullname.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirm_password)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (role == null || role.equals("Select a role")) {
            JOptionPane.showMessageDialog(null, "Please select a role.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.length() < 5) {
            JOptionPane.showMessageDialog(null, "Username must be at least 5 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.equals(username)) {
            JOptionPane.showMessageDialog(null, "Password cannot be the same as username.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fullname.length() < 3) {
            JOptionPane.showMessageDialog(null, "Full name must be at least 3 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("fullname", fullname);
        userData.put("password", password);
        userData.put("role", role);
        AccountManagerSQL.getInstance().AddAccount(userData);
        usernameField.clear();
        nameField.clear();
        passwordField1.clear();
        passwordField2.clear();
        roleComboBox.setValue("Select a role");
    }
    @FXML
    public void updateAccount(){
        String username = editUsernameField.getText();

        String fullname = editNameField.getText();

        String password = editPasswordField.getText();

        String confirm_password = editPasswordField2.getText();

        String role =  editRoleComboBox.getValue();

        if (username.isEmpty() || fullname.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirm_password)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (role == null || role.equals("Select a role")) {
            JOptionPane.showMessageDialog(null, "Please select a role.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.length() < 5) {
            JOptionPane.showMessageDialog(null, "Username must be at least 5 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.equals(username)) {
            JOptionPane.showMessageDialog(null, "Password cannot be the same as username.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fullname.length() < 3) {
            JOptionPane.showMessageDialog(null, "Full name must be at least 3 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("fullname", fullname);
        userData.put("password", password);
        userData.put("role", role);
        userData.put("user_id", userId);

        AccountManagerSQL.getInstance().updateAccount(userData);
        editUsernameField.clear();
        editNameField.clear();
        editPasswordField.clear();
        editPasswordField2.clear();
        editRoleComboBox.setValue("Select a role");
        tabPane.getSelectionModel().select(accountTab);
    }

    @FXML
    public void AddStudent() {
        String student_name = studentName.getText();
        String date = birthDate.getEditor().getText(); // Get the date as a string from the DatePicker editor
        String guardian_name = guardianName.getText();
        String student_phoneNumber = studentPhoneNumber.getText();
        String year_and_section = yearAndSection.getText();

        if (student_name.isEmpty() || date.isEmpty() || guardian_name.isEmpty() || student_phoneNumber.isEmpty() || year_and_section.isEmpty()) {
            CustomJDialog.getInstance().showDialog("Error", "Please fill all the blanks");
            return;
        }

        // Parse the date in M/d/yyyy format
        LocalDate parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            CustomJDialog.getInstance().showDialog("Error", "Invalid date format. Please use MM/dd/yyyy.");
            return;
        }

        Map<String, Object> studentData = new HashMap<>();
        studentData.put("studentName", student_name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        studentData.put("birthdate", parsedDate.format(formatter)); // Convert LocalDate to String
        studentData.put("guardian", guardian_name);
        studentData.put("phone", student_phoneNumber);
        studentData.put("yearAndSection", year_and_section);

        StudentManagerSQL.getInstance().InsertStudent(studentData);

        // Clear the fields
        studentName.setText("");
        guardianName.setText("");
        studentPhoneNumber.setText("");
        birthDate.getEditor().clear(); // Clear the DatePicker editor
        yearAndSection.setText("");
        loadStudent();
    }

    public void setRole(String role) {
        this.role = role;
        System.out.println("Role set to: " + role);
        if (role.equals("Admin")) {
            // Show admin-specific UI elements
            System.out.println("Admin UI elements shown.");

            accountBtn.setVisible(true);
        } else {
            // Handle other roles or show a default UI
            System.out.println("Default UI elements shown.");
            accountBtn.setVisible(false);
        }
    }

    // This will load the user data
    public void loadAccoountData() {
        accountTable.getItems().clear();
        accountModelObservableList.clear();
        accountTable.refresh();

        try {
            ObservableList<Map<String, Object>> accounts = AccountManagerSQL.getInstance().getAllAccounts();
            if (accounts != null && !accounts.isEmpty()) {
                for (Map<String, Object> account : accounts) {
                    String id = (String) account.get("user_id");
                    String username = (String) account.get("username");
                    String password = (String) account.get("password");
                    String name = (String) account.get("name");
                    String role = (String) account.get("role");

                    AccountModel accountModel = new AccountModel(id, username, password, name, role);
                    accountModelObservableList.add(accountModel);
                }
                accountTable.setItems(accountModelObservableList);
            } else {
                System.out.println("No accounts found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStudent() {
        studentTable.getItems().clear();
        studentModelObservableList.clear();
        studentTable.refresh();

        try {
            ObservableList<Map<String, Object>> students = StudentManagerSQL.getInstance().getStudent();
            if (students != null) {

                for (Map<String, Object> student : students) {
                    String id = (String) student.get("student_id");
                    String studentName = (String) student.get("student_name");
                    String birthdate = (String) student.get("birthdate");
                    String guardianName = (String) student.get("guardian");
                    String contactNumber = (String) student.get("contact_number");
                    String yearAndSection = (String) student.get("year_and_section");

                    StudentModel studentModel = new StudentModel(id, studentName, birthdate, guardianName, contactNumber, yearAndSection);
                    studentModelObservableList.add(studentModel);

                    // Populate the studentComboBox with student names
                    studentObservableList.add(studentName);
                }
                studentTable.setItems(studentModelObservableList);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void loadAppointment() {
        appointmentTable.getItems().clear();
        appointmentModelObservableList.clear();
        appointmentTable.refresh();

        try {
            ObservableList<Map<String, Object>> appointments = AppointmentManagerSQL.getInstance().getAllAppointments();
            if (appointments != null && !appointments.isEmpty()) {
                for (Map<String, Object> appointment : appointments) {
                    String id = (String) appointment.get("appointment_id");
                    String studentName = (String) appointment.get("student_name");
                    String date = (String) appointment.get("date_of_appointment");
                    String time = (String) appointment.get("time");
                    String dateSubmitted = (String) appointment.get("date_submitted");

                    AppointmentModel appointmentModel = new AppointmentModel(id, studentName, dateSubmitted, time, date);
                    appointmentModelObservableList.add(appointmentModel);
                }
                appointmentTable.setItems(appointmentModelObservableList);
            } else {
                System.out.println("No appointments found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

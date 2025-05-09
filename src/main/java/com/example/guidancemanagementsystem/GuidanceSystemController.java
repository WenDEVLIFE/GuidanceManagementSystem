package com.example.guidancemanagementsystem;

import database.AccountManagerSQL;
import database.StudentManagerSQL;
import database.ViolationManagerSQL;
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
import database.AppointmentManagerSQL;
import utils.ManageButton;
import utils.StudentButton;
import utils.ViolationButton;

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
    private TextField editAppointmentStudentName;

    @FXML
    private DatePicker editAppointmentDate;

    @FXML
    private TextField editAppointmentTime;

    @FXML
    private ComboBox<String>  selectedStudentComboBox;

    @FXML
    private DatePicker selectDateViolation;

    @FXML
    private ComboBox<String>  violationComboBox;

    @FXML
    private TextArea violationDescription;

    @FXML
    private TextField getEditStudentName;

    @FXML
    private DatePicker EditViolationDate;

    @FXML
    private TextArea EditViolationDescription;

    @FXML
    private ComboBox <String> editViolationComboBox;


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
    private TableView<AppointmentModel> appointmentTable1;


    @FXML
            private TableColumn<AppointmentModel, String> appointmentIdColumn;

    @FXML
            private TableColumn<AppointmentModel, String> appointmentStudentNameColumn;

    @FXML
    private TableColumn<AppointmentModel, String> appointmentStudentNameColumn1;

    @FXML
            private TableColumn<AppointmentModel, String> appointmentDateColumn;

    @FXML
    private TableColumn<AppointmentModel, String> appointmentDateColumn1;

    @FXML
    private TableColumn<AppointmentModel, String> dateSubmittedColumn;

    @FXML
            private TableColumn<AppointmentModel, String> appointmentTimeColumn;

    @FXML
    private TableColumn<AppointmentModel, String> appointmentTimeColumn1;

    @FXML
            private TableColumn<AppointmentModel, Void> appointmentEditColumn;

    @FXML
            private TableColumn<AppointmentModel, Void> appointmentDeleteColumn;

    @FXML
            private TableView<ViolationModel> violationTable;

    @FXML
            private TableColumn<ViolationModel, String> violationIdColumn;

    @FXML
            private TableColumn<ViolationModel, String> violationStudentNameColumn;

    @FXML
            private TableColumn<ViolationModel, String> violationDateColumn;

    @FXML
            private TableColumn<ViolationModel, String> violationTypeColumn;

    @FXML
            private TableColumn<ViolationModel, String> violationDescriptionColumn;

    @FXML
            private TableColumn<ViolationModel, Void> violationEditColumn;

    @FXML
            private TableColumn<ViolationModel, Void> violationDeleteColumn;

   ObservableList <AccountModel> accountModelObservableList = FXCollections.observableArrayList();

    ObservableList <StudentModel> studentModelObservableList = FXCollections.observableArrayList();

    ObservableList <String> studentObservableList = FXCollections.observableArrayList();

    ObservableList <AppointmentModel> appointmentModelObservableList = FXCollections.observableArrayList();

    ObservableList<ViolationModel> violationModelObservableList = FXCollections.observableArrayList();

    public void initialize(){

        ObservableList<String> roles = FXCollections.observableArrayList("Admin", "Student");
        ObservableList <String> violationList = FXCollections.observableArrayList(
                "Disobedience/Insubordination 1", "Cheating", "Profanity", "Unauthorized Collaboration", "Disruptin of Class",
                "Disrespect to Authority", "Disrespect to Peers", "Disruption of Class", "Inappropriate Language",
                "Inappropriate Behavior", "Inappropriate Attire", "Inappropriate Use of Technology", "Inappropriate Use of Social Media",
                "Inappropriate Use of School Property", "Inappropriate Use of School Facilities", "Inappropriate Use of School Resources",
                "Inappropriate Use of School Equipment", "Inappropriate Use of School Supplies", "Inappropriate Use of School Materials",
                "Inappropriate Use of School Resources", "Inappropriate Use of School Facilities", "Inappropriate Use of School Property", "Others");

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
        selectedStudentComboBox.setItems(studentObservableList);

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
        appointmentTable1.getColumns().clear();
        appointmentTable1.getItems().clear();

        appointmentStudentNameColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
        appointmentDateColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAppointmentDate()));
        appointmentTimeColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAppointmentTime()));

        appointmentTable1.getColumns().addAll(appointmentStudentNameColumn1, appointmentDateColumn1, appointmentTimeColumn1);

        loadAppointment();

        violationComboBox.setItems(violationList);

        violationTable.getColumns().clear();
        violationTable.getItems().clear();
        violationIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        violationStudentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
        violationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateSubmitted()));
        violationTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getViolationType()));
        violationDescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        violationEditColumn.setCellFactory(ViolationButton.forTableColumn("Edit", violationTable, violationModelObservableList, this));
        violationDeleteColumn.setCellFactory(ViolationButton.forTableColumn("Delete", violationTable, violationModelObservableList, this));

        violationTable.getColumns().addAll(violationIdColumn, violationStudentNameColumn, violationDateColumn, violationTypeColumn, violationDescriptionColumn, violationEditColumn, violationDeleteColumn);

        loadViolation();
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

        appointmentId = appointment.getId();

        editAppointmentStudentName.setText(appointment.getStudentName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate parsedDate = LocalDate.parse(appointment.getAppointmentDate(), formatter);
        editAppointmentDate.setValue(parsedDate);


        editAppointmentTime.setText(appointment.getAppointmentTime());


    }

    @FXML
    public void updateAppointment() {
        String date = editAppointmentDate.getEditor().getText(); // Get the date as a string from the DatePicker editor
        String time = editAppointmentTime.getText();
        String studentName = editAppointmentStudentName.getText();

        if (date.isEmpty() || time.isEmpty() || studentName.isEmpty()) {
            CustomJDialog.getInstance().showDialog("Error", "Please fill all the blanks");
            return;
        }

        LocalDate parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            CustomJDialog.getInstance().showDialog("Error", "Invalid date format. Please use MM/dd/yyyy.");
            return;
        }

        Map<String, Object> appointmentData = new HashMap<>();
        appointmentData.put("dateOfAppointment", parsedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"))); // Convert LocalDate to String
        appointmentData.put("time", time);
        appointmentData.put("studentName", studentName);
        appointmentData.put("appointmentId", appointmentId);

        AppointmentManagerSQL.getInstance().updateAppointment(appointmentData);

        editAppointmentStudentName.setText("");
        editAppointmentDate.getEditor().clear();
        editAppointmentTime.setText("");

        loadAppointment();

        tabPane.getSelectionModel().select(appointmentTab);
    }

    @FXML
    public void navigateToAddViolation() {
        tabPane.getSelectionModel().select(addViolationTab);
    }

    @FXML
    public void addViolation() {
        String date = selectDateViolation.getEditor().getText(); // Get the date as a string from the DatePicker editor
        String violation = violationComboBox.getValue();
        String description = violationDescription.getText();
        String studentName = selectedStudentComboBox.getValue();

        if (date.isEmpty() || violation == null || description.isEmpty() || studentName == null) {
            CustomJDialog.getInstance().showDialog("Error", "Please fill all the blanks");
            return;
        }

        LocalDate parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            CustomJDialog.getInstance().showDialog("Error", "Invalid date format. Please use MM/dd/yyyy.");
            return;
        }

        Map<String, Object> violationData = new HashMap<>();
        violationData.put("date_of_violation", parsedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy")));
        violationData.put("violation_type", violation);
        violationData.put("description", description);
        violationData.put("student_name", studentName);

        ViolationManagerSQL.getInstance().InsertViolation(violationData);

        selectDateViolation.getEditor().clear();
        violationDescription.clear();
        selectedStudentComboBox.setValue("Select a student");
        violationComboBox.setValue("Select a violation");

        loadStudent();
        loadViolation();
    }

    @FXML
    private void updateViolation() {
        String date = EditViolationDate.getEditor().getText();
        String violation = editViolationComboBox.getValue();
        String description = EditViolationDescription.getText();
        String studentName = getEditStudentName.getText();

        if (date.isEmpty() || violation == null || description.isEmpty() || studentName == null) {
            CustomJDialog.getInstance().showDialog("Error", "Please fill all the blanks");
            return;
        }

        LocalDate parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            CustomJDialog.getInstance().showDialog("Error", "Invalid date format. Please use MM/dd/yyyy.");
            return;
        }

        Map<String, Object> violationData = new HashMap<>();
        violationData.put("date_of_violation", parsedDate.format(DateTimeFormatter.ofPattern("M/d/yyyy")));
        violationData.put("violation_type", violation);
        violationData.put("description", description);
        violationData.put("student_name", studentName);
        violationData.put("violationId", violationId);

        ViolationManagerSQL.getInstance().updateViolation(violationData);

        EditViolationDescription.clear();
        getEditStudentName.clear();
        editViolationComboBox.setValue("Select a violation");

        loadViolation();

        tabPane.getSelectionModel().select(violationTab);
    }

    @FXML
    public void navigateToEditViolation(ViolationModel violation) {
        tabPane.getSelectionModel().select(editViolationTab);

        violationId = violation.getId();
        getEditStudentName.setText(violation.getStudentName());
        EditViolationDescription.setText(violation.getDescription());
        violationComboBox.setValue(violation.getViolationType());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate parsedDate = LocalDate.parse(violation.getDateSubmitted(), formatter);
        EditViolationDate.setValue(parsedDate);
        editViolationComboBox.setValue(violation.getViolationType());
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

        editStudentName.setText("");
        editGuardianName.setText("");
        editStudentPhoneNumber.setText("");
        editBirthDate.getEditor().clear();
        editYearAndSection.setText("");
        loadStudent();

        tabPane.getSelectionModel().select(studentTab);
    }

    @FXML
    public void onLogout() {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {

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
        String date = birthDate.getEditor().getText();
        String guardian_name = guardianName.getText();
        String student_phoneNumber = studentPhoneNumber.getText();
        String year_and_section = yearAndSection.getText();

        if (student_name.isEmpty() || date.isEmpty() || guardian_name.isEmpty() || student_phoneNumber.isEmpty() || year_and_section.isEmpty()) {
            CustomJDialog.getInstance().showDialog("Error", "Please fill all the blanks");
            return;
        }

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

        StudentManagerSQL.getInstance().InsertStudent(studentData);

        studentName.setText("");
        guardianName.setText("");
        studentPhoneNumber.setText("");
        birthDate.getEditor().clear();
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
        studentObservableList.clear();
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
        appointmentTable1.getItems().clear();
        appointmentModelObservableList.clear();
        appointmentTable.refresh();
        appointmentTable1.refresh();


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
                appointmentTable1.setItems(appointmentModelObservableList);
            } else {
                System.out.println("No appointments found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadViolation() {
        violationTable.getItems().clear();
        violationModelObservableList.clear();
        violationTable.refresh();

        try {
            ObservableList<Map<String, Object>> violations = ViolationManagerSQL.getInstance().getAllViolations();
            if (violations != null && !violations.isEmpty()) {
                for (Map<String, Object> violation : violations) {

                    String id = String.valueOf(violation.get("violation_id"));
                    String studentName = (String) violation.get("student_name");
                    String date = (String) violation.get("date_of_violation");
                    String type = (String) violation.get("violation_type");
                    String description = (String) violation.get("description");

                    ViolationModel violationModel = new ViolationModel(id, studentName, date, type, description);
                    violationModelObservableList.add(violationModel);
                }
                violationTable.setItems(violationModelObservableList);
            } else {
                System.out.println("No violations found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

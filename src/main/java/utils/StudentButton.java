package utils;


import com.example.guidancemanagementsystem.GuidanceSystemController;
import database.AccountManagerSQL;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.ReportModel;
import model.StudentModel;

public class StudentButton extends TableCell<StudentModel, Void> {

    private final Button button;

    private TableView<StudentModel> studentTable;

    private ObservableList<StudentModel> studentModelObservableList;

    public StudentButton(String buttonText, TableView<StudentModel> studentTable, ObservableList<StudentModel> studentModelObservableList, GuidanceSystemController controller) {
        this.button = new Button(buttonText);
        this.studentTable = studentTable;
        this.studentModelObservableList = studentModelObservableList;


        this.button.setOnAction(event -> {
            StudentModel selectItem = getTableRow().getItem();
            if (selectItem != null) {
                if (buttonText.equals("Delete")) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Delete Report");
                    alert.setContentText("Are you sure you want to delete this report?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            // Delete car from database

                            Platform.runLater(() -> {
                                AccountManagerSQL.getInstance().deleteUser(selectItem.getId());
                                studentModelObservableList.remove(selectItem);
                                studentTable.refresh();


                            });


                        }
                    });
                }

                else if (buttonText.equals("Edit")) {

                    Platform.runLater(() -> {
                        System.out.println("Edit");

                        // Go to buy product and get the controller
                        Platform.runLater(() -> {
                            controller.navigateToEditStudent(selectItem);
                        });


                    });


                }
            }
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            // Set style class for the button based on buttonText
            String buttonText = button.getText();
            if ("Delete".equals(buttonText)) {
                button.getStyleClass().add("deleteButton");
                button.setMaxSize(100, 100);
            } else if ("Edit".equals(buttonText)) {
                button.getStyleClass().add("editButton");
                button.setMaxSize(100, 100);
            }

            setGraphic(button);
        }
    }


    // Static method to create a callback for the table column
    public static Callback<TableColumn<StudentModel, Void>, TableCell<StudentModel, Void>> forTableColumn(String buttonText, TableView<StudentModel> reportTable, ObservableList<StudentModel> reportModelObservableList, GuidanceSystemController controller) {
        return param -> new StudentButton(buttonText, reportTable, reportModelObservableList, controller);
    }
}

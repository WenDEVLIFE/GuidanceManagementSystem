package utils;


import com.example.guidancemanagementsystem.GuidanceSystemController;
import database.AccountManagerSQL;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.AccountModel;
import model.AppointmentModel;

public class AppointmentButton extends TableCell<AppointmentModel, Void> {

    private final Button button;

    private TableView<AppointmentModel> appointmentTable;

    private ObservableList<AppointmentModel> appointmentModelObservableList;

    public AppointmentButton(String buttonText, TableView<AppointmentModel> appointmentTable, ObservableList<AppointmentModel> appointmentModelObservableList, GuidanceSystemController controller) {
        this.button = new Button(buttonText);
        this.appointmentTable = appointmentTable;
        this.appointmentModelObservableList = appointmentModelObservableList;


        this.button.setOnAction(event -> {
            AppointmentModel selectItem = getTableRow().getItem();
            if (selectItem != null) {
                if (buttonText.equals("Delete")) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Delete Category");
                    alert.setContentText("Are you sure you want to delete this category?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            // Delete car from database

                            Platform.runLater(() -> {
                                AccountManagerSQL.getInstance().deleteUser(selectItem.getId());
                                appointmentModelObservableList.remove(selectItem);
                                appointmentTable.refresh();


                            });


                        }
                    });


                }
                // Rent Car function
                else if (buttonText.equals("Edit")) {

                    Platform.runLater(() -> {
                        System.out.println("Edit");

                        // Go to buy product and get the controller
                        Platform.runLater(() -> {
                            controller.navigateToEditAppointment(selectItem);
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
    public static Callback<TableColumn<AppointmentModel, Void>, TableCell<AppointmentModel, Void>> forTableColumn(String buttonText, TableView<AppointmentModel> appointmentTable, ObservableList<AppointmentModel> accountModelObservableList, GuidanceSystemController controller) {
        return param -> new AppointmentButton(buttonText, appointmentTable, accountModelObservableList, controller);
    }
}
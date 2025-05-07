package utils;

import com.example.guidancemanagementsystem.GuidanceSystemController;
import database.AccountManagerSQL;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.StudentModel;
import model.ViolationModel;

public class ViolationButton extends TableCell<ViolationModel, Void> {

    private final Button button;

    private TableView<ViolationModel> violationTable;

    private ObservableList<ViolationModel> violationObservableList;

    public ViolationButton(String buttonText, TableView<ViolationModel> violationTable, ObservableList<ViolationModel> violationObservableList, GuidanceSystemController controller) {
        this.button = new Button(buttonText);
        this.violationTable = violationTable;
        this.violationObservableList = violationObservableList;


        this.button.setOnAction(event -> {
            ViolationModel selectItem = getTableRow().getItem();
            if (selectItem != null) {
                if (buttonText.equals("Delete")) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Delete Report");
                    alert.setContentText("Are you sure you want to delete this violation?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            // Delete car from database

                            Platform.runLater(() -> {
                                AccountManagerSQL.getInstance().deleteUser(selectItem.getId());
                                violationObservableList.remove(selectItem);
                                violationTable.refresh();


                            });


                        }
                    });
                }

                else if (buttonText.equals("Edit")) {

                    Platform.runLater(() -> {
                        System.out.println("Edit");

                        // Go to buy product and get the controller
                        Platform.runLater(() -> {
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
    public static Callback<TableColumn<ViolationModel, Void>, TableCell<ViolationModel, Void>> forTableColumn(String buttonText, TableView<ViolationModel> reportTable, ObservableList<ViolationModel> reportModelObservableList, GuidanceSystemController controller) {
        return param -> new ViolationButton(buttonText, reportTable, reportModelObservableList, controller);
    }
}

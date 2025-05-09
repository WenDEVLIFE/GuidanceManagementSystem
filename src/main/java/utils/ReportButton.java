package utils;

import com.example.guidancemanagementsystem.GuidanceSystemController;
import database.AccountManagerSQL;
import database.ReportManagerSQL;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.AccountModel;
import model.ReportModel;

public class ReportButton extends TableCell<ReportModel, Void> {

    private final Button button;

    private TableView<ReportModel> reportTable;

    private ObservableList<ReportModel> reportModelObservableList;

    public ReportButton(String buttonText, TableView<ReportModel> reportTable, ObservableList<ReportModel> reportModelObservableList, GuidanceSystemController controller) {
        this.button = new Button(buttonText);
        this.reportTable = reportTable;
        this.reportModelObservableList = reportModelObservableList;


        this.button.setOnAction(event -> {
            ReportModel selectItem = getTableRow().getItem();
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
                                ReportManagerSQL.getInstance().deleteReports(selectItem.getId());
                                reportModelObservableList.remove(selectItem);
                                reportTable.refresh();


                            });


                        }
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
    public static Callback<TableColumn<ReportModel, Void>, TableCell<ReportModel, Void>> forTableColumn(String buttonText, TableView<ReportModel> reportTable, ObservableList<ReportModel> reportModelObservableList, GuidanceSystemController controller) {
        return param -> new ReportButton(buttonText, reportTable, reportModelObservableList, controller);
    }
}

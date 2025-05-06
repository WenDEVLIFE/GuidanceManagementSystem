package utils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.AccountModel;

public class ManageButton extends TableCell<AccountModel, Void> {

    private final Button button;

    private TableView<AccountModel> ProductTable;

    private ObservableList<AccountModel> productModelObservableList;

    public ManageButton(String buttonText, TableView<AccountModel> ProductTable, ObservableList<AccountModel> productModelObservableList) {
        this.button = new Button(buttonText);
        this.ProductTable = ProductTable;
        this.productModelObservableList = productModelObservableList;


        this.button.setOnAction(event -> {
            AccountModel selectItem = getTableRow().getItem();
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
                              //  DeleteDatabaseInstance.getInstance().DeleteProducts(selectItem.getId());
                                productModelObservableList.remove(selectItem);
                                ProductTable.refresh();


                            });


                        }
                    });


                }
                // Rent Car function
                else if (buttonText.equals("Edit")) {

                    Platform.runLater(() -> {
                        System.out.println("Edit");

                        // Go to buy product and get the controller


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
                button.getStyleClass().add("button-red");
                button.setMaxSize(100, 100);
            } else if ("Edit".equals(buttonText)) {
                button.getStyleClass().add("button-blue");
                button.setMaxSize(100, 100);
            }

            setGraphic(button);
        }
    }


    // Static method to create a callback for the table column
    public static Callback<TableColumn<AccountModel, Void>, TableCell<AccountModel, Void>> forTableColumn(String buttonText, TableView<AccountModel> ProductTable, ObservableList<AccountModel> productModelObservableList) {
        return param -> new ManageButton(buttonText, ProductTable, productModelObservableList);
    }
}
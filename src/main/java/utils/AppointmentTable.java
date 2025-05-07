package utils;


import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.AccountModel;
import model.AppointmentModel;

public class AppointmentTable {

    public static Callback<TableColumn<AppointmentModel, String>, TableCell<AppointmentModel, String>> cellFactoryForString() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<AppointmentModel, Integer>, TableCell<AppointmentModel, Integer>> cellFactoryForInteger() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<AppointmentModel, String>, TableCell<AppointmentModel, String>> createCenteredStringCell() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setAlignment(Pos.CENTER);
            }
        };
    }
}
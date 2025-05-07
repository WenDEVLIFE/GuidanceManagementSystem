package utils;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.StudentModel;
import model.ViolationModel;

public class ViolationTable {

    public static Callback<TableColumn<ViolationModel, String>, TableCell<ViolationModel, String>> cellFactoryForString() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<ViolationModel, Integer>, TableCell<ViolationModel, Integer>> cellFactoryForInteger() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<ViolationModel, String>, TableCell<ViolationModel, String>> createCenteredStringCell() {
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

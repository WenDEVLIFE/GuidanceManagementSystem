package utils;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.AppointmentModel;
import model.ReportModel;

public class ReportTable {

    public static Callback<TableColumn<ReportModel, String>, TableCell<ReportModel, String>> cellFactoryForString() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<ReportModel, Integer>, TableCell<ReportModel, Integer>> cellFactoryForInteger() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
                setAlignment(Pos.CENTER);
            }
        };
    }

    public static Callback<TableColumn<ReportModel, String>, TableCell<ReportModel, String>> createCenteredStringCell() {
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

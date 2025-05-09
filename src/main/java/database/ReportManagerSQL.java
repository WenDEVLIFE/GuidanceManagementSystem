package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class ReportManagerSQL {

    private  static volatile ReportManagerSQL instance;

    public static ReportManagerSQL getInstance(){
        if (instance == null) {
            synchronized (ReportManagerSQL.class) {
                if (instance == null) {
                    instance = new ReportManagerSQL();
                }
            }
        }
        return instance;
    }


    public ObservableList<Map<String, Object>> getAllReports() {
        String sql = "SELECT * FROM reports";
        ObservableList<Map<String, Object>> reportList = FXCollections.observableArrayList();
        try (var conn = MYSQLConnection.getConnection();
             var pstmt = conn.prepareStatement(sql);
             var rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> reportData = Map.of(
                        "report_id", rs.getInt("report_id"),
                        "description", rs.getString("description"),
                        "date", rs.getString("date"),
                        "time", rs.getString("time")
                );
                reportList.add(reportData);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return reportList;
    }

    public void deleteReports(String id) {
        String sql = "DELETE FROM reports WHERE report_id = ?";
        try (var conn = MYSQLConnection.getConnection();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Report deleted successfully.");
            } else {
                System.out.println("Failed to delete report.");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int getReportCount() {
        String sql = "SELECT COUNT(*) AS report_count FROM reports";
        try (var conn = MYSQLConnection.getConnection();
             var pstmt = conn.prepareStatement(sql);
             var rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("report_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

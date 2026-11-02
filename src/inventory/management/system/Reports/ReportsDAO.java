package inventory.management.system.Reports;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import inventory.management.system.DBConnection;

public class ReportsDAO {

    public List<ReportEntity> getSummaryReports() {
        List<ReportEntity> reports = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            // Count products (from products table)
            String sql1 = "SELECT COUNT(*) AS product_count FROM products";
            try (PreparedStatement stmt = conn.prepareStatement(sql1);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reports.add(new ReportEntity("Total Products", rs.getInt("product_count")));
                }
            }

            // Count transactions (from transactions table)
            String sql2 = "SELECT COUNT(*) AS transaction_count FROM transactions";
            try (PreparedStatement stmt = conn.prepareStatement(sql2);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reports.add(new ReportEntity("Total Transactions", rs.getInt("transaction_count")));
                }
            }

            // ✅ Total sales value (price × quantity from products + transactions join)
            String sql3 = "SELECT SUM(p.price * t.quantity) AS total_sales "
                        + "FROM transactions t "
                        + "JOIN products p ON t.product_id = p.product_id";
            try (PreparedStatement stmt = conn.prepareStatement(sql3);
                ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reports.add(new ReportEntity("Total Sales Value", rs.getDouble("total_sales")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
}

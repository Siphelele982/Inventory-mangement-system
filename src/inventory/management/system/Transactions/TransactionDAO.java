package inventory.management.system.Transactions;

import inventory.management.system.DBConnection;
import inventory.management.system.Transactions.TransactionEntity;
import java.sql.*;
import java.util.*;

public class TransactionDAO {
    
    public void addTransaction(TransactionEntity transaction) {
        String sql = "INSERT INTO transactions (product_id, user_id, type, quantity,total_price) VALUES (?, ?, ?, ?,?)";
        String updateStockSql = "";

        if (transaction.getType().equalsIgnoreCase("IN")) {
            updateStockSql = "UPDATE products SET quantity = quantity + ? WHERE product_id = ?";
        } else if (transaction.getType().equalsIgnoreCase("OUT")) {
            updateStockSql = "UPDATE products SET quantity = quantity - ? WHERE product_id = ?";
        }

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, transaction.getProductId());
                stmt.setInt(2, transaction.getUserId());
                stmt.setString(3, transaction.getType());
                stmt.setInt(4, transaction.getQuantity());
                stmt.setDouble(5,transaction.getTotalPrice());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt2 = conn.prepareStatement(updateStockSql)) {
                stmt2.setInt(1, transaction.getQuantity());
                stmt2.setInt(2, transaction.getProductId());
                stmt2.executeUpdate();
            }

            conn.commit(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

    public List<TransactionEntity> getAllTransactions() {
        List<TransactionEntity> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                TransactionEntity t = new TransactionEntity(
                    rs.getInt("transaction_id"),
                    rs.getInt("product_id"),
                    rs.getInt("user_id"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getDouble("total_price"),
                    rs.getTimestamp("date")
                );
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transactionId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateTransaction(TransactionEntity transaction) {
        String sql = "UPDATE transactions SET product_id = ?, user_id = ?, type = ?, quantity = ?, total_price = ?, date = CURRENT_TIMESTAMP WHERE transaction_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transaction.getProductId());
            stmt.setInt(2, transaction.getUserId());
            stmt.setString(3, transaction.getType());
            stmt.setInt(4, transaction.getQuantity());
            stmt.setDouble(5, transaction.getTotalPrice());
            stmt.setInt(6, transaction.getTransactionId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


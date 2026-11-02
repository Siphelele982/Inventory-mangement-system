package inventory.management.system.Suppliers;


import inventory.management.system.DBConnection;
import java.sql.*;
import java.util.*;
import inventory.management.system.Suppliers.SupplierEntity;

public class SupplierDAO {
    public void addSupplier(SupplierEntity supplier) {
        String sql = "INSERT INTO suppliers (name, contact_info,email,phone) VALUES (?, ?,?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContactInfo());
            stmt.setString(3,supplier.getEmail());
            stmt.setString(4,supplier.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<SupplierEntity> getAllSuppliers() {
        List<SupplierEntity> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                SupplierEntity s = new SupplierEntity(
                    rs.getInt("supplier_id"),
                    rs.getString("name"),
                    rs.getString("contact_info"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                suppliers.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
    public void updateSupplier(SupplierEntity supplier) {
        String sql = "UPDATE suppliers SET name = ?, contact_info = ?, email = ?, phone = ? WHERE supplier_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContactInfo());
            stmt.setString(3, supplier.getEmail());
            stmt.setString(4, supplier.getPhone());
            stmt.setInt(5, supplier.getSupplierId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSupplier(int supplierId) {
        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, supplierId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


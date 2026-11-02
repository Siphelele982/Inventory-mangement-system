package inventory.management.system.Products;

import inventory.management.system.DBConnection;
import inventory.management.system.Products.ProductEntity;
import java.sql.*;
import java.util.*;

public class ProductDAO {
    public void addProduct(ProductEntity product) {
        String sql = "INSERT INTO products (name, category, price, quantity, supplier_id) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, product.getName());
                stmt.setString(2, product.getCategory());
                stmt.setDouble(3, product.getPrice());
                stmt.setInt(4, product.getQuantity());
                stmt.setInt(5, product.getSupplierId());
            
                stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductEntity p = new ProductEntity(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getInt("supplier_id")
            );
                products.add(p);
            }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return products;
    }
    
    public void updateProduct(ProductEntity product) {
        String sql = "UPDATE products SET name = ?, category = ?, price = ?, quantity = ?, supplier_id = ? WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getSupplierId());
            stmt.setInt(6, product.getProductId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Delete product by ID
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // For dropdowns (productId → name)
public Map<Integer, String> getAllProductsMap() {
    Map<Integer, String> products = new HashMap<>();
    String sql = "SELECT product_id, name FROM products";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            products.put(rs.getInt("product_id"), rs.getString("name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return products;
}
 
    public double getProductPrice(int productId) {
        double price = 0.0;
       String sql = "SELECT price FROM products WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, productId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return price;
}
    public double getPrice(int productId) {
        double price = 0.0;
        String sql = "SELECT price FROM products WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }


}



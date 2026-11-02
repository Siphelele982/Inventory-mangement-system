package inventory.management.system.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ProductsPanel extends JPanel {
    private JTable productTable;
    private DefaultTableModel tableModel;

    public ProductsPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Name", "Category", "Price", "Quantity", "Supplier ID"}, 0
        );
        productTable = new JTable(tableModel);

        loadProducts();

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = new JButton("Add Product");
        JButton editBtn = new JButton("Edit Product");
        JButton deleteBtn = new JButton("Delete Product");

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField categoryField = new JTextField();
            JTextField priceField = new JTextField();
            JTextField quantityField = new JTextField();
            JTextField supplierField = new JTextField();

            Object[] fields = {
                "Name:", nameField,
                "Category:", categoryField,
                "Price:", priceField,
                "Quantity:", quantityField,
                "Supplier ID:", supplierField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Add Product", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int quantity = Integer.parseInt(quantityField.getText().trim());
                int supplierId = Integer.parseInt(supplierField.getText().trim());

                ProductDAO dao = new ProductDAO();
                dao.addProduct(new ProductEntity(0, name, category, price, quantity, supplierId));

                loadProducts();
                JOptionPane.showMessageDialog(this, "Product added successfully!");
            }
        });

        editBtn.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                int productId = (int) tableModel.getValueAt(selectedRow, 0);
                String name = (String) tableModel.getValueAt(selectedRow, 1);
                String category = (String) tableModel.getValueAt(selectedRow, 2);
                double price = (double) tableModel.getValueAt(selectedRow, 3);
                int quantity = (int) tableModel.getValueAt(selectedRow, 4);
                int supplierId = (int) tableModel.getValueAt(selectedRow, 5);

                JTextField nameField = new JTextField(name);
                JTextField categoryField = new JTextField(category);
                JTextField priceField = new JTextField(String.valueOf(price));
                JTextField quantityField = new JTextField(String.valueOf(quantity));
                JTextField supplierField = new JTextField(String.valueOf(supplierId));

                Object[] fields = {
                    "Name:", nameField,
                    "Category:", categoryField,
                    "Price:", priceField,
                    "Quantity:", quantityField,
                    "Supplier ID:", supplierField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Product", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String newName = nameField.getText().trim();
                    String newCategory = categoryField.getText().trim();
                    double newPrice = Double.parseDouble(priceField.getText().trim());
                    int newQuantity = Integer.parseInt(quantityField.getText().trim());
                    int newSupplierId = Integer.parseInt(supplierField.getText().trim());

                    ProductDAO dao = new ProductDAO();
                    dao.updateProduct(new ProductEntity(productId, newName, newCategory, newPrice, newQuantity, newSupplierId));

                    loadProducts();
                    JOptionPane.showMessageDialog(this, "Product updated successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product to edit.");
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                int productId = (int) tableModel.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete product ID " + productId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    ProductDAO dao = new ProductDAO();
                    dao.deleteProduct(productId);
                    loadProducts();
                    JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product to delete.");
            }
        });
    }

    private void loadProducts() {
        ProductDAO dao = new ProductDAO();
        List<ProductEntity> products = dao.getAllProducts(); 

        tableModel.setRowCount(0); 
        for (ProductEntity p : products) {
            tableModel.addRow(new Object[]{
                p.getProductId(),
                p.getName(),
                p.getCategory(),
                p.getPrice(),
                p.getQuantity(),
                p.getSupplierId()
            });
        }
    }
}

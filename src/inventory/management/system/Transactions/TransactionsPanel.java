package inventory.management.system.Transactions;

import inventory.management.system.Products.ProductDAO;
import inventory.management.system.Users.UserDAO;
import inventory.management.system.Transactions.TransactionDAO;
import inventory.management.system.Transactions.TransactionEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TransactionsPanel extends JPanel {
    private JTable transactionTable;
    private DefaultTableModel tableModel;

    public TransactionsPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Product ID", "User ID", "Type", "Quantity", "Total Price", "Date"}, 0
        );
        transactionTable = new JTable(tableModel);

        loadTransactions();

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = new JButton("Add Transaction");
        JButton editBtn = new JButton("Edit Transaction");
        JButton deleteBtn = new JButton("Delete Transaction");

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            
          // Load products into dropdown
            ProductDAO productsDAO = new ProductDAO();
            Map<Integer, String> products = productsDAO.getAllProductsMap(); 
            JComboBox<String> productComboBox = new JComboBox<>();
            for (Map.Entry<Integer, String> entry : products.entrySet()) {
                productComboBox.addItem(entry.getKey() + " - " + entry.getValue());
            }

            UserDAO usersDAO = new UserDAO();
            Map<Integer, String> users = usersDAO.getAllUsersMap(); 
            JComboBox<String> userComboBox = new JComboBox<>();
            for (Map.Entry<Integer, String> entry : users.entrySet()) {
                userComboBox.addItem(entry.getKey() + " - " + entry.getValue());
            }

            JTextField typeField = new JTextField();
            JTextField quantityField = new JTextField();

            Object[] fields = {
                "Product:", productComboBox,
                "User:", userComboBox,
                "Type (IN/OUT):", typeField,
                "Quantity:", quantityField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Add Transaction", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String selectedProduct = (String) productComboBox.getSelectedItem();
                int productId = Integer.parseInt(selectedProduct.split(" - ")[0]);

                String selectedUser = (String) userComboBox.getSelectedItem();
                int userId = Integer.parseInt(selectedUser.split(" - ")[0]);

                String type = typeField.getText().trim();
                int quantity = Integer.parseInt(quantityField.getText().trim());

                double unitPrice = productsDAO.getProductPrice(productId);
                double totalPrice = unitPrice * quantity;

                TransactionDAO dao = new TransactionDAO();
                dao.addTransaction(new TransactionEntity(0, productId, userId, type, quantity, totalPrice, null));

                loadTransactions();
                JOptionPane.showMessageDialog(this, "Transaction added successfully!");
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = transactionTable.getSelectedRow();
            if (selectedRow >= 0) {
                int transactionId = (int) tableModel.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete transaction ID " + transactionId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    TransactionDAO dao = new TransactionDAO();
                    dao.deleteTransaction(transactionId);
                    loadTransactions();
                    JOptionPane.showMessageDialog(this, "Transaction deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a transaction to delete.");
            }
        });
    
       editBtn.addActionListener(e -> {
            int selectedRow = transactionTable.getSelectedRow();
            if (selectedRow >= 0) {
                int transactionId = (int) tableModel.getValueAt(selectedRow, 0);
                int productId = (int) tableModel.getValueAt(selectedRow, 1);
                int userId = (int) tableModel.getValueAt(selectedRow, 2);
                String type = (String) tableModel.getValueAt(selectedRow, 3);
                int quantity = (int) tableModel.getValueAt(selectedRow, 4);
                double totalPrice = (double) tableModel.getValueAt(selectedRow, 5);

                JTextField typeField = new JTextField(type);
                JTextField quantityField = new JTextField(String.valueOf(quantity));

                Object[] fields = {
                    "Type (IN/OUT):", typeField,
                    "Quantity:", quantityField
                };

            int option = JOptionPane.showConfirmDialog(this, fields, "Edit Transaction", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newType = typeField.getText().trim();
                int newQuantity = Integer.parseInt(quantityField.getText().trim());

                ProductDAO productsDAO = new ProductDAO();
                double unitPrice = productsDAO.getProductPrice(productId);
                double newTotalPrice = unitPrice * newQuantity;

                TransactionDAO dao = new TransactionDAO();
                dao.updateTransaction(new TransactionEntity(transactionId, productId, userId, newType, newQuantity, newTotalPrice, null));

                loadTransactions();
                JOptionPane.showMessageDialog(this, "Transaction updated successfully!");
           }
           }else {
            JOptionPane.showMessageDialog(this, "Please select a transaction to edit.");
        }
    });
    }
    
       private void loadTransactions() {
        TransactionDAO dao = new TransactionDAO();
        List<TransactionEntity> transactions = dao.getAllTransactions();

        tableModel.setRowCount(0); 
        for (TransactionEntity t : transactions) {
            tableModel.addRow(new Object[]{
                t.getTransactionId(),
                t.getProductId(),
                t.getUserId(),
                t.getType(),
                t.getQuantity(),
                t.getTotalPrice(),
                t.getDate()
            });
        }
    }
}

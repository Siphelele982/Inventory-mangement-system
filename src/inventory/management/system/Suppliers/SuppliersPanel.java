package inventory.management.system.Suppliers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import inventory.management.system.Suppliers.SupplierDAO;
import java.util.function.Supplier;

public class SuppliersPanel extends JPanel {
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public SuppliersPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Name", "Contact", "Email", "Phone"}, 0
        );
        supplierTable = new JTable(tableModel);

        loadSuppliers();

        JScrollPane scrollPane = new JScrollPane(supplierTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = new JButton("Add Supplier");
        JButton editBtn = new JButton("Edit Supplier");
        JButton deleteBtn = new JButton("Delete Supplier");

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField contactField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField phoneField = new JTextField();

            Object[] fields = {
                "Name:", nameField,
                "Contact Person:", contactField,
                "Email:", emailField,
                "Phone:", phoneField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Add Supplier", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText().trim();
                String contact = contactField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();

                SupplierDAO dao = new SupplierDAO();
                dao.addSupplier(new SupplierEntity(0, name, contact, email, phone));

                loadSuppliers();
                JOptionPane.showMessageDialog(this, "Supplier added successfully!");
            }
        });

        // Edit Supplier
        editBtn.addActionListener(e -> {
            int selectedRow = supplierTable.getSelectedRow();
            if (selectedRow >= 0) {
                int supplierId = (int) tableModel.getValueAt(selectedRow, 0);
                String name = (String) tableModel.getValueAt(selectedRow, 1);
                String contact = (String) tableModel.getValueAt(selectedRow, 2);
                String email = (String) tableModel.getValueAt(selectedRow, 3);
                String phone = (String) tableModel.getValueAt(selectedRow, 4);

                JTextField nameField = new JTextField(name);
                JTextField contactField = new JTextField(contact);
                JTextField emailField = new JTextField(email);
                JTextField phoneField = new JTextField(phone);

                Object[] fields = {
                    "Name:", nameField,
                    "Contact Person:", contactField,
                    "Email:", emailField,
                    "Phone:", phoneField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Supplier", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String newName = nameField.getText().trim();
                    String newContact = contactField.getText().trim();
                    String newEmail = emailField.getText().trim();
                    String newPhone = phoneField.getText().trim();

                    SupplierDAO dao = new SupplierDAO();
                    dao.updateSupplier(new SupplierEntity(supplierId, newName, newContact, newEmail, newPhone));

                    loadSuppliers();
                    JOptionPane.showMessageDialog(this, "Supplier updated successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a supplier to edit.");
            }
        });

        // Delete Supplier
        deleteBtn.addActionListener(e -> {
            int selectedRow = supplierTable.getSelectedRow();
            if (selectedRow >= 0) {
                int supplierId = (int) tableModel.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete supplier ID " + supplierId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    SupplierDAO dao = new SupplierDAO();
                    dao.deleteSupplier(supplierId);
                    loadSuppliers();
                    JOptionPane.showMessageDialog(this, "Supplier deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a supplier to delete.");
            }
        });
    }

    private void loadSuppliers() {
        SupplierDAO dao = new SupplierDAO();
        List<SupplierEntity> suppliers = dao.getAllSuppliers();

        tableModel.setRowCount(0); // clear existing rows
        for (SupplierEntity s : suppliers) {
            tableModel.addRow(new Object[]{
                s.getSupplierId(),
                s.getName(),
                s.getContactInfo(),
                s.getEmail(),
                s.getPhone()
            });
        }
    }
}


package inventory.management.system.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementPanel extends JPanel {
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserManagementPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Username","Password","Email","Role"}, 0
        );
        userTable = new JTable(tableModel);

        loadUsers();

        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = new JButton("Add User");
        JButton editBtn = new JButton("Edit User");
        JButton deleteBtn = new JButton("Delete User");

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        
        addBtn.addActionListener(e -> {
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField emailField = new JTextField();
            JTextField roleField = new JTextField();
            

            Object[] fields = {
                "Username:", usernameField,
                "Password:", passwordField,
                "Email:", emailField,
                "Role (Admin/Staff):", roleField,
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Add User", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText().trim();
                String role = roleField.getText().trim();

                UserDAO dao = new UserDAO();
                dao.addUser(new UserEntity(0, username,password,email,role));

                loadUsers();
                JOptionPane.showMessageDialog(this, "User added successfully!");
            }
        });

        editBtn.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                int userId = (int) tableModel.getValueAt(selectedRow, 0);
                String username = (String) tableModel.getValueAt(selectedRow, 1);
                String password = (String) tableModel.getValueAt(selectedRow, 2);
                String email = (String) tableModel.getValueAt(selectedRow, 3);
                String role = (String) tableModel.getValueAt(selectedRow, 4);

                JTextField usernameField = new JTextField(username);
                JPasswordField passwordField = new JPasswordField(password);
                JTextField emailField = new JTextField(email);
                JTextField roleField = new JTextField(role);


                Object[] fields = {
                    "Username:", usernameField,
                    "password:",passwordField,
                    "Email:", emailField,
                    "Role (Admin/Staff):", roleField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit User", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String newUsername = usernameField.getText().trim();
                    String newPassword =  passwordField.getText().trim();
                    String newEmail = emailField.getText().trim();
                    String newRole = roleField.getText().trim();

                    UserDAO dao = new UserDAO();
                    dao.updateUser(new UserEntity(userId, newUsername, newPassword ,newEmail, newRole));

                    loadUsers();
                    JOptionPane.showMessageDialog(this, "User updated successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to edit.");
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                int userId = (int) tableModel.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete user ID " + userId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    UserDAO dao = new UserDAO();
                    dao.deleteUser(userId);
                    loadUsers();
                    JOptionPane.showMessageDialog(this, "User deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            }
        });
    }

    private void loadUsers() {
        UserDAO dao = new UserDAO();
        List<UserEntity> users = dao.getAllUsers();

        tableModel.setRowCount(0); // clear existing rows
        for (UserEntity u : users) {
            tableModel.addRow(new Object[]{
                u.getUserId(),
                u.getUsername(),
                u.getPassword(),
                u.getEmail(),
                u.getRole()
            });
        }
    }
}


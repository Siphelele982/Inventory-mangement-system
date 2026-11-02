package inventory.management.system.Users;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegistrationForm() {
        setTitle("Register New User");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("Create New Account");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(heading, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);

        emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(200,20));
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 3;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Role:"), gbc);

        String[] roles = {"ADMIN", "STAFF"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(roleBox, gbc);

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);
        
        JCheckBox showPassword = new JCheckBox("Show Password");
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(showPassword, gbc);
        

        add(panel);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String email = emailField.getText().trim();
            String role = (String) roleBox.getSelectedItem();
            
            
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!");
                return;
            }

            UserDAO userDAO = new UserDAO();
            UserEntity user = new UserEntity(0, username, password,email, role);
            userDAO.addUser(user);

            JOptionPane.showMessageDialog(this, "User registered successfully!");
            new LoginForm().setVisible(true);
            dispose();
        });

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
    }
}

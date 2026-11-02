package inventory.management.system.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import inventory.management.system.Users.UserDAO;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;
    private JCheckBox showPassword;

    public LoginForm() {
        setTitle("Inventory System - Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("Inventory Management System");
        heading.setFont(new Font("Arial",Font.BOLD,18));
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(heading, gbc);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(10);
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        gbc.gridx = 1; 
        gbc.gridy = 1;
        panel.add(usernameField, gbc);
        
        //PASSWORD LABELS AND FIELD
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(10);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);
        
        showPassword = new JCheckBox("Show password");
        showPassword.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(showPassword, gbc);

        
        //LOGIN BUTTON
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);
        
        //SIGN BUTTON
        signupButton = new JButton("Sign Up");
        signupButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(signupButton, gbc);

        
        panel.add(heading);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));       
        panel.add(passwordLabel); 
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(new JLabel()); 
        panel.add(loginButton);
        panel.add(signupButton);
        panel.add(showPassword);
        
        add(panel);
        
        //ACTIONS
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                UserDAO userDAO = new UserDAO();
                UserEntity user = userDAO.login(username, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(null, 
                        "Welcome " + user.getUsername() + " (" + user.getRole() + ")");
                    
                    if (user.getRole().equalsIgnoreCase("ADMIN")) {
                        new AdminDashBoardUI(user).setVisible(true);
                    } else {
                        new StaffDashBoardUI(user).setVisible(true);
                    }
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            }
        });
        signupButton.addActionListener(e -> {
            new RegistrationForm().setVisible(true);
            dispose(); // CLOSE WINDOW
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
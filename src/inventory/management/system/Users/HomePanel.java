package inventory.management.system.Users;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel(UserEntity user) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); 

        JPanel header = new JPanel();
        header.setBackground(new Color(0, 150, 136)); // teal
        JLabel title = new JLabel("Inventory Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);

        JLabel welcomeLabel = new JLabel("Welcome " + user.getUsername() + " [" + user.getRole() + "]");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Description
        JLabel description = new JLabel("<html><center>Manage Products, Transactions, and more.<br>Use the navigation buttons to get started.</center></html>");
        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setHorizontalAlignment(SwingConstants.CENTER);

        add(header, BorderLayout.NORTH);
        add(welcomeLabel, BorderLayout.CENTER);
        add(description, BorderLayout.SOUTH);
    }
}


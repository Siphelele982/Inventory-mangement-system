package inventory.management.system.Users;

import inventory.management.system.Products.ProductsPanel;
import inventory.management.system.Transactions.TransactionsPanel;
import javax.swing.*;
import java.awt.*;

public class StaffDashBoardUI extends JFrame {
    private UserEntity loggedInUser;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public StaffDashBoardUI(UserEntity user) {
        this.loggedInUser = user;

        setTitle("Staff Dashboard - Inventory System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Inventory Management System", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(0, 150, 136)); // teal
        header.setForeground(Color.WHITE);
        add(header, BorderLayout.NORTH);

        // Navigation buttons
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton homeBtn = new JButton("Home");
        JButton productsBtn = new JButton("Products");
        JButton transactionsBtn = new JButton("Transactions");
        JButton logoutBtn = new JButton("Logout");

        Dimension btnSize = new Dimension(140, 35);
        for (JButton btn : new JButton[]{homeBtn, productsBtn, transactionsBtn, logoutBtn}) {
            btn.setPreferredSize(btnSize);
            navPanel.add(btn);
        }

        add(navPanel, BorderLayout.CENTER);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panels
        mainPanel.add(new HomePanel(user), "HOME");
        mainPanel.add(new ProductsPanel(), "PRODUCTS");
        mainPanel.add(new TransactionsPanel(), "TRANSACTIONS");

        add(mainPanel, BorderLayout.SOUTH);

        homeBtn.addActionListener(e -> 
                cardLayout.show(mainPanel, "HOME"));
        productsBtn.addActionListener(e ->
                cardLayout.show(mainPanel, "PRODUCTS"));
        transactionsBtn.addActionListener(e -> 
                cardLayout.show(mainPanel, "TRANSACTIONS"));
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });

        // Show home by default
        cardLayout.show(mainPanel, "HOME");
    }
}

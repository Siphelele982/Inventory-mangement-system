package inventory.management.system.Users;

import inventory.management.system.Products.ProductsPanel;
import inventory.management.system.Reports.ReportsPanel;
import inventory.management.system.Suppliers.SuppliersPanel;
import inventory.management.system.Transactions.TransactionsPanel;
import javax.swing.*;
import java.awt.*;

public class AdminDashBoardUI extends JFrame {
    private UserEntity loggedInUser;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public AdminDashBoardUI(UserEntity user) {
        this.loggedInUser = user;

        setTitle("Admin Dashboard - Inventory System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel header = new JLabel("Inventory Management System - Admin", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(63, 81, 181)); // indigo
        header.setForeground(Color.WHITE);
        add(header, BorderLayout.NORTH);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(240, 240, 240)); // light gray

        Dimension btnSize = new Dimension(180, 40);

        JButton homeBtn = new JButton("Home");
        JButton productsBtn = new JButton("Products");
        JButton transactionsBtn = new JButton("Transactions");
        JButton reportsBtn = new JButton("Reports");
        JButton suppliersBtn = new JButton("Suppliers");
        JButton usersBtn = new JButton("User Management");
        JButton logoutBtn = new JButton("Logout");

        for (JButton btn : new JButton[]{homeBtn, productsBtn, transactionsBtn, reportsBtn, suppliersBtn, usersBtn, logoutBtn}) {
            btn.setMaximumSize(btnSize);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setBackground(new Color(63, 81, 181)); // indigo
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            sidebar.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
            sidebar.add(btn);
        }

        add(sidebar, BorderLayout.WEST);

        // Main content area with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new HomePanel(user), "HOME");
        mainPanel.add(new ProductsPanel(), "PRODUCTS");
        mainPanel.add(new TransactionsPanel(), "TRANSACTIONS");
        mainPanel.add(new ReportsPanel(), "REPORTS");
        mainPanel.add(new SuppliersPanel(), "SUPPLIERS");
        mainPanel.add(new UserManagementPanel(), "USERS");

        add(mainPanel, BorderLayout.CENTER);

        homeBtn.addActionListener(e -> 
                cardLayout.show(mainPanel, "HOME"));
        productsBtn.addActionListener(e -> 
                cardLayout.show(mainPanel, "PRODUCTS"));
        transactionsBtn.addActionListener(e -> 
                cardLayout.show(mainPanel, "TRANSACTIONS"));
        reportsBtn.addActionListener(e -> 
                cardLayout.show(mainPanel, "REPORTS"));
        suppliersBtn.addActionListener(e -> 
                cardLayout.show(mainPanel, "SUPPLIERS"));
        usersBtn.addActionListener(e ->
                cardLayout.show(mainPanel, "USERS"));
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });

        cardLayout.show(mainPanel, "HOME");
    }
}

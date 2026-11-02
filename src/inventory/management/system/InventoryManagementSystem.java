package inventory.management.system;

import inventory.management.system.Users.LoginForm;
import javax.swing.SwingUtilities;


public class InventoryManagementSystem{
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}




package ui.panels;





import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import dao.FarmerDAO;
import model.Farmer;

@SuppressWarnings("unused")
public class FarmerOnboardingPanel extends JPanel {
    private FarmerDAO farmerDAO = new FarmerDAO();
    private JTextField nameField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JTextField mobileField = new JTextField(20);
    private JTextField addressField = new JTextField(20);
    private JButton registerButton = new JButton("Register");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton loginButton = new JButton("Login");

    public FarmerOnboardingPanel(JFrame mainFrame) {
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Mobile:"));
        add(mobileField);
        add(new JLabel("Address:"));
        add(addressField);

        add(registerButton);
        add(updateButton);
        add(deleteButton);
        add(loginButton);

        registerButton.addActionListener(e -> {
            Farmer farmer = new Farmer(0, nameField.getText(), emailField.getText(), new String(passwordField.getPassword()), mobileField.getText(), addressField.getText());
            if (farmerDAO.addFarmer(farmer)) {
                JOptionPane.showMessageDialog(this, "Farmer registered successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed.");
            }
        });

        updateButton.addActionListener(e -> {
            int farmerId = Integer.parseInt(JOptionPane.showInputDialog("Enter Farmer ID:"));
            Farmer farmer = new Farmer(farmerId, nameField.getText(), emailField.getText(), new String(passwordField.getPassword()), mobileField.getText(), addressField.getText());
            if (farmerDAO.updateFarmer(farmer)) {
                JOptionPane.showMessageDialog(this, "Farmer updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }
        });

        deleteButton.addActionListener(e -> {
            int farmerId = Integer.parseInt(JOptionPane.showInputDialog("Enter Farmer ID to delete:"));
            if (farmerDAO.deleteFarmer(farmerId)) {
                JOptionPane.showMessageDialog(this, "Farmer deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Deletion failed.");
            }
        });

        loginButton.addActionListener(e -> {
            if (farmerDAO.validateFarmer(emailField.getText(), new String(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Navigate to the product registration panel for farmers
                mainFrame.setContentPane(new ProductRegistrationPanelForFarmers(mainFrame));
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login details. Please try again.");
            }
        });
    }
}

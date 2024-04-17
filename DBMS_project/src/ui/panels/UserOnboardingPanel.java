package ui.panels;

import javax.swing.*;
import model.User;
import dao.UserDAO;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class UserOnboardingPanel extends JPanel {
    private UserDAO userDAO = new UserDAO();
    private JTextField nameField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JTextField addressField = new JTextField(20);
    private JButton registerButton = new JButton("Register");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton loginButton = new JButton("Login");

    public UserOnboardingPanel(JFrame mainFrame) {
        setLayout(new GridLayout(0, 2));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Address:"));
        add(addressField);
        add(registerButton);
        add(updateButton);
        add(deleteButton);
        add(loginButton);

        registerButton.addActionListener(e -> {
            User user = new User(0, nameField.getText(), emailField.getText(), new String(passwordField.getPassword()), addressField.getText());
            if (userDAO.addUser(user)) {
                JOptionPane.showMessageDialog(this, "Registered successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed.");
            }
        });

        updateButton.addActionListener(e -> {
            User user = new User(Integer.parseInt(JOptionPane.showInputDialog("Enter User ID:")), nameField.getText(), emailField.getText(), new String(passwordField.getPassword()), addressField.getText());
            if (userDAO.updateUser(user)) {
                JOptionPane.showMessageDialog(this, "Updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }
        });

        deleteButton.addActionListener(e -> {
            int userId = Integer.parseInt(JOptionPane.showInputDialog("Enter User ID to delete:"));
            if (userDAO.deleteUser(userId)) {
                JOptionPane.showMessageDialog(this, "Deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Deletion failed.");
            }
        });

        loginButton.addActionListener(e -> {
            mainFrame.setContentPane(new LoginPanel(mainFrame));
            mainFrame.validate();
        });
    }
}

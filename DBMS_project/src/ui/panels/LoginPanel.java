package ui.panels;

import javax.swing.*;
import java.awt.*;
import dao.UserDAO;
import ui.panels.ProductPanelForBuyers;
import util.UserSession;

@SuppressWarnings("unused")
public class LoginPanel extends JPanel {
    private JTextField emailField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");

    public LoginPanel(JFrame mainFrame) {
        setLayout(new BorderLayout());
        JPanel loginForm = createLoginForm();
        add(loginForm, BorderLayout.CENTER);
        initLoginButton(mainFrame);
    }

    private JPanel createLoginForm() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        return panel;
    }

    private void initLoginButton(JFrame mainFrame) {
        loginButton.addActionListener(e -> handleLogin(mainFrame));
        add(loginButton, BorderLayout.SOUTH);
    }

    private void handleLogin(JFrame mainFrame) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.validateUser(email, password);
        if (userId > 0) {
            UserSession.login(userId);
            JOptionPane.showMessageDialog(this, "Login successful!");
            mainFrame.setContentPane(new ProductPanelForBuyers(mainFrame));
            mainFrame.validate();
            mainFrame.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed, please try again.");
        }
    }
}

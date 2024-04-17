package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WelcomePanel extends JPanel {
    public WelcomePanel(JFrame mainFrame) {
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the AgroMarket", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton buyerButton = new JButton("Buyer");
        JButton farmerButton = new JButton("Farmer");

        buyerButton.addActionListener((ActionEvent e) -> mainFrame.setContentPane(new UserOnboardingPanel(mainFrame)));
        farmerButton.addActionListener((ActionEvent e) -> mainFrame.setContentPane(new FarmerOnboardingPanel(mainFrame)));

        buttonPanel.add(buyerButton);
        buttonPanel.add(farmerButton);
        add(buttonPanel, BorderLayout.CENTER);
    }
}

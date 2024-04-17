package ui.main;

import javax.swing.*;

import ui.panels.WelcomePanel;

import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("FarmersMarket Application");
        setSize(800, 600); // Set the initial size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout()); // Use BorderLayout as the layout manager
        initUI(); // Initialize the user interface components
    }

    private void initUI() {
        // Set the initial panel to the WelcomePanel
        setContentPane(new WelcomePanel(this));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true); // Make the frame visible
        });
    }
}

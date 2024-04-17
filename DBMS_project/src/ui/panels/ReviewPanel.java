package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.ProductReviewDAO;
import model.ProductReview;
import util.UserSession;

import java.util.Date;

public class ReviewPanel extends JPanel {
    private ProductReviewDAO reviewDAO = new ProductReviewDAO();

    public ReviewPanel(JFrame mainFrame, int productId) {
        setLayout(new GridLayout(0, 2));

        JLabel ratingLabel = new JLabel("Rating (1-5):");
        JComboBox<Integer> ratingBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        JLabel reviewTextLabel = new JLabel("Review:");
        JTextArea reviewTextArea = new JTextArea(5, 20);
        JButton postReviewButton = new JButton("Post Review");

        postReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductReview review = new ProductReview(0, productId, UserSession.getUserId(), // Assuming user ID is 1 for example
                    reviewTextArea.getText(), new Date(), (Integer) ratingBox.getSelectedItem());
                if (reviewDAO.addProductReview(review)) {
                    JOptionPane.showMessageDialog(null, "Review posted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to post review.");
                }
            }
        });

        add(ratingLabel);
        add(ratingBox);
        add(reviewTextLabel);
        add(new JScrollPane(reviewTextArea));
        add(postReviewButton);
    }
}

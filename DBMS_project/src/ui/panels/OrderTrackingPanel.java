package ui.panels;

import javax.swing.*;
import java.awt.*;
import model.ProductTracking;
import dao.ProductTrackingDAO;

public class OrderTrackingPanel extends JPanel {
    private ProductTrackingDAO trackingDAO = new ProductTrackingDAO();

    // Constructor now accepts an orderId parameter
    public OrderTrackingPanel(JFrame mainFrame, int orderId) {
        setLayout(new GridLayout(0, 1));  // Use GridLayout for vertical arrangement

        // Fetch the latest tracking information using the provided orderId
        ProductTracking tracking = trackingDAO.getLatestTrackingInfo(orderId);
        if (tracking != null) {
            JLabel trackingIdLabel = new JLabel("Tracking ID: " + tracking.getTrackingId());
            JLabel statusLabel = new JLabel("Order Status: " + tracking.getTrackingStatus());
            JLabel estimatedLabel = new JLabel("Estimated Delivery: " + 
                (tracking.getEstimatedDeliveryDate() != null ? tracking.getEstimatedDeliveryDate().toString() : "To be updated soon"));
            JLabel actualLabel = new JLabel("Actual Delivery: " + 
                (tracking.getActualDeliveryDate() != null ? tracking.getActualDeliveryDate().toString() : "To be updated soon"));

            // Add tracking information to the panel
            add(trackingIdLabel);
            add(statusLabel);
            add(estimatedLabel);
            add(actualLabel);
        } else {
            add(new JLabel("No tracking information available."));
        }

        // Add a review button that redirects to the ReviewPanel
        JButton reviewButton = new JButton("Add a Review");
        reviewButton.addActionListener(e -> {
            mainFrame.setContentPane(new ReviewPanel(mainFrame, orderId));  // Assume ReviewPanel accepts JFrame and orderId
            mainFrame.validate();
        });
        add(reviewButton);
    }
}

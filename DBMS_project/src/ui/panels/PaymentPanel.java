package ui.panels;

import javax.swing.*;
import model.Product;
import model.ProductTracking;
import model.Payment;
import dao.PaymentDAO;
import dao.ProductTrackingDAO;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Date;

public class PaymentPanel extends JPanel {
    private PaymentDAO paymentDAO = new PaymentDAO();
    private ProductTrackingDAO trackingDAO = new ProductTrackingDAO();
    // Updated constructor to accept orderId
    public PaymentPanel(JFrame mainFrame, Product product, int quantity, int orderId) {
        setLayout(new GridLayout(0, 2));

        BigDecimal amount = product.getPPrice().multiply(new BigDecimal(quantity));
        JLabel amountLabel = new JLabel("Amount Due: $" + amount);
        JButton payButton = new JButton("Pay Now");

        payButton.addActionListener(e -> {
            // Updated to use the passed orderId
            Payment payment = new Payment(0, orderId, new Date(), amount, "Success", "Online");
            ProductTracking tracking = new ProductTracking(0, orderId, "To be shipped", new Date(), null);
            trackingDAO.addProductTracking(tracking);
            if (paymentDAO.createPayment(payment)) {
                JOptionPane.showMessageDialog(this, "Payment successful!");
                // Pass orderId to the OrderTrackingPanel
                mainFrame.setContentPane(new OrderTrackingPanel(mainFrame, orderId));
            } else {
                JOptionPane.showMessageDialog(this, "Payment failed.");
            }
        });

        add(amountLabel);
        add(payButton);
    }
}

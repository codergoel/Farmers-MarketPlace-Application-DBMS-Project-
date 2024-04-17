package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import dao.ProductDAO;
import dao.CustomerOrderDAO;
import model.Product;
import util.UserSession;
import model.CustomerOrder;
import java.util.Date;

public class ProductPanelForBuyers extends JPanel {
    private ProductDAO productDAO = new ProductDAO();
    private CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();

    public ProductPanelForBuyers(JFrame mainFrame) {
        setLayout(new GridLayout(0, 2));  // Adjust grid layout according to the number of products

        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            JPanel card = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel(product.getName());
            JLabel priceLabel = new JLabel("$" + product.getPPrice().toString());
            JTextField quantityField = new JTextField("1", 10);
            JButton buyButton = new JButton("Buy");

            buyButton.addActionListener(e -> {
                int quantity = Integer.parseInt(quantityField.getText());
                if (productDAO.purchaseProduct(product.getPId(), quantity)) {
                    // Assuming userId is known, replace '1' with actual userId
                    int userId = UserSession.getUserId(); // This should be dynamically determined based on the logged-in user
                    CustomerOrder newOrder = new CustomerOrder(0, userId, new Date(), "In process");
                    int orderId = customerOrderDAO.createCustomerOrder(newOrder);
                    if (orderId != -1) {
                        JOptionPane.showMessageDialog(this, "Purchase successful!");
                        mainFrame.setContentPane(new PaymentPanel(mainFrame, product, quantity, orderId));
                        mainFrame.validate();
                    } else {
                        JOptionPane.showMessageDialog(this, "Order creation failed. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Purchase failed. Check availability.");
                }
            });

            card.add(nameLabel, BorderLayout.NORTH);
            card.add(priceLabel, BorderLayout.CENTER);
            JPanel bottomPanel = new JPanel(new FlowLayout());
            bottomPanel.add(quantityField);
            bottomPanel.add(buyButton);
            card.add(bottomPanel, BorderLayout.SOUTH);
            add(card);
        }
    }
}

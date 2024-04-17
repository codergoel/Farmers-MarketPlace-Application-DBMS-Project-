package ui.panels;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import dao.ProductDAO;
import model.Product;
import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ProductRegistrationPanelForFarmers extends JPanel {
    private ProductDAO productDAO = new ProductDAO();

    public ProductRegistrationPanelForFarmers(JFrame mainFrame) {
        setLayout(new GridLayout(0, 2));

        JTextField nameField = new JTextField(20);
        JTextArea descArea = new JTextArea(5, 20);
        JTextField priceField = new JTextField(20);
        JTextField quantityField = new JTextField(20);
        JTextField categoryIdField = new JTextField(20);
        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");

        addButton.addActionListener(e -> {
            Product product = new Product(0, 1, // Assuming farmer ID is 1
                nameField.getText(), Integer.parseInt(quantityField.getText()), new BigDecimal(priceField.getText()), Integer.parseInt(categoryIdField.getText()),  descArea.getText());
            if (productDAO.addProduct(product)) {
                JOptionPane.showMessageDialog(null, "Product added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add product.");
            }
        });

        updateButton.addActionListener(e -> {
            // Assume product ID is known or fetched separately
            int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter Product ID:"));
            Product product = new Product(productId, 1, // Assuming farmer ID is 1
                nameField.getText(),Integer.parseInt(quantityField.getText()), new BigDecimal(priceField.getText()), Integer.parseInt(categoryIdField.getText()), descArea.getText());
            if (productDAO.updateProduct(product)) {
                JOptionPane.showMessageDialog(null, "Product updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update product.");
            }
        });

        deleteButton.addActionListener(e -> {
            int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter Product ID to delete:"));
            if (productDAO.deleteProduct(productId)) {
                JOptionPane.showMessageDialog(null, "Product deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete product.");
            }
        });

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Description:"));
        add(new JScrollPane(descArea));
        add(new JLabel("Price:"));
        add(priceField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Category ID:"));
        add(categoryIdField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
    }
}


package dao;

import model.CustomerOrder;
import util.DatabaseConnection;

import java.sql.*;
import java.util.Random;

public class CustomerOrderDAO {

    public int createCustomerOrder(CustomerOrder order) {
        // Using Random to generate a random order_id
        Random random = new Random();
        int orderId = random.nextInt(Integer.MAX_VALUE);  // Ensure it's a positive integer

        String sql = "INSERT INTO CustomerOrder (order_id, user_id, order_date, order_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, order.getUserId());
            pstmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setString(4, order.getOrderStatus());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return orderId;  // Return the successfully generated and used order_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
            orderId = -1; // Set orderId to -1 to indicate failure
        }
        return orderId;
    }
    
}

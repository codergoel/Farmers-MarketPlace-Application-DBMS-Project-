package dao;

import model.OrderDetail;
import util.DatabaseConnection;

import java.sql.*;
import java.util.Random;

public class OrderDetailDAO {

    public boolean createOrderDetail(OrderDetail detail) {
        // Generating a random unique ID for order_detail_id
        Random random = new Random();
        int orderDetailId = random.nextInt(Integer.MAX_VALUE);  // Ensure the ID is positive and somewhat unique

        String sql = "INSERT INTO OrderDetail (order_detail_id, order_id, p_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderDetailId);
            pstmt.setInt(2, detail.getOrderId());
            pstmt.setInt(3, detail.getProductId());
            pstmt.setInt(4, detail.getQuantity());
            pstmt.setBigDecimal(5, detail.getPrice());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

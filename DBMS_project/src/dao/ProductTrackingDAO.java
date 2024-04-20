package dao;

import model.ProductTracking;
import util.DatabaseConnection;
import java.sql.*;
import java.util.Random;

public class ProductTrackingDAO {
    public boolean addProductTracking(ProductTracking tracking) {
        String sql = "INSERT INTO producttracking (tracking_id, order_id, tracking_status, estimated_delivery_date, actual_delivery_date) VALUES (?, ?, ?, ?, ?)";
        Random random = new Random();
        int randomTrackingId = random.nextInt(Integer.MAX_VALUE);  // Generate a random tracking_id

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, randomTrackingId);
            pstmt.setInt(2, tracking.getOrderId());
            pstmt.setString(3, tracking.getTrackingStatus() != null ? tracking.getTrackingStatus() : "To be shipped");
            pstmt.setString(4, tracking.getEstimatedDeliveryDate() != null ? tracking.getEstimatedDeliveryDate().toString() : "To be updated soon");
            pstmt.setString(5, tracking.getActualDeliveryDate() != null ? tracking.getActualDeliveryDate().toString() : "To be updated soon");

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ProductTracking getLatestTrackingInfo(int orderId) {
        String sql = "SELECT * FROM ProductTracking WHERE order_id = ? ORDER BY tracking_id DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ProductTracking(
                    rs.getInt("tracking_id"),
                    rs.getInt("order_id"),
                    rs.getString("tracking_status"),
                    rs.getString("estimated_delivery_date"),
                    rs.getString("actual_delivery_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

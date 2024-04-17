package dao;

import model.Payment;
import util.DatabaseConnection;

import java.sql.*;
import java.util.Random;

public class PaymentDAO {

    public boolean createPayment(Payment payment) {
        // Generate a random unique payment_id
        int paymentId = generateUniqueId();
        String sql = "INSERT INTO Payment (payment_id, order_id, payment_date, amount, payment_status, payment_method) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, paymentId);
            pstmt.setInt(2, payment.getOrderId());
            pstmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            pstmt.setBigDecimal(4, payment.getAmount());
            pstmt.setString(5, payment.getPaymentStatus());
            pstmt.setString(6, payment.getPaymentMethod());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                payment.setPaymentId(paymentId);  // Set the generated id back to the payment object
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to generate a unique integer ID
    private int generateUniqueId() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);  // Generates a random integer within the positive range of int
    }
}

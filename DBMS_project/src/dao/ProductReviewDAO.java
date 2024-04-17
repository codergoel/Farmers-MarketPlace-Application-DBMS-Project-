package dao;

import model.ProductReview;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductReviewDAO {
    public boolean addProductReview(ProductReview review) {
        // Generate a random unique review ID
        Random random = new Random();
        int reviewId = random.nextInt(Integer.MAX_VALUE);

        String sql = "INSERT INTO ProductReview (review_id, p_id, user_id, rating, review_text, review_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reviewId);
            pstmt.setInt(2, review.getProductId());
            pstmt.setInt(3, review.getUserId());
            pstmt.setInt(4, review.getRating());
            pstmt.setString(5, review.getReviewText());
            pstmt.setDate(6, new java.sql.Date(review.getReviewDate().getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductReview> getReviewsByProductId(int productId) {
        List<ProductReview> reviews = new ArrayList<>();
        String sql = "SELECT * FROM ProductReview WHERE p_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reviews.add(new ProductReview(
                    rs.getInt("review_id"),
                    rs.getInt("p_id"),
                    rs.getInt("user_id"),
                    rs.getString("review_text"),
                    rs.getDate("review_date"),
                    rs.getInt("rating")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}

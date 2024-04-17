package dao;

import model.Category;
import util.DatabaseConnection;

import java.sql.*;

public class CategoryDAO {
    public Category getCategory(int catId) {
        String sql = "SELECT * FROM Category WHERE cat_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, catId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Category(rs.getInt("cat_id"), rs.getString("cat_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

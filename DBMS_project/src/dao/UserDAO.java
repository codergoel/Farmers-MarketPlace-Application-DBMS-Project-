package dao;

import model.User;
import util.DatabaseConnection;
import java.sql.*;
import java.util.Random;

public class UserDAO {
    public boolean addUser(User user) {
        String sql = "INSERT INTO User (user_id, user_name, user_email, user_password, user_address) VALUES (?, ?, ?, ?, ?)";
        Random rand = new Random();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            int uniqueId = rand.nextInt(1000000); // Generate a random number up to 1,000,000
            pstmt.setInt(1, uniqueId);
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getAddress());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                user.setUserId(uniqueId); // Set the generated ID back to the user object
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int validateUser(String email, String password) {
        String sql = "SELECT user_id FROM User WHERE user_email = ? AND user_password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");  // Return the user_id if credentials are valid
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 if credentials are invalid or an error occurs
    }
    

    public boolean updateUser(User user) {
        String sql = "UPDATE User SET user_name = ?, user_email = ?, user_password = ?, user_address = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getAddress());
            pstmt.setInt(5, user.getUserId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM User WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

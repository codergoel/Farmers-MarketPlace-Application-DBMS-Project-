package dao;

import model.Farmer;
import util.DatabaseConnection;
import java.sql.*;

public class FarmerDAO {
    public boolean addFarmer(Farmer farmer) {
        String sql = "INSERT INTO farmer (f_name, f_email, f_password, f_mobile, f_address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, farmer.getName());
            pstmt.setString(2, farmer.getEmail());
            pstmt.setString(3, farmer.getPassword());
            pstmt.setString(4, farmer.getMobile());
            pstmt.setString(5, farmer.getAddress());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    farmer.setId(rs.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateFarmer(String email, String password) {
        String sql = "SELECT * FROM farmer WHERE f_email = ? AND f_password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if there is at least one result, meaning the credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if any errors occur or if the credentials are not valid
    }

    public boolean updateFarmer(Farmer farmer) {
        String sql = "UPDATE farmer SET f_name = ?, f_email = ?, f_password = ?, f_mobile = ?, f_address = ? WHERE f_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, farmer.getName());
            pstmt.setString(2, farmer.getEmail());
            pstmt.setString(3, farmer.getPassword());
            pstmt.setString(4, farmer.getMobile());
            pstmt.setString(5, farmer.getAddress());
            pstmt.setInt(6, farmer.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFarmer(int farmerId) {
        String sql = "DELETE FROM farmer WHERE f_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, farmerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Farmer getFarmerById(int farmerId) {
        String sql = "SELECT * FROM farmer WHERE f_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, farmerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Farmer(
                    rs.getInt("f_id"),
                    rs.getString("f_name"),
                    rs.getString("f_email"),
                    rs.getString("f_password"),
                    rs.getString("f_mobile"),
                    rs.getString("f_address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

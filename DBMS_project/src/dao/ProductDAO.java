package dao;

import model.Product;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ProductDAO {
    
    public boolean addProduct(Product product) {
        // Generate a unique p_id
        int productId = generateUniqueId();
        
        String sql = "INSERT INTO product (p_id, f_id, name, p_quantity, p_price, p_cat_id, p_desc) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.setInt(2, product.getFId());
            pstmt.setString(3, product.getName());
            pstmt.setInt(4, product.getPQuantity());
            pstmt.setBigDecimal(5, product.getPPrice());
            pstmt.setInt(6, product.getPCatId());
            pstmt.setString(7, product.getPDesc());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private int generateUniqueId() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);  // Generates a random integer within the range of int
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET f_id = ?, name = ?, p_quantity = ?, p_price = ?, p_cat_id = ?, p_desc = ? WHERE p_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getFId());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getPQuantity());
            pstmt.setBigDecimal(4, product.getPPrice());
            pstmt.setInt(5, product.getPCatId());
            pstmt.setString(6, product.getPDesc());
            pstmt.setInt(7, product.getPId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("p_id"),
                    rs.getInt("f_id"),
                    rs.getString("name"),
                    rs.getInt("p_quantity"),
                    rs.getBigDecimal("p_price"),
                    rs.getInt("p_cat_id"),
                    rs.getString("p_desc")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean purchaseProduct(int productId, int quantity) {
        Connection conn = null;
        PreparedStatement checkStockStmt = null;
        PreparedStatement updateStockStmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);  // Start transaction

            // Check current stock
            String checkStockSql = "SELECT p_quantity FROM Product WHERE p_id = ?";
            checkStockStmt = conn.prepareStatement(checkStockSql);
            checkStockStmt.setInt(1, productId);
            rs = checkStockStmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("p_quantity");
                if (stock >= quantity) {
                    // Update stock
                    String updateStockSql = "UPDATE Product SET p_quantity = p_quantity - ? WHERE p_id = ?";
                    updateStockStmt = conn.prepareStatement(updateStockSql);
                    updateStockStmt.setInt(1, quantity);
                    updateStockStmt.setInt(2, productId);

                    updateStockStmt.executeUpdate();
                    conn.commit();  // Commit transaction
                    return true;
                }
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) conn.rollback();  // Rollback transaction on error
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkStockStmt != null) checkStockStmt.close();
                if (updateStockStmt != null) updateStockStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE p_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Product getProduct(int productId) {
        String sql = "SELECT * FROM product WHERE p_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("p_id"),
                    rs.getInt("f_id"),
                    rs.getString("name"),
                    rs.getInt("p_quantity"),
                    rs.getBigDecimal("p_price"),
                    rs.getInt("p_cat_id"),
                    rs.getString("p_desc")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

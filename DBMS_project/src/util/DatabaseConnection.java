package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for handling database connections.
 */
public class DatabaseConnection {
    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/farmers_market?useSSL=false&allowPublicKeyRetrieval=true"; // Adjust the database name as necessary
    // Database credentials
    private static final String USER = "root"; // Use your database username
    private static final String PASSWORD = "TanmayGOEL@1306"; // Use your database password

    // Load the JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection to the database.
     * @return a Connection object
     * @throws SQLException if a database access error occurs or the url is null
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successfully connected to the MySQL database.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Failed to establish a connection to the MySQL database.");
            throw e;  // Rethrow exception to ensure it's handled further up the call stack
        }
    }

    /**
     * Close the given connection.
     * @param conn the connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to close connection.");
                e.printStackTrace();
            }
        }
    }
}

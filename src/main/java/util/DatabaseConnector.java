package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
   
    private static final String URL = "jdbc:mysql://localhost:3306/airlines"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Abraham@09!"; 

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

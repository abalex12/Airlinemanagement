package dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.*;

public class UserDaoAuthentication {
    private static final String INSERT_USER_SQL = "INSERT INTO users (username, email,phone, password_hash) VALUES (?, ?,?, ?)";
    private static final String SELECT_PASSWORD_HASH_SQL = "SELECT password_hash FROM users WHERE username = ?";

    // Method to hash the password
    public String hashPassword(String password) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace(); 
    }
    return null;
}


    // Method to insert a new user into the database
    public boolean createUser(String username, String email, String phone, String password) {
        String hashedPassword = hashPassword(password);
        if (hashedPassword != null) {
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement statement = conn.prepareStatement(INSERT_USER_SQL)) {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, phone);
                statement.setString(4, hashedPassword);
                int rowCount = statement.executeUpdate();
    
                if (rowCount > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); 
            }
        } else {
            System.out.println("Hashing failed");
        }
        return false;
    }
    

    // Method to retrieve the hashed password from the database by username
    public String getPasswordHashByUsername(String username) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_PASSWORD_HASH_SQL)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password_hash");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // For demonstration, print the stack trace
        }
        return null; // Username not found or SQL exception occurred
    }
    
}

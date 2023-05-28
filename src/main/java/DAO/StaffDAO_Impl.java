package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DatabaseConnection.DatabaseConnection;
import Entity.AdminUser;

public class StaffDAO_Impl {
	private Connection connection = DatabaseConnection.getConnection();
	
	public AdminUser getAdminUser(String username, String password) {
        String query = "SELECT * FROM AdminUser WHERE adminUser_username = ? AND adminUser_password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userId = resultSet.getString("adminUser_userId");
                    String userRole = resultSet.getString("adminUser_userRole");

                    // Create and return an AdminUser object
                    return new AdminUser(userId, username, password, userRole);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // If no user is found or an exception occurs
    }
}

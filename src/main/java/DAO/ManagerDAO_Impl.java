package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import DatabaseConnection.DatabaseConnection;
import Entity.AdminUser;
import Entity.Customer;

public class ManagerDAO_Impl {
	private Connection connection = DatabaseConnection.getConnection();

	public static String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			sb.append(characters.charAt(randomIndex));
		}
		return sb.toString();
	}

	public void insertStaffAccount(String username, String password) {
		String sql = "Insert into AdminUser(adminUser_userId, adminUser_username, adminUser_password, adminUser_userRole) values (?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, generateRandomString(5));
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, "Staff");
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean doesUsernameExist(String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM AdminUser WHERE adminUser_username = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count > 0;
    }
	
	public ArrayList<AdminUser> getAllAdminUsers() throws SQLException {
        ArrayList<AdminUser> adminUsers = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM AdminUser where adminUser_userRole = 'Staff'");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            AdminUser adminUser = new AdminUser();
            adminUser.setAdminUser_userId(resultSet.getString("adminUser_userId"));
            adminUser.setAdminUser_username(resultSet.getString("adminUser_username"));
            adminUser.setAdminUser_password(resultSet.getString("adminUser_password"));
            adminUser.setAdminUser_userRole(resultSet.getString("adminUser_userRole"));
            adminUsers.add(adminUser);
        }
        return adminUsers;
    }
	
	public void removeAdminUserByUsername(String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM AdminUser WHERE adminUser_username = ?");
        statement.setString(1, username);
        statement.executeUpdate();
    }
	
	public ArrayList<Customer> getVipCustomerList(){
		ArrayList<Customer> listCustomer = new ArrayList<Customer>();
		ResultSet rs = null;
		String sql = "select * from Customer inner join Account on Customer.customer_bankAccount = Account.account_number where account_vipStatus = 'true'";
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (rs.next()) {
				Customer cus = new Customer();
				cus.setCustomerId(rs.getString("customer_id"));
				cus.setCustomerName(rs.getString("customer_name"));
				cus.setCustomerDob(rs.getDate("customer_dob"));
				cus.setCustomerAddress(rs.getString("customer_address"));
				cus.setCustomerPhoneNumber(rs.getString("customer_phoneNumber"));
				cus.setCustomerEmail(rs.getString("customer_email"));
				cus.setCustomerUsername(rs.getString("customer_username"));
				cus.setCustomerPassword(rs.getString("customer_password"));
				cus.setCustomerBankAccount(rs.getString("customer_bankAccount"));
				cus.setLoginAttempts(rs.getInt("customer_loginAttempts"));
				listCustomer.add(cus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCustomer;
	}
	
	public void removeVipStatus(String id) {
		String sql = "Update Account\r\n"
				+ "SET Account.account_vipStatus = 'false'\r\n"
				+ "from Account inner join Customer on Customer.customer_bankAccount = account_number\r\n"
				+ "where customer_id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;

import DatabaseConnection.DatabaseConnection;
import Entity.AdminUser;
import Entity.Customer;
import Entity.Request;

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

	public ArrayList<Customer> getCustomerList() {
		ArrayList<Customer> listCustomer = new ArrayList<Customer>();
		String sql = "SELECT *from Customer inner join Account on Customer.customer_bankAccount = Account.account_number";
		ResultSet result = null;

		try {
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (result.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(result.getString("customer_id"));
				customer.setCustomerName(result.getString("customer_name"));
				customer.setCustomerDob(result.getDate("customer_dob"));
				customer.setCustomerAddress(result.getString("customer_address"));
				customer.setCustomerPhoneNumber(result.getString("customer_phoneNumber"));
				customer.setCustomerEmail(result.getString("customer_email"));
				customer.setCustomerUsername(result.getString("customer_username"));
				customer.setCustomerPassword(result.getString("customer_password"));
				customer.setCustomerBankAccount(result.getString("customer_bankAccount"));
				customer.setLoginAttempts(result.getInt("customer_loginAttempts"));
				customer.setVipStatus(result.getBoolean("account_vipStatus"));
				listCustomer.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listCustomer;
	}

	public ArrayList<Request> getListRequest() {
		ArrayList<Request> listRequest = new ArrayList<Request>();
		String sql = "select * from Request inner join Customer on Request.request_by = Customer.customer_id";
		ResultSet result = null;
		Gson gson = new Gson();
		try {
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (result.next()) {
				Request req = new Request();
				req.setRequestId(result.getString("request_id"));
				req.setRequestType(result.getString("request_type"));
				req.setRequestBy(result.getString("request_by"));
				req.setRequestDate(result.getTimestamp("request_date"));
				if (req.getRequestType().equals("Unlock Acc.") || req.getRequestType().equals("Lock Acc.")) {
					req.setRequestContent(result.getString("request_content"));					
				} else if (req.getRequestType().equals("Savings")) {
					req.setRequestSavings(gson.fromJson(result.getString("request_content"), HashMap.class));					
				} else {
					req.setRequestInformationChanging(gson.fromJson(result.getString("request_content"), HashMap.class));
				}

				req.setRequestStatus(result.getBoolean("request_status"));
				req.setRequestResolvedBy(result.getString("request_resolvedBy"));
				req.setRequestName(result.getString("customer_name"));
				req.setReqOwnerId(result.getString("customer_id"));

				req.setRejected(result.getBoolean("request_isRejected"));
				listRequest.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listRequest;
	}
	
	public Request getRequestFromId(String id) {
		Request request = new Request();
		
		
		return request;
	}
}

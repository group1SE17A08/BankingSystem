package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import DatabaseConnection.DatabaseConnection;
import Entity.AdminUser;
import Entity.Customer;
import Entity.Request;
import Entity.Savings;

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
					req.setRequestSavings(
							gson.fromJson(result.getString("request_content"), new HashMap<Double, Date>().getClass()));
				} else {
					req.setRequestInformationChanging(gson.fromJson(result.getString("request_content"),
							new HashMap<String, String>().getClass()));
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
		Gson gson = new Gson();
		String sql = "select * from Request where Request.request_id = ?";
		ResultSet rs = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				request.setRequestId(rs.getString("request_id"));
				request.setRequestType(rs.getString("request_type"));
				request.setRequestBy(rs.getString("request_by"));
				request.setRequestDate(rs.getTimestamp("request_date"));
				if (request.getRequestType().equals("Unlock Acc.") || request.getRequestType().equals("Lock Acc.")) {
					request.setRequestContent(rs.getString("request_content"));
				} else if (request.getRequestType().equals("Savings")) {
					request.setRequestSavings(gson.fromJson(rs.getString("request_content"), HashMap.class));
				} else {
					request.setRequestInformationChanging(
							gson.fromJson(rs.getString("request_content"), HashMap.class));
				}

				request.setRequestStatus(rs.getBoolean("request_status"));
				request.setRequestResolvedBy(rs.getString("request_resolvedBy"));
				request.setRejected(rs.getBoolean("request_isRejected"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	public void updateRequest(Request request) throws SQLException {
		String updateQuery = "UPDATE Request SET request_status = ?, request_resolvedBy = ?, request_isRejected = ? WHERE request_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
			statement.setBoolean(1, request.getRequestStatus());
			statement.setString(2, request.getRequestResolvedBy());
			statement.setBoolean(3, request.getRejected());
			statement.setString(4, request.getRequestId());

			statement.executeUpdate();
		}
	}

	public String getEmailByCustomerId(String id) {
		String email = null;
		String sql = "Select customer_email from Customer where customer_id = ?";
		ResultSet rs = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				email = rs.getString("customer_email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
	}

	public void updateLockStatus(String id, boolean status) throws SQLException {
		String updateQuery = "update Account set account_isLocked = ?\r\n"
				+ "from Account inner join Customer on Account.account_number = Customer.customer_bankAccount\r\n"
				+ "where customer_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
			statement.setBoolean(1, status);
			statement.setString(2, id);
			statement.executeUpdate();
		}
	}

	public boolean getVipStatus(String id) {
		boolean vipStatus = false;
		String sql = "select Account.account_vipStatus from Account inner join Customer on Customer.customer_bankAccount = Account.account_number where customer.customer_id = ?";
		ResultSet rs = null;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				vipStatus = rs.getBoolean("account_vipStatus");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vipStatus;
	}

	public void insertSavings(Savings s) throws SQLException {
		String query = "INSERT INTO Saving (savings_id, savings_interest, savings_maturityDay, savings_owner, savings_amount) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, s.getSavingsId());
			statement.setDouble(2, s.getSavingsInterest());
			statement.setDate(3, s.getSavingsMaturityDay());
			statement.setString(4, s.getSavingsOwner());
			statement.setDouble(5, s.getSavingsAmount());

			statement.executeUpdate();
		}
	}

	public void updateInformation(Customer c) throws SQLException {
		String sql = "update Customer set customer_name = ?, customer_email = ?, customer_phoneNumber = ?, customer_dob = ?, customer_address = ? where customer_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, c.getCustomerName());
			statement.setString(2, c.getCustomerEmail());
			statement.setString(3, c.getCustomerPhoneNumber());
			statement.setDate(4, c.getCustomerDob());
			statement.setString(5, c.getCustomerAddress());
			statement.setString(6, c.getCustomerId());
			statement.executeUpdate();
		}
	}
	
	public void updateTotalSavings(String customerId, double amount) {
		String sql = "update Account set account_totalSavings = ? ";
	}
}

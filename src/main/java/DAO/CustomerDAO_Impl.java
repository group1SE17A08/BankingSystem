package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.google.gson.Gson;

import DatabaseConnection.DatabaseConnection;
import Entity.Account;
import Entity.Bill;
import Entity.Customer;
import Entity.Request;
import Entity.Transaction;

public class CustomerDAO_Impl {
	private Connection connection = DatabaseConnection.getConnection();
	
	public Customer verifyLogin(String username, String password) {
		String query = "SELECT * FROM Customer WHERE customer_username = ? AND customer_password = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String customerId = resultSet.getString("customer_id");
				String customerName = resultSet.getString("customer_name");
				LocalDate customerDob = resultSet.getDate("customer_dob").toLocalDate();
				String customerAddress = resultSet.getString("customer_address");
				String customerPhoneNumber = resultSet.getString("customer_phoneNumber");
				String customerEmail = resultSet.getString("customer_email");
				String customerBankAccount = resultSet.getString("customer_bankAccount");
				String customerUsername = resultSet.getString("customer_username");
				String customerPassword = resultSet.getString("customer_password");
				int customerLoginAttempts = resultSet.getInt("customer_loginAttempts");

				Customer customer = new Customer(customerId, customerName, customerDob, customerAddress,
						customerPhoneNumber, customerEmail, customerUsername, customerPassword, customerBankAccount,
						customerLoginAttempts);

				return customer;
			}
		} catch (SQLException e) {
			System.out.println("Error occurred while verifying customer login.");
			e.printStackTrace();
		}

		return null;
	}

	public void changePassword(Customer customer, String newPassword) {
		String query = "UPDATE Customer SET customer_password = ? WHERE customer_id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newPassword);
			statement.setString(2, customer.getCustomerId());

			statement.executeUpdate();

			System.out.println("Password changed successfully.");
		} catch (SQLException e) {
			System.out.println("Failed to change password: " + e.getMessage());
		}
	}

	public String idGenerator() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		// Always start with a capitalized 'C'
		sb.append("C");

		// Generate 4 random letters
		for (int i = 0; i < 4; i++) {
			char randomLetter = letters.charAt(random.nextInt(letters.length()));
			sb.append(randomLetter);
		}

		return sb.toString();
	}

	public String generateUniqueId() {
		String generatedId = null;

		do {
			// Generate a random ID
			generatedId = idGenerator();

			// Check if the ID exists in the database
			if (isIdExists(generatedId)) {
				generatedId = null; // Reset the generated ID if it exists
			}
		} while (generatedId == null);

		return generatedId;
	}

	public boolean isIdExists(String id) {
		boolean exists = false;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT COUNT(*) FROM Customer WHERE customer_id = ?")) {

			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				if (count > 0) {
					exists = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	public void signUp(Customer c) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Customer (customer_id, customer_name, customer_dob, customer_address, customer_phoneNumber, customer_email, customer_username, customer_password, customer_bankAccount) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setString(1, idGenerator());
			statement.setString(2, c.getCustomerName());
			statement.setDate(3, Date.valueOf(c.getCustomerDob()));
			statement.setString(4, c.getCustomerAddress());
			statement.setString(5, c.getCustomerPhoneNumber());
			statement.setString(6, c.getCustomerEmail());
			statement.setString(7, c.getCustomerUsername());
			statement.setString(8, c.getCustomerPassword());
			statement.setString(9, c.getCustomerBankAccount());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean accNumValidation(String accountNumber) {
		boolean isDuplicated = false;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT COUNT(*) FROM Account WHERE account_number = ?")) {

			statement.setString(1, accountNumber);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					if (count > 0) {
						isDuplicated = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isDuplicated;
	}

	public boolean addAccount(Account account) {
		boolean success = false;

		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO Account (account_number, account_otp) VALUES (?, ?)")) {

			statement.setString(1, account.getAccountNumber());
			statement.setString(2, account.getAccountOTP());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean isUsernameDuplicated(String username) {
		boolean isDuplicated = false;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT COUNT(*) FROM Customer WHERE customer_username = ?")) {

			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					if (count > 0) {
						isDuplicated = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isDuplicated;
	}

	public boolean isEmailDuplicated(String email) {
		boolean isDuplicated = false;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT COUNT(*) FROM Customer WHERE customer_email = ?")) {

			statement.setString(1, email);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					if (count > 0) {
						isDuplicated = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isDuplicated;
	}

	public boolean isPhoneNumberDuplicated(String phoneNumber) {
		boolean isDuplicated = false;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT COUNT(*) FROM Customer WHERE customer_phoneNumber = ?")) {

			statement.setString(1, phoneNumber);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					if (count > 0) {
						isDuplicated = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isDuplicated;
	}

	public void lockAccount(String username) {
		try (PreparedStatement statement = connection.prepareStatement("UPDATE Account SET account_isLocked = ? "
				+ "FROM Account INNER JOIN Customer ON Customer.customer_bankAccount = Account.account_number "
				+ "WHERE Customer.customer_username = ?")) {

			statement.setBoolean(1, true);
			statement.setString(2, username);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateLoginAttempts(String username, int loginAttempts) {
		try (PreparedStatement statement = connection
				.prepareStatement("UPDATE Customer SET customer_loginAttempts = ? WHERE customer_username = ?")) {

			statement.setInt(1, loginAttempts);
			statement.setString(2, username);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verifyCustomerLogin(String username, String password) {
		boolean isLoginSuccessful = false;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM Customer WHERE customer_username = ?")) {

			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String storedPassword = resultSet.getString("customer_password");
					int loginAttempts = resultSet.getInt("customer_loginAttempts");

					if (storedPassword.equals(password)) {
						isLoginSuccessful = true;
					} else {
						// Increment the login attempts counter
						loginAttempts++;
						updateLoginAttempts(username, loginAttempts);

						// Check if the maximum login attempts limit is reached
						if (loginAttempts >= 4) {
							lockAccount(username);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isLoginSuccessful;
	}

	public int getLoginAttempts(String username) {
		int loginAttempts = 0;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT customer_loginAttempts FROM Customer WHERE customer_username = ?")) {

			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					loginAttempts = resultSet.getInt("customer_loginAttempts");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loginAttempts;
	}

	public boolean getAccountLockStatus(String username) {
		boolean isLocked = false;

		try (PreparedStatement statement = connection.prepareStatement("SELECT account_isLocked "
				+ "FROM Account INNER JOIN Customer ON Account.account_number = Customer.customer_bankAccount "
				+ "WHERE Customer.customer_username = ?")) {

			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				isLocked = resultSet.getBoolean("account_isLocked");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isLocked;
	}

	public boolean isUsernameExists(String username) {
		boolean exists = false;

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT customer_username FROM Customer WHERE customer_username = ?")) {

			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				exists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	public Customer getCustomerByEmail(String email) {
		String query = "SELECT * FROM Customer WHERE customer_email = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String customerId = resultSet.getString("customer_id");
					String customerName = resultSet.getString("customer_name");
					LocalDate customerDob = resultSet.getDate("customer_dob").toLocalDate();
					String customerAddress = resultSet.getString("customer_address");
					String customerPhoneNumber = resultSet.getString("customer_phoneNumber");
					String customerEmail = resultSet.getString("customer_email");
					String customerBankAccount = resultSet.getString("customer_bankAccount");
					String customerUsername = resultSet.getString("customer_username");
					String customerPassword = resultSet.getString("customer_password");
					int customerLoginAttempts = resultSet.getInt("customer_loginAttempts");

					Customer customer = new Customer(customerId, customerName, customerDob, customerAddress,
							customerPhoneNumber, customerEmail, customerBankAccount, customerUsername, customerPassword,
							customerLoginAttempts);

					return customer;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateCustomerPassword(Customer customer) {
		try {
			String sql = "UPDATE Customer SET customer_password = ? WHERE customer_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Set the password value for the placeholder in the SQL statement
			statement.setString(1, customer.getCustomerPassword());
			statement.setString(2, customer.getCustomerId());

			// Execute the update statement
			int rowsAffected = statement.executeUpdate();

			// Return true if at least one row was affected, indicating a successful update
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getCustomerPassword(Customer customer) {
		try {
			String sql = "SELECT customer_password FROM Customer WHERE customer_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Set the customer ID value for the placeholder in the SQL statement
			statement.setString(1, customer.getCustomerId());

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Check if the query returned a result
			if (resultSet.next()) {
				// Retrieve the password from the result set
				return resultSet.getString("customer_password");
			} else {
				// If no result was found, return null or an appropriate default value
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isEmailExist(String email) {
		try {
			String sql = "SELECT COUNT(*) AS count FROM Customer WHERE customer_email = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Set the email value for the placeholder in the SQL statement
			statement.setString(1, email);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Retrieve the count value from the result set
			if (resultSet.next()) {
				int count = resultSet.getInt("count");
				return count > 0;
			} else {
				// If no result was found, return false
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void performTransaction(Transaction transaction) throws SQLException {
		String fromAccount = transaction.getFromAccount();
		String toAccount = transaction.getToAccount();
		double amount = transaction.getAmount();

		String subtractSql = "UPDATE Account SET account_balance = account_balance - ? WHERE account_number = ?";
		String addSql = "UPDATE Account SET account_balance = account_balance + ? WHERE account_number = ?";

		connection.setAutoCommit(false);

		try {
			PreparedStatement subtractStmt = connection.prepareStatement(subtractSql);
			subtractStmt.setDouble(1, amount);
			subtractStmt.setString(2, fromAccount);
			subtractStmt.executeUpdate();

			PreparedStatement addStmt = connection.prepareStatement(addSql);
			addStmt.setDouble(1, amount);
			addStmt.setString(2, toAccount);
			addStmt.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException autoCommitException) {
				autoCommitException.printStackTrace();
			}
		}
	}

	public Account getCurrentUserAccount(Customer customer) {
		Account account = null;

		try {
			// Prepare the SQL statement to retrieve the account
			String sql = "SELECT * FROM Account WHERE account_number = ?";

			// Create a PreparedStatement with the SQL statement
			PreparedStatement statement = connection.prepareStatement(sql);

			// Set the parameter values
			statement.setString(1, customer.getCustomerBankAccount());

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Check if the account exists
			if (resultSet.next()) {
				// Retrieve the account details from the result set
				String accountNumber = resultSet.getString("account_number");
				String accountOtp = resultSet.getString("account_otp");
				double accountBalance = resultSet.getDouble("account_balance");
				boolean accountVipStatus = resultSet.getBoolean("account_vipStatus");
				double accountTotalSavings = resultSet.getDouble("account_totalSavings");
				String accountSavingsId = resultSet.getString("account_savingsId");
				boolean accountIsLocked = resultSet.getBoolean("account_isLocked");

				// Create an Account object with the retrieved details
				account = new Account(accountNumber, accountOtp, accountBalance, accountVipStatus, accountTotalSavings,
						accountSavingsId, accountIsLocked);
			}

		} catch (SQLException e) {
			// Handle any errors that occur during the database access
			e.printStackTrace();
		}

		return account;
	}

	public void logTransaction(Transaction transaction) {
		String query = "INSERT INTO Transaction_ (transaction_id, transaction_fromAccount, transaction_toAccount, "
				+ "transaction_amount, transaction_date, transaction_status, transaction_content, transaction_type, transaction_billDetails) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Gson gson = new Gson();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			// Set values for the prepared statement
			statement.setString(1, transaction.getTransactionId());
			statement.setString(2, transaction.getFromAccount());
			statement.setString(3, transaction.getToAccount());
			statement.setDouble(4, transaction.getAmount());
			statement.setDate(5, transaction.getDate());
			statement.setBoolean(6, transaction.getStatus());
			statement.setString(7, transaction.getContent());
			statement.setString(8, transaction.getTransactionType());
			statement.setString(9, gson.toJson(transaction.getBillDetails()));
			// Execute the query
			statement.executeUpdate();

			System.out.println("Transaction added successfully.");
		} catch (SQLException e) {
			System.out.println("Error adding transaction: " + e.getMessage());
		}
	}

	public boolean isAccountNumberExists(String accountNumber) {
		boolean exists = false;

		try {
			// Prepare the SQL query
			String sql = "SELECT COUNT(*) FROM Account WHERE account_number = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Set the account number parameter
			statement.setString(1, accountNumber);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Check the result
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				if (count > 0) {
					exists = true; // Account number exists
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any exceptions
		}

		return exists;
	}

	public void addBill(Bill bill) {
		String query = "INSERT INTO Bill (bill_id, bill_createdBy, bill_accountReceive, bill_accountPaid, "
				+ "bill_paidDate, bill_isPaid, bill_dueDate, bill_details) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Gson gson = new Gson();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, bill.getBillId());
			statement.setString(2, bill.getBillCreatedBy());
			statement.setString(3, bill.getBillAccountReceive());
			statement.setString(4, bill.getBillAccountPaid());

//            statement.setDate(6, bill.getBillPaidDate());
			statement.setDate(5, null);
			statement.setBoolean(6, bill.isBillIsPaid());
			statement.setDate(7, bill.getBillDueDate());
			statement.setString(8, gson.toJson(bill.getBillDetails()));

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Bill added successfully.");
			} else {
				System.out.println("Failed to add the bill.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while adding the bill.");
			e.printStackTrace();
		}
	}

	public ArrayList<Bill> getBillsByAccountReceive(String accountReceive) {
		ArrayList<Bill> bills = new ArrayList<>();
		Gson gson = new Gson();
		try {
			// Prepare the SQL statement
			String query = "SELECT * FROM Bill WHERE bill_accountReceive = ? and bill_isPaid = 0";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, accountReceive);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Process the results
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setBillId(resultSet.getString("bill_id"));
				bill.setBillCreatedBy(resultSet.getString("bill_createdBy"));
				bill.setBillAccountReceive(resultSet.getString("bill_accountReceive"));
				bill.setBillAccountPaid(resultSet.getString("bill_accountPaid"));

				bill.setBillPaidDate(resultSet.getDate("bill_paidDate"));
				bill.setBillIsPaid(resultSet.getBoolean("bill_isPaid"));

				bill.setBillDueDate(resultSet.getDate("bill_dueDate"));
				// THEM HASHMAP
				bill.setBillDetails(gson.fromJson(resultSet.getString("bill_details"), HashMap.class));
				bills.add(bill);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any potential exceptions
		}

		return bills;
	}

	public ArrayList<Bill> getBillsByAccountPaid(String accountPaid) {
		ArrayList<Bill> bills = new ArrayList<>();
		Gson gson = new Gson();
		try {
			// Prepare the SQL statement
			String query = "SELECT * FROM Bill WHERE bill_accountPaid = ? and bill_isPaid = 0";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, accountPaid);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// Process the results
			while (resultSet.next()) {
				Bill bill = new Bill();
				bill.setBillId(resultSet.getString("bill_id"));
				bill.setBillCreatedBy(resultSet.getString("bill_createdBy"));
				bill.setBillAccountReceive(resultSet.getString("bill_accountReceive"));
				bill.setBillAccountPaid(resultSet.getString("bill_accountPaid"));

				bill.setBillPaidDate(resultSet.getDate("bill_paidDate"));
				bill.setBillIsPaid(resultSet.getBoolean("bill_isPaid"));

				bill.setBillDueDate(resultSet.getDate("bill_dueDate"));
				// THEM HASHMAP
				bill.setBillDetails(gson.fromJson(resultSet.getString("bill_details"), HashMap.class));
				bills.add(bill);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle any potential exceptions
		}

		return bills;
	}

	public Bill getBillById(String billId) {
		Bill bill = null;
		String query = "SELECT * FROM Bill WHERE bill_id = ?";
		Gson gson = new Gson();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, billId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				bill = new Bill();
				bill.setBillId(resultSet.getString("bill_id"));
				bill.setBillCreatedBy(resultSet.getString("bill_createdBy"));
				bill.setBillAccountReceive(resultSet.getString("bill_accountReceive"));
				bill.setBillAccountPaid(resultSet.getString("bill_accountPaid"));

				bill.setBillPaidDate(resultSet.getDate("bill_paidDate"));
				bill.setBillIsPaid(resultSet.getBoolean("bill_isPaid"));

				bill.setBillDueDate(resultSet.getDate("bill_dueDate"));
				bill.setBillDetails(gson.fromJson(resultSet.getString("bill_details"), HashMap.class));
				// THEM HASHMAP
			}


		} catch (SQLException e) {
			// Handle any exceptions or log the error
			e.printStackTrace();
		}

		return bill;
	}

	public Customer getCustomerByBankAccount(String bankAccount) {
		Customer customer = null;
		String query = "SELECT * FROM Customer WHERE customer_bankAccount = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, bankAccount);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getString("customer_id"));
				customer.setCustomerName(resultSet.getString("customer_name"));
				customer.setCustomerDob(resultSet.getDate("customer_dob").toLocalDate());
				customer.setCustomerAddress(resultSet.getString("customer_address"));
				customer.setCustomerPhoneNumber(resultSet.getString("customer_phoneNumber"));
				customer.setCustomerEmail(resultSet.getString("customer_email"));
				customer.setCustomerUsername(resultSet.getString("customer_username"));
				customer.setCustomerPassword(resultSet.getString("customer_password"));
				customer.setCustomerBankAccount(resultSet.getString("customer_bankAccount"));
				customer.setLoginAttempts(resultSet.getInt("customer_loginAttempts"));
			}

			
		} catch (SQLException e) {
			// Handle any exceptions or log the error
			e.printStackTrace();
		}

		return customer;
	}

	public void markBillAsPaid(String billId) throws SQLException {
		String sql = "UPDATE Bill SET bill_isPaid = 1 WHERE bill_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, billId);
			statement.executeUpdate();
		}
	}

	public void updateBillPaidDate(String billId, Date paidDate) throws SQLException {
		String sql = "UPDATE Bill SET bill_paidDate = ? WHERE bill_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDate(1, paidDate);
			statement.setString(2, billId);
			statement.executeUpdate();
		}
	}
	
	public void insertRequest(Request request) throws SQLException {
        String sql = "INSERT INTO Request (request_id, request_type, request_by, request_date, request_content, request_status, request_resolvedBy) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, request.getRequestId());
            statement.setString(2, request.getRequestType());
            statement.setString(3, request.getRequestBy());
            statement.setDate(4, request.getRequestDate());
            statement.setString(5, request.getRequestContent());
            statement.setBoolean(6, request.getRequestStatus());
            statement.setString(7, request.getRequestResolvedBy());

            statement.executeUpdate();
        }
    }
	
	public String getBalanceFlucAsJson(Customer currentCustomer) throws SQLException {
		String json = null;
		ArrayList<Double> listAmount = new ArrayList<Double>();
		listAmount.add(getDefaultBalanceValue(currentCustomer));
		String sql = "select Transaction_.transaction_amount as amount,\r\n"
				+ "case\r\n"
				+ "	when Transaction_.transaction_fromAccount = ? then -1 * Transaction_.transaction_amount\r\n"
				+ "	when Transaction_.transaction_toAccount = ? then  Transaction_.transaction_amount\r\n"
				+ "	end as result_column\r\n"
				+ "from Transaction_\r\n"
				+ "where Transaction_.transaction_fromAccount = ? or Transaction_.transaction_toAccount = ? order by transaction_date asc ";

		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, currentCustomer.getCustomerBankAccount());
			statement.setString(2, currentCustomer.getCustomerBankAccount());
			statement.setString(3, currentCustomer.getCustomerBankAccount());
			statement.setString(4, currentCustomer.getCustomerBankAccount());
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				listAmount.add(rs.getDouble("result_column"));
			}
		}
		
		
		
		
		
		StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < listAmount.size(); i++) {
        	Double amount = listAmount.get(i);
            sb.append("{\"amount\": ")
                .append(amount)
                .append("}");

            if (i < listAmount.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        json = sb.toString();
		return json;
	}
	
	public Double getDefaultBalanceValue(Customer currentCustomer) throws SQLException {
		String sql = "select Account.account_defaultBalance from Account where account_number = ? ";

		Double value = 0.0;
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, currentCustomer.getCustomerBankAccount());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				value = rs.getDouble("account_defaultBalance");
			}
		}
		
		
		return value;
	}
	
	public ArrayList<Transaction> getDisplayTransaction(Customer currentCustomer) throws SQLException {
		String sql = "select *  ,\r\n"
				+ "case\r\n"
				+ "when Transaction_.transaction_fromAccount = ? then -1 * transaction_amount\r\n"
				+ "when  Transaction_.transaction_toAccount = ? then transaction_amount\r\n"
				+ "end as displayAmount\r\n"
				+ "from Transaction_\r\n"
				+ "where Transaction_.transaction_fromAccount = ?\r\n"
				+ "or Transaction_.transaction_toAccount = ? order by transaction_date asc";

		ArrayList<Transaction> listTrans = new ArrayList<Transaction>();
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, currentCustomer.getCustomerBankAccount());
			statement.setString(2, currentCustomer.getCustomerBankAccount());
			statement.setString(3, currentCustomer.getCustomerBankAccount());
			statement.setString(4, currentCustomer.getCustomerBankAccount());
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setFromAccount(rs.getString("transaction_fromAccount"));
				t.setToAccount(rs.getString("transaction_toAccount"));
				t.setAmount(Double.parseDouble(rs.getString("displayAmount")));
				t.setDate(rs.getDate("transaction_date"));
				t.setTransactionType(rs.getString("transaction_type"));
				listTrans.add(t);
			}
		}
		
		
		return listTrans;
	}
	
	public void updateAccountOTP(String accountNumber, String newOTP) throws SQLException {
        String sql = "UPDATE Account SET account_otp = ? WHERE account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newOTP);
            statement.setString(2, accountNumber);
            statement.executeUpdate();
        }
    }
}

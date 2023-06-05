package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

import DatabaseConnection.DatabaseConnection;
import Entity.Account;
import Entity.Customer;
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
			// TODO Auto-generated catch block
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
        String transactionId = transaction.getTransactionId();
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
        String query = "INSERT INTO Transaction_ (transaction_id, transaction_fromAccount, transaction_toAccount, " +
                       "transaction_amount, transaction_date, transaction_status, transaction_content) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set values for the prepared statement
            statement.setString(1, transaction.getTransactionId());
            statement.setString(2, transaction.getFromAccount());
            statement.setString(3, transaction.getToAccount());
            statement.setDouble(4, transaction.getAmount());
            statement.setDate(5, transaction.getDate());
            statement.setBoolean(6, transaction.getStatus());
            statement.setString(7, transaction.getContent());

            // Execute the query
            statement.executeUpdate();

            System.out.println("Transaction added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }
    }
}

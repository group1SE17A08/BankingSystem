package DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.CustomerDAO_Impl;

public class ConnectionTesting {
    public static void main(String[] args) {
        // Obtain the database connection
        Connection connection = DatabaseConnection.getConnection();
        CustomerDAO_Impl bankDao = new CustomerDAO_Impl();	
        System.out.println(bankDao.generateUniqueId());
        if (connection != null) {
            try {
                // Create a statement
                Statement statement = connection.createStatement();

                // Execute a test query
                ResultSet resultSet = statement.executeQuery("SELECT GETDATE() AS CurrentDateTime");

                // Process the query results
                if (resultSet.next()) {
                    String currentDateTime = resultSet.getString("CurrentDateTime");
                    System.out.println("Current database server date and time: " + currentDateTime);
                }

                // Close the result set, statement, and connection
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to execute the test query.");
                e.printStackTrace();
            }
        }
    }
}

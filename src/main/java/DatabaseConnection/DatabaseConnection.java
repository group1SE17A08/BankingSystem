package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DatabaseInformation.DRIVER);
            connection = DriverManager.getConnection(DatabaseInformation.URL, DatabaseInformation.USERNAME, DatabaseInformation.PASSWORD);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to establish the database connection.");
            e.printStackTrace();
        }
        return connection;
    }
}
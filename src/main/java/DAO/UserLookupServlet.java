package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseConnection.DatabaseConnection;

@WebServlet("/user-lookup")
public class UserLookupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = DatabaseConnection.getConnection();

	public UserLookupServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		String receiver = request.getParameter("accountNumber");
		PrintWriter out = response.getWriter();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT customer_name FROM Customer WHERE customer_bankAccount = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, receiver);

			// Execute the query
			rs = stmt.executeQuery();

			// Process the result
			if (rs.next()) {
				String userName = rs.getString("customer_name");
				out.print(userName);
			} else {
				out.print("Unknown");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

package AdminController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ManagerDAO_Impl;
import Entity.AdminUser;

@WebServlet("/create-staff-account")
public class CreatingAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreatingAccountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ManagerDAO_Impl managerDao = new ManagerDAO_Impl();
		HttpSession session = request.getSession();
		ArrayList<AdminUser> listAdminUser = null;

		
		String removeUsername = request.getParameter("removeUsername");
		String action = request.getParameter("action");
		if (session.getAttribute("currentUser") == null) {
			response.sendRedirect("admin-login");
		} else if(action != null && removeUsername != null) {
			try {
				managerDao.removeAdminUserByUsername(removeUsername);
				session.setAttribute("removed", "Account removed successfully!");
				response.sendRedirect("create-staff-account");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				listAdminUser = managerDao.getAllAdminUsers();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("listAdminUser", listAdminUser);
			request.getServletContext().getRequestDispatcher("/manager-createStaffAccount.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action.equals("addAccount")) {
			doPost_createAccount(request, response);
		}
	}
	
	// This function takes input from manager-createStaffAccount.jsp
	// Return: New Staff Account added in the database
	protected void doPost_createAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String staffUsername = request.getParameter("staffUsername");
		String staffPassword = request.getParameter("staffPassword");
		ManagerDAO_Impl managerDao = new ManagerDAO_Impl();
		HttpSession session = request.getSession();	
		try {
			if (managerDao.doesUsernameExist(staffUsername)) {
				session.setAttribute("staffUsernameExisted", "The account cannot be created because the Staff Username is duplicated.");
				response.sendRedirect("create-staff-account");
			} else {
				managerDao.insertStaffAccount(staffUsername, staffPassword);
				session.setAttribute("insertedStaffAccount", "New account added successfully");
				response.sendRedirect("create-staff-account");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

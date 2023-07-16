package AdminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.StaffDAO_Impl;
import Entity.Customer;

@WebServlet("/user-list")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StaffDAO_Impl staffDao = new StaffDAO_Impl();
		ArrayList<Customer> listCustomer = staffDao.getCustomerList();
		HttpSession session = request.getSession();
		request.setAttribute("listCustomer", listCustomer);
		if (session.getAttribute("currentUser") == null) {
			response.sendRedirect("admin-login");
		} else
		request.getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

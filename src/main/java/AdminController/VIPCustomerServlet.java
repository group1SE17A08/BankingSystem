package AdminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ManagerDAO_Impl;
import Entity.Customer;

@WebServlet("/vip-customer")
public class VIPCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VIPCustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ManagerDAO_Impl managerDao = new ManagerDAO_Impl();
		ArrayList<Customer> listVipCustomer = managerDao.getVipCustomerList();
		request.setAttribute("listVipCustomer", listVipCustomer);	
		HttpSession session = request.getSession();	
		if (session.getAttribute("currentUser") == null) {
			response.sendRedirect("admin-login");
		} else
		request.getServletContext().getRequestDispatcher("/manager-viewVIPCustomer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

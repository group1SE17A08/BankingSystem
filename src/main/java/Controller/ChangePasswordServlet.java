package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDAO_Impl;
import Entity.Customer;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePasswordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/change-password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("submitChangePass")) {
			doPost_changePassword(request, response);
		}
	}
	
	protected void doPost_changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("user");
		System.out.println(c);
		String currentPass = request.getParameter("currentPass");
		String newPass = request.getParameter("newPass");
		String confirmPass = request.getParameter("confirmPass");
		System.out.println(c.getCustomerPassword());
		
		if (!currentPass.equals(cusDao.getCustomerPassword(c))) {
			session.setAttribute("failChanging", "You entered wrong current password!");
			response.sendRedirect("change-password");
		} else if (!newPass.equals(confirmPass)) {
			session.setAttribute("failChanging", "Password Confirmation mismatch!");
			response.sendRedirect("change-password");
		} else {
			c.setCustomerPassword(confirmPass);
			cusDao.updateCustomerPassword(c);
			request.setAttribute("redirectToHome", 1);
			session.invalidate();
			request.getServletContext().getRequestDispatcher("/change-password.jsp").forward(request, response);
		}
		
	}
}

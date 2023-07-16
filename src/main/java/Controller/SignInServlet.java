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

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignInServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/sign-in.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("login")) {
			doPost_signIn(request, response);
		}
	}

	protected void doPost_signIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		if (cusDao.getAccountLockStatus(username)) {
			session.setAttribute("loginFail",
					"Your account is now locked, please contact to Staff for more information!");
			request.getServletContext().getRequestDispatcher("/sign-in.jsp").forward(request, response);
		} else if (cusDao.isUsernameExists(username) == false && username != null) {
			session.setAttribute("loginFail",
					"No username existed");
			request.getServletContext().getRequestDispatcher("/sign-in.jsp").forward(request, response);
		} else if (cusDao.verifyCustomerLogin(username, password) == false && username != null){
			session.setAttribute("loginFail",
					"Password entered is incorrect! Attempt(s): " + cusDao.getLoginAttempts(username));
			request.getServletContext().getRequestDispatcher("/sign-in.jsp").forward(request, response);
		} else {
			Customer customer = cusDao.verifyLogin(username, password);
			if (customer == null) {
				session.setAttribute("loginFail", "Username not exist!");
				request.getServletContext().getRequestDispatcher("/sign-in.jsp").forward(request, response);
			} else {
				session.setAttribute("user", customer);
				System.out.println("cus homepage is: " + session.getAttribute("user"));
				cusDao.updateLoginAttempts(username, 0);
				response.sendRedirect("homepage");
			}
		}
	}
}

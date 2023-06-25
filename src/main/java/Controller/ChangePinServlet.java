package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDAO_Impl;
import Entity.Account;
import Entity.Customer;

@WebServlet("/change-pin")
public class ChangePinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePinServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/change-pin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("submitChangePin")) {
			doPost_changePin(request, response);
		}
	}
	protected void doPost_changePin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("user");
		Account acc = cusDao.getCurrentUserAccount(c);
		
		String currentPin = request.getParameter("currentPin");
		String newPin = request.getParameter("newPin");
		String confirmPin = request.getParameter("confirmPin");
		
		if (!currentPin.equals(acc.getAccountOTP())) {
			session.setAttribute("failChanging", "You entered wrong current pin!");
			response.sendRedirect("change-pin");
		} else if (!newPin.equals(confirmPin)) {
			session.setAttribute("failChanging", "Pin Confirmation mismatch!");
			response.sendRedirect("change-pin");
		} else {
			acc.setAccountOTP(confirmPin);
			try {
				cusDao.updateAccountOTP(acc.getAccountNumber(), confirmPin);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("redirectToHome", 1);
			session.invalidate();
			request.getServletContext().getRequestDispatcher("/change-pin.jsp").forward(request, response);
		}
	}
}

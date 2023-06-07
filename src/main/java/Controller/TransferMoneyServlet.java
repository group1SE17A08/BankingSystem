package Controller;

import java.io.IOException;
import java.sql.Date;
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
import Entity.Transaction;

@WebServlet("/transfer-money")
public class TransferMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TransferMoneyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("user");
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		
		Account acc = cusDao.getCurrentUserAccount(c);
		session.setAttribute("currentAcc", acc);
		session.setAttribute("accNumFormatted", acc.formatAccountNumber());
		request.getServletContext().getRequestDispatcher("/transfer-money.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("submitTransaction")) {
			doPost_submitTransaction(request, response);
		}
	}

	protected void doPost_submitTransaction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		System.out.println("customer is: " + customer);
		System.out.println("customer session is: " + session.getAttribute("user"));
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		Transaction t = new Transaction();
		long currentTimeMillis = System.currentTimeMillis();
		java.util.Date date = new Date(currentTimeMillis);
		Date currentDate = new Date(date.getTime());
		Account currentAcc = cusDao.getCurrentUserAccount(customer);
		
		
		
		String otp = request.getParameter("smartOtp");
		if (!otp.equals(currentAcc.getAccountOTP())) {
			session.setAttribute("transactionErr", "Smart OTP is incorrect");
			response.sendRedirect("transfer-money");
		} else if (currentAcc.getAccountNumber().equals(request.getParameter("accountReceive"))) {
			session.setAttribute("transactionErr", "You can't transfer money to yourself!");
			response.sendRedirect("transfer-money");
		} else if (Double.parseDouble(request.getParameter("moneyAmount")) > currentAcc.getAccountBalance()) {
			session.setAttribute("transactionErr", "Insufficient Funds");
			response.sendRedirect("transfer-money");
		}
		else if(!cusDao.isAccountNumberExists(request.getParameter("accountReceive"))) {
			session.setAttribute("transactionErr", "Cannot transfer money to Unknown User!");
			response.sendRedirect("transfer-money");
		}
		else {
			try {
				t.setFromAccount(currentAcc.getAccountNumber());
				t.setToAccount(request.getParameter("accountReceive"));
				t.setAmount(Double.parseDouble(request.getParameter("moneyAmount")));
				t.setDate(currentDate);
				t.setStatus(true);
				t.setContent(request.getParameter("transactionContent"));
				t.setTransactionType("Transfer Money");
				cusDao.performTransaction(t);
				cusDao.logTransaction(t);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("successfulTransfer", 1);
			response.sendRedirect("transfer-money");
		}
	}
}

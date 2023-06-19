package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

@WebServlet("/transaction-history")
public class TransactionHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TransactionHistoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();
		
		Customer currentCustomer = (Customer) session.getAttribute("user");
		String data = null;
		try {
			data = cusDao.getBalanceFlucAsJson(currentCustomer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		Account acc = cusDao.getCurrentUserAccount(currentCustomer);
		request.setAttribute("accNumFormatted", acc.formatAccountNumber());
		request.setAttribute("currentAcc", acc);
		request.setAttribute("data", data);
		
		ArrayList<Transaction> listTrans = new ArrayList<Transaction>();
		try {
			listTrans = cusDao.getDisplayTransaction(currentCustomer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("listTrans", listTrans);
		
		request.getServletContext().getRequestDispatcher("/transaction-history.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

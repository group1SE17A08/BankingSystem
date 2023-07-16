package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entity.Customer;
import Entity.Savings;
import Entity.Transaction;

@WebServlet("/takeout-saving")
public class TakeOutSavingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TakeOutSavingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("savingId");
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("user");
		
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		StaffDAO_Impl staffDao = new StaffDAO_Impl();
		
		Savings currentSaving = null;
		try {
			currentSaving = cusDao.getSavingsById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		staffDao.updateInProcess(id, false);
		long currentTimeMillis = System.currentTimeMillis();
		Timestamp currentDate = new Timestamp(currentTimeMillis);
		cusDao.performTakeOutSaving(currentSaving);
		Transaction t = new Transaction();
		t.setFromAccount("BankingSystem-Ad");
		t.setToAccount(c.getCustomerBankAccount());
		t.setAmount(currentSaving.currentAmount());
		t.setDate(currentDate);
		t.setStatus(true);
		t.setContent("Saving Taking Out - " + currentSaving.currentAmount());
		t.setTransactionType("Savings");
		t.setBillDetails(null);
		
		cusDao.logTransaction(t);

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		out.print("success");
	}

}

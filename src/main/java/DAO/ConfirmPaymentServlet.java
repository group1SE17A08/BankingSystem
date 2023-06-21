package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Entity.Bill;
import Entity.Transaction;

@WebServlet("/confirm-payment")
public class ConfirmPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmPaymentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String billId = request.getParameter("billId");
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		Transaction t = new Transaction();
		Bill b = cusDao.getBillById(billId);
		
		long currentTimeMillis = System.currentTimeMillis();
<<<<<<< HEAD
		
		Timestamp currentDate = new Timestamp(currentTimeMillis);
		
=======
		java.util.Date date = new Date(currentTimeMillis);
		Date currentDate = new Date(date.getTime());
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
		Gson gson = new Gson();

		t.setFromAccount(b.getBillAccountPaid());
		t.setToAccount(b.getBillAccountReceive());

		t.setDate(currentDate);
		t.setStatus(true);

		t.setBillDetails(b.getBillDetails());
		t.setTransactionType("Invoice Payment");

		double totalAmount = 0;
		for (Double value : b.getBillDetails().values()) {
			totalAmount += value;
		}
		
		t.setAmount(totalAmount);
		try {
			cusDao.performTransaction(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cusDao.logTransaction(t);
		try {
			cusDao.markBillAsPaid(billId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cusDao.updateBillPaidDate(billId, currentDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

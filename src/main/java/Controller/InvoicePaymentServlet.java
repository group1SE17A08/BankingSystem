package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDAO_Impl;
import Entity.Account;
import Entity.Bill;
import Entity.Customer;

@WebServlet("/pay-invoice")
public class InvoicePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InvoicePaymentServlet() {
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
		
		Bill creatingBill = new Bill();
		
		//bill this user have to pay
		ArrayList<Bill> listBillPaid = cusDao.getBillsByAccountPaid(acc.getAccountNumber());
		request.setAttribute("listBillPaid", listBillPaid);
		
		session.setAttribute("creatingBill", creatingBill);	
		request.getServletContext().getRequestDispatcher("/invoice-payment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("submitPayForOther")) {
			doPost_proceedPayment(request, response);
		}
	}
	
	protected void doPost_proceedPayment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String billId = request.getParameter("billId");
		response.sendRedirect("view-invoice?billId=" + billId +"&isMyBill=false");
	}
}

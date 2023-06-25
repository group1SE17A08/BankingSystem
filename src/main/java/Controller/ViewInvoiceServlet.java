package Controller;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO_Impl;
import Entity.Bill;
import Entity.Customer;

@WebServlet("/view-invoice")
public class ViewInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewInvoiceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		Bill currentBill = cusDao.getBillById(request.getParameter("billId"));
		
		double totalAmount = 0;
		for (Entry<String, Double> entry : currentBill.getBillDetails().entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();
		    totalAmount += value;
		}
		
		currentBill.setBillAmount(totalAmount);
		Customer billTo = cusDao.getCustomerByBankAccount(currentBill.getBillAccountPaid());
		Customer billFrom = cusDao.getCustomerByBankAccount(currentBill.getBillAccountReceive());
		//Neu khong phai bill cua minh` tao ra -> hien nut Pay Now
		if (Boolean.valueOf(request.getParameter("isMyBill")) == false) {
			request.setAttribute("displayPayButton", 1);
		}
		request.setAttribute("billFrom", billFrom);
		request.setAttribute("billTo", billTo);
		
		request.setAttribute("currentBill", currentBill);
		request.getServletContext().getRequestDispatcher("/view-invoice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

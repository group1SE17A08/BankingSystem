package Controller;

import java.io.IOException;
import java.sql.Date;
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

@WebServlet("/create-invoice")
public class InvoiceCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InvoiceCreateServlet() {
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
		
		//bill this user created
		ArrayList<Bill> listBillCreated = cusDao.getBillsByAccountReceive(acc.getAccountNumber());
		session.setAttribute("listBillCreated", listBillCreated);
		//bill this user have to pay
		ArrayList<Bill> listBillPaid = cusDao.getBillsByAccountPaid(acc.getAccountNumber());
		session.setAttribute("listBillPaid", listBillPaid);
		
		session.setAttribute("creatingBill", creatingBill);	
		request.getServletContext().getRequestDispatcher("/create-bill.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("submitBill")) {
			doPost_createBill(request, response);
		}
	}

	protected void doPost_createBill(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Bill creatingBill = (Bill) session.getAttribute("creatingBill");
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		Customer currentUser = (Customer) session.getAttribute("user");
		
		//Lay ngay` tao bill
		long currentTimeMillis = System.currentTimeMillis();
		java.util.Date date = new Date(currentTimeMillis);
		Date currentDate = new Date(date.getTime());
		
		//Lay ngay` due bill
		Date dueDate = Date.valueOf(request.getParameter("dueDate"));
		
		
		
		
		if (!cusDao.isAccountNumberExists(request.getParameter("billTo"))) {
			session.setAttribute("generateInvErr", "You cannot create an Invoice to Unknown User!");
			response.sendRedirect("create-invoice");
		} else {
			//Bill id da~ set
			creatingBill.setBillCreatedBy(currentUser.getCustomerId());
			creatingBill.setBillAccountReceive(cusDao.getCurrentUserAccount(currentUser).getAccountNumber());
			creatingBill.setBillAccountPaid(request.getParameter("billTo"));
			creatingBill.setBillAmount(Double.parseDouble(request.getParameter("billAmount")));
			creatingBill.setBillPaidDate(currentDate);
			creatingBill.setBillIsPaid(false);
			creatingBill.setBillContent(request.getParameter("billContent"));
			creatingBill.setBillDueDate(dueDate);
			
			cusDao.addBill(creatingBill);
			session.setAttribute("successfulGenerateInv", 1);
			response.sendRedirect("create-invoice");
		}
	}
	
    
}

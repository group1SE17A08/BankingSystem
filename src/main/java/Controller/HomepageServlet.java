package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.CustomerDAO_Impl;
import DAO.StaffDAO_Impl;
import Entity.Account;
import Entity.Customer;
import Entity.Request;
import Entity.Savings;
import Entity.Transaction;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomepageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("user");
		
		if (c != null) {
			Account acc = cusDao.getCurrentUserAccount(c);
			ArrayList<Savings> currentSaving = cusDao.getCurrentCustomerSaving(c);
			request.setAttribute("saving", currentSaving);
			request.setAttribute("account", acc);
		}
		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String buttonPressed = request.getParameter("action");
		if (buttonPressed.equals("signIn")) {
			doPost_signIn(request, response);
		} else if (buttonPressed.equals("logOut")) {
			doPost_logOut(request, response);
		} else if (buttonPressed.equals("reqSubmit")) {
			doPost_takeReq(request, response);
		} else if (buttonPressed.equals("unlockSubmit")) {
			doPost_takeUnlockReq(request, response);
		}
	}

	protected void doPost_signIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("sign-in");
	}

	protected void doPost_logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("homepage");
	}

	protected void doPost_takeReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqReason = request.getParameter("reqReason");
		String reqAmount = request.getParameter("reqAmount");

		String savingTerm = request.getParameter("monthOption");

		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();

		long currentTimeMillis = System.currentTimeMillis();

		Timestamp currentDate = new Timestamp(currentTimeMillis);
		HashMap<Double, String> savings = null;
		Customer cus = (Customer) session.getAttribute("user");
		

		if (savingTerm != null) {
			savings = new HashMap<Double, String>();
			savings.put(Double.parseDouble(reqAmount), savingTerm);
		}

		Gson gson = new Gson();

		Request req = new Request();
		req.setRequestId(req.generateRequestId());

		if (reqReason != null) {
			req.setRequestType("Lock Acc.");
			req.setRequestBy(cus.getCustomerId());
			req.setRequestContent(reqReason);
			req.setRequestDate(currentDate);
			req.setRequestStatus(false);
			req.setRequestResolvedBy(null);
			try {
				cusDao.insertRequest(req);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("successReq", "Request Success, You are going to redirected to homepage.");
			response.sendRedirect("homepage");
		} else if (Double.parseDouble(reqAmount) <= cusDao.getCurrentUserAccount(cus).getAccountBalance()){
			req.setRequestType("Savings");
			req.setRequestBy(cus.getCustomerId());
			req.setRequestContent(gson.toJson(savings));
			req.setRequestDate(currentDate);
			req.setRequestStatus(false);
			req.setRequestResolvedBy(null);
			try {
				cusDao.insertRequest(req);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("successReq", "Request Success, You are going to redirected to homepage.");
			response.sendRedirect("homepage");
		} else {
			session.setAttribute("failReq", "Your request is not complete. Please check your balance before Request");
			response.sendRedirect("homepage");
		}

	}
	

	
	protected void doPost_takeUnlockReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUsername = request.getParameter("username");
		String reqEmail = request.getParameter("email");
		String reason = request.getParameter("reason");
		
		long currentTimeMillis = System.currentTimeMillis();
		Timestamp currentDate = new Timestamp(currentTimeMillis);
		
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();
		
		Request req = new Request();
		req.setRequestId(req.generateRequestId());
		req.setRequestType("Unlock Acc.");
		req.setRequestBy(cusDao.getCustomerIdByUsername(reqUsername));
		req.setRequestContent("Request: Username: " + reqUsername + " - Email: " + reqEmail + " - Reason: " + reason);
		req.setRequestDate(currentDate);
		req.setRequestStatus(false);
		req.setRequestResolvedBy(null);
		
		try {
			cusDao.insertRequest(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("successReq", "Request Success, You are going to redirected to homepage.");
		response.sendRedirect("homepage");

	}
}

package AdminController;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.CustomerServices;
import Controller.EmailUtility;
import DAO.StaffDAO_Impl;
import Entity.AdminUser;
import Entity.Customer;
import Entity.Request;
import Entity.Savings;

@WebServlet("/request-list")
public class RequestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String email;
	private String name;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		email = context.getInitParameter("email");
		name = context.getInitParameter("name");
		pass = context.getInitParameter("pass");
	}

	public RequestListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StaffDAO_Impl staffDao = new StaffDAO_Impl();
		ArrayList<Request> listRequest = staffDao.getListRequest();
		request.setAttribute("listRequest", listRequest);
		System.out.println(host + port + email + name + pass);
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		if (action != null) {
			if (action.equals("reject")) {
				doPost_reject(request, response, id);
				System.out.println(action);
				response.sendRedirect("request-list");
			} else if (action.equals("approve")) {
				doPost_approve(request, response, id);
				System.out.println(action);
				response.sendRedirect("request-list");
			}
		} else {
			request.getServletContext().getRequestDispatcher("/request-list.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPost_reject(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		StaffDAO_Impl staffDao = new StaffDAO_Impl();
		Request req = staffDao.getRequestFromId(id);
		HttpSession session = request.getSession();
		AdminUser admin = (AdminUser) session.getAttribute("adminUser");
		System.out.println(req.getRequestBy());
		String recipient = staffDao.getEmailByCustomerId(req.getRequestBy()); // customerid
		System.out.println(recipient);

		String subject = "About your request on our our system.";
		CustomerServices customerServices = new CustomerServices(request, response);
		String content = "We are so sorry to annouce that your request has been rejected.";

		if (req.getRequestType().equals("Lock Acc.") || req.getRequestType().equals("Unlock Acc.")) {
			content += "\nYour request: " + req.getRequestContent();
		} else if (req.getRequestType().equals("Savings")) {
			content += "\nYour Savings request: ";
			for (Entry<String, String> entry : req.getRequestSavings().entrySet()) {
				String key = entry.getKey();
				Double amount = Double.valueOf(key);

				String value = entry.getValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
				java.util.Date utilDate = null;
				try {
					utilDate = dateFormat.parse(value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date date = new Date(utilDate.getTime());

				content += "\nAmount: " + amount;
				content += "\nMaturity Date: " + date;
			}
		} else if (req.getRequestType().equals("Request Information Changing")) {
			content += "\nYour Changing Information request: ";
			for (Entry<String, String> entry : req.getRequestInformationChanging().entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				content += "\n" + key + ": " + value;
			}
		}
		String message = "";

		req.setRequestStatus(true);
		req.setRequestResolvedBy(admin.getAdminUser_userId());
		req.setRejected(true);

		try {
			staffDao.updateRequest(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			EmailUtility.sendEmail(host, port, email, name, pass, recipient, subject, content);
			message = "Your password has been reset. Please check your e-mail.";
		} catch (Exception ex) {
			ex.printStackTrace();
			message = "There were an error: " + ex.getMessage();
		}
	}

	protected void doPost_approve(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		StaffDAO_Impl staffDao = new StaffDAO_Impl();
		Request req = staffDao.getRequestFromId(id);
		HttpSession session = request.getSession();
		AdminUser admin = (AdminUser) session.getAttribute("adminUser");
		String recipient = staffDao.getEmailByCustomerId(req.getRequestBy());

		String subject = "About your request on our our system.";
		CustomerServices customerServices = new CustomerServices(request, response);
		String content = "We are glad to annouce that your request has been approved.";
		System.out.println(req.getRequestSavings());

		Double amount = 0.0;
		Date date = null;

		if (req.getRequestType().equals("Lock Acc.") || req.getRequestType().equals("Unlock Acc.")) {
			content += "\nYour request: " + req.getRequestContent();
			if (req.getRequestType().equals("Lock Acc.")) {
				try {
					staffDao.updateLockStatus(req.getRequestBy(), true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (req.getRequestType().equals("Unlock Acc.")) {
				try {
					staffDao.updateLockStatus(req.getRequestBy(), false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (req.getRequestType().equals("Savings")) {
			content += "\nYour Savings request: ";
			for (Entry<String, String> entry : req.getRequestSavings().entrySet()) {
				String key = entry.getKey();
				amount = Double.valueOf(key);

				String value = entry.getValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
				java.util.Date utilDate = null;
				try {
					utilDate = dateFormat.parse(value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				date = new Date(utilDate.getTime());

				content += "\nAmount: " + amount;
				content += "\nMaturity Date: " + date;
			}
			
			Savings s = new Savings();
			s.setSavingsId(s.generateRandomString());

			if (staffDao.getVipStatus(req.getRequestBy()) == true) {
				s.setSavingsInterest(0.1);
			} else {
				s.setSavingsInterest(0.07);
			}

			s.setSavingsMaturityDay(date);
			s.setSavingsOwner(req.getRequestBy());
			s.setSavingsAmount(amount);
			try {
				staffDao.insertSavings(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (req.getRequestType().equals("Request Information Changing")) {
			content += "\nYour Changing Information request: ";
			ArrayList<String> listObject = new ArrayList<String>();
			for (Entry<String, String> entry : req.getRequestInformationChanging().entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				listObject.add(value);
				content += "\n" + key + ": " + value;
			}
			
			Customer c = new Customer();
			c.setCustomerId(req.getRequestBy());
			c.setCustomerAddress(listObject.get(0));
			c.setCustomerPhoneNumber(listObject.get(1));
			c.setCustomerDob(Date.valueOf(listObject.get(2)));
			c.setCustomerName(listObject.get(3));
			c.setCustomerEmail(listObject.get(4));
			
			try {
				staffDao.updateInformation(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		String message = "";

		req.setRequestStatus(true);
		req.setRequestResolvedBy(admin.getAdminUser_userId());
		req.setRejected(false);

		try {
			staffDao.updateRequest(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			EmailUtility.sendEmail(host, port, email, name, pass, recipient, subject, content);
			message = "Your password has been reset. Please check your e-mail.";
		} catch (Exception ex) {
			ex.printStackTrace();
			message = "There were an error: " + ex.getMessage();
		}
	}
}

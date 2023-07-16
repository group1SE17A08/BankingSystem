package Controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDAO_Impl;

/**
 * A Java Servlet to handle requests to reset password for customer
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/reset_password")
public class ResetPasswordServlet extends HttpServlet {
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

	public ResetPasswordServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = "forget-pass.jsp";
		request.getRequestDispatcher(page).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recipient = request.getParameter("email");
		HttpSession session = request.getSession();
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		if (!cusDao.isEmailExist(recipient)) {
			session.setAttribute("noEmail", "The email you entered is not associated with any account in our system!");
			response.sendRedirect("reset_password");
		} else {
			String subject = "Your Password has been reset";

			CustomerServices customerServices = new CustomerServices(request, response);
			String newPassword = customerServices.resetCustomerPassword(recipient);

			String content = "Hi, this is your new password: " + newPassword;
			content += "\nNote: for security reason, " + "you must change your password after logging in.";

			String message = "";

			try {
				EmailUtility.sendEmail(host, port, email, name, pass, recipient, subject, content);
				message = "Your password has been reset. Please check your e-mail.";
			} catch (Exception ex) {
				ex.printStackTrace();
				message = "There were an error: " + ex.getMessage();
			} finally {
				request.setAttribute("message", message);
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
		}
	}
}
package Controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDAO_Impl;
import Entity.Account;
import Entity.Customer;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/sign-up.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("signUp")) {
			doPost_signUp(request, response);
		}
	}

	protected void doPost_signUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String dob = request.getParameter("dateOfBirth");

		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		String cfmPassword = request.getParameter("confirm_pass");
		String email = request.getParameter("email");

		String accountNumber = request.getParameter("accountNumber");
		String otp = request.getParameter("otp");
		String cfmOtp = request.getParameter("confirm_otp");

		Customer customer = new Customer();
		Account account = new Account();
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();

		System.out.println(name + address + phoneNumber + dob + username + password + cfmPassword + email
				+ accountNumber + otp + cfmOtp);
		;

		if (!password.equals(cfmPassword)) {
			session.setAttribute("failPasswordCfm", "Password Confirmation mismatch");
			session.removeAttribute("phoneNumberDuplicated");
			session.removeAttribute("failOtpCfm");
			session.removeAttribute("accNumExisted");
			session.removeAttribute("usernameDuplicated");
			session.removeAttribute("emailDuplicated");
			session.removeAttribute("phoneNumberDuplicated");		
			response.sendRedirect("sign-up");
		} else if (!otp.equals(cfmOtp)) {
			session.setAttribute("failOtpCfm", "Smart OTP Confirmation mismatch");
			session.removeAttribute("phoneNumberDuplicated");
			session.removeAttribute("failPasswordCfm");
			session.removeAttribute("accNumExisted");
			session.removeAttribute("usernameDuplicated");
			session.removeAttribute("emailDuplicated");
			session.removeAttribute("phoneNumberDuplicated");
			response.sendRedirect("sign-up");
		} else if (cusDao.accNumValidation(accountNumber)) {
			session.setAttribute("accNumExisted", "The account number you want existed in database!");
			session.removeAttribute("phoneNumberDuplicated");
			session.removeAttribute("failOtpCfm");
			session.removeAttribute("failPasswordCfm");
			session.removeAttribute("usernameDuplicated");
			session.removeAttribute("emailDuplicated");
			session.removeAttribute("phoneNumberDuplicated");
			response.sendRedirect("sign-up");
		} else if (cusDao.isUsernameDuplicated(username)) {
			session.setAttribute("usernameDuplicated", "The username you entered existed in database!");
			session.removeAttribute("phoneNumberDuplicated");
			session.removeAttribute("failOtpCfm");
			session.removeAttribute("accNumExisted");
			session.removeAttribute("failPasswordCfm");
			session.removeAttribute("emailDuplicated");
			session.removeAttribute("phoneNumberDuplicated");
			response.sendRedirect("sign-up");
		} else if (cusDao.isEmailDuplicated(email)) {
			session.setAttribute("emailDuplicated", "This email has been used for another account!");
			session.removeAttribute("phoneNumberDuplicated");
			session.removeAttribute("failOtpCfm");
			session.removeAttribute("accNumExisted");
			session.removeAttribute("usernameDuplicated");
			session.removeAttribute("failPasswordCfm");
			session.removeAttribute("phoneNumberDuplicated");
			response.sendRedirect("sign-up");
		} else if (cusDao.isPhoneNumberDuplicated(phoneNumber)) {
			session.setAttribute("phoneNumberDuplicated", "This phone number has been used for another account!");
			session.removeAttribute("failPasswordCfm");
			session.removeAttribute("failOtpCfm");
			session.removeAttribute("accNumExisted");
			session.removeAttribute("usernameDuplicated");
			session.removeAttribute("emailDuplicated");
			session.removeAttribute("failPasswordCfm");
			response.sendRedirect("sign-up"); //Nen xem lai dat response.sendRedirect cho nao hop ly
		} else {
			account.setAccountNumber(accountNumber);
			account.setAccountOTP(otp);
			account.setAccountLocked(false);
			cusDao.addAccount(account);
			
			customer.setCustomerId(cusDao.generateUniqueId());
			customer.setCustomerName(name);
			customer.setCustomerAddress(address);
			customer.setCustomerPhoneNumber(phoneNumber);
			customer.setCustomerDob(LocalDate.parse(dob));
			customer.setCustomerEmail(email);
			customer.setCustomerUsername(username);
			customer.setCustomerPassword(cfmPassword);
			customer.setCustomerBankAccount(accountNumber);
			customer.setLoginAttempts(0);
			cusDao.signUp(customer);

			

			session.setAttribute("successSignUp", "You have sign up successfully! Now you can log in to our system!");
			response.sendRedirect("sign-in");
		}
	}
}

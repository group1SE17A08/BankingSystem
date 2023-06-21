package Controller;

import java.io.IOException;
<<<<<<< HEAD
import java.sql.SQLException;
import java.sql.Timestamp;
=======
import java.sql.Date;
import java.sql.SQLException;
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.CustomerDAO_Impl;
import Entity.Customer;
import Entity.Request;

@WebServlet("/my-profile")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/my-profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("submitRequest")) {
			doPost_requestChange(request, response);
		}
	}
	
	protected void doPost_requestChange(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		String[] input = request.getParameterValues("inputInfor[]");
		HashMap<String, String> inputContent = new HashMap<String, String>();
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();
		HttpSession session = request.getSession();
		Customer cus = (Customer) session.getAttribute("user");
		
		long currentTimeMillis = System.currentTimeMillis();
<<<<<<< HEAD

		Timestamp currentDate = new Timestamp(currentTimeMillis);
=======
		java.util.Date date = new Date(currentTimeMillis);
		Date currentDate = new Date(date.getTime());
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
		
		inputContent.put("name", input[0]);
		inputContent.put("email", input[1]);
		inputContent.put("phone", input[2]);
		inputContent.put("dob", input[3]);
		inputContent.put("address", input[4]);
		
		Request rq = new Request();
		rq.setRequestBy(cus.getCustomerId());
		rq.setRequestType("Request Information Changing");
		rq.setRequestDate(currentDate);
		rq.setRequestContent(gson.toJson(inputContent));
		rq.setRequestStatus(false);
		rq.setRequestResolvedBy(null);
		rq.setRequestId(uuid.substring(0, 5));
		try {
			cusDao.insertRequest(rq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("requestSuccess", "1");
		response.sendRedirect("my-profile");
	}
	
}

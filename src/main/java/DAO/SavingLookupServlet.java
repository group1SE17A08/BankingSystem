package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Entity.Savings;

@WebServlet("/saving-lookup")
public class SavingLookupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SavingLookupServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("savingId");
		
		CustomerDAO_Impl cusDao = new CustomerDAO_Impl();	
		ArrayList<String> listResult = new ArrayList<String>();
		Savings s = null;
		
		try {
			s = cusDao.getSavingsById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listResult.add(String.valueOf(s.getSavingsAmount()));
		listResult.add(String.valueOf(s.currentAmount()));
		listResult.add(String.valueOf(s.finalAmount()));
		
		String responseString = new Gson().toJson(listResult);
		out.write(responseString);		
	}

}

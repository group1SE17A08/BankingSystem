package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ManagerDAO_Impl;

@WebServlet("/RemoveVipStatus")
public class RemoveVipStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveVipStatusServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		ManagerDAO_Impl managerDao = new ManagerDAO_Impl();
		managerDao.removeVipStatus(id);
		
		response.sendRedirect("vip-customer");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

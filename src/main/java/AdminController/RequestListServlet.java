package AdminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StaffDAO_Impl;
import Entity.Request;

@WebServlet("/request-list")
public class RequestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RequestListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StaffDAO_Impl staffDao = new StaffDAO_Impl();
		ArrayList<Request> listRequest = staffDao.getListRequest();
		request.setAttribute("listRequest", listRequest);

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

	}

	protected void doPost_reject(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		System.out.println(id);
	}

	protected void doPost_approve(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		System.out.println(id);
	}
}

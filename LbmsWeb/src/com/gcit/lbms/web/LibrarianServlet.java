package com.gcit.lbms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet({ "/checklibrarian","/updateBranchAndCopies"})
public class LibrarianServlet extends HttpServlet {
	BranchUtil branchUtil = new BranchUtil();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = null;
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPath = "Welcome.jsp";

		switch (reqUrl) {
		case "/checklibrarian":
			 
			Boolean flag = branchUtil.isLibrarianValid(request);
			if (flag) {
				message="<div class='alert alert-success alert-dismissible' role='alert'>"
						+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'> "
						+ "<span aria-hidden='true'>&times;</span></button><strong>Congratulations !!</strong> "
						+ "Succefully logged into the Library</div>";	
				
				forwardPath = "/editbranchlibrarian.jsp";
			} else {
				message="<div class='alert alert-danger alert-dismissible' role='alert'>"
						+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'> "
						+ "<span aria-hidden='true'>&times;</span></button><strong>Warning!</strong> "
						+ "Incorrect BranchId Retry</div>";
				forwardPath = "/librarian.jsp";
				
			}
			break;

		}
		if (!reqUrl.contains("/search")) {
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data;
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPath = "Welcome.jsp";

		switch (reqUrl) {
			case "/updateBranchAndCopies":
				branchUtil.updateBranchAndCopies(request);
				break;
		}
		if (!reqUrl.contains("/search")) {
			RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
			rd.forward(request, response);
		}
	}
	

}

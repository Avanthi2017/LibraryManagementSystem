package com.gcit.lbms.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/updateAuthor", "/pageAuthors", "/deleteAuthor", "/searchAuthors", "/addGenre",
		"/updateGenre", "/pageGenres", "/deleteGenre", "/searchGenres", "/addPublisher", "/updatePublisher",
		"/pagePublishers", "/deletePublisher", "/searchPublishers", "/addBorrower", "/updateBorrower", "/pageBorrowers",
		"/deleteBorrower", "/searchBorrowers", "/addBranch", "/pageBranchs", "/deleteBranch",
		"/searchBranchs", "/addBook", "/updateBook", "/pageBooks", "/deleteBook", "/searchBooks","/updateBookCopies","/updateBookLoan",
		"/updatebranch","/addBookCopies"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AuthorUtil authorUtil = new AuthorUtil();
	GenreUtil genreUtil = new GenreUtil();
	PublisherUtil publisherUtil = new PublisherUtil();
	BranchUtil branchUtil = new BranchUtil();
	BorrowerUtil borrowerUtil = new BorrowerUtil();
	BookUtil bookUtil = new BookUtil();
	BookLoanUtil bookLoanUtil= new BookLoanUtil();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data;
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPath = "Welcome.jsp";

		switch (reqUrl) {
		case "/pageAuthors":
			authorUtil.pageAuthors(request);
			forwardPath = "/viewauthor.jsp";
			break;
		case "/pageGenres":
			genreUtil.pageGenres(request);
			forwardPath = "/viewgenre.jsp";
			break;
		case "/pagePublishers":
			publisherUtil.pagePublishers(request);
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/pageBorrowers":
			borrowerUtil.pageBorrowers(request);
			forwardPath = "/viewborrower.jsp";
			break;
		case "/pageBranchs":
			branchUtil.pageBranchs(request);
			forwardPath = "/viewbranch.jsp";
			break;
		case "/pageBooks":
			bookUtil.pageBooks(request);
			forwardPath = "/viewbook.jsp";
			break;
		case "/searchBooks":
			data = bookUtil.searchBooks(request);
			response.getWriter().write(data);
			break;
		case "/searchBorrowers":
			data = borrowerUtil.searchBorrowers(request);
			response.getWriter().write(data);
			break;
		case "/searchBranchs":
			data = branchUtil.searchBranchs(request);
			response.getWriter().write(data);
			break;
		case "/searchAuthors":
			data = authorUtil.searchAuthors(request);
			response.getWriter().write(data);
			break;
		case "/searchGenres":
			data = genreUtil.searchGenres(request);
			response.getWriter().write(data);
			break;
		case "/searchPublishers":
			data = publisherUtil.searchPublishers(request);
			response.getWriter().write(data);
			break;
		case "/deleteAuthor":
			authorUtil.deleteAuthor(request);
			forwardPath = "/viewauthor.jsp";
			break;
		case "/deleteGenre":
			genreUtil.deleteGenre(request);
			forwardPath = "/viewgenre.jsp";
			break;
		case "/deletePublisher":
			publisherUtil.deletePublisher(request);
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/deleteBranch":
			branchUtil.deleteBranch(request);
			forwardPath = "/viewbranch.jsp";
			break;
		case "/deleteBorrower":
			borrowerUtil.deleteBorrower(request);
			forwardPath = "/viewborrower.jsp";
			break;
		case "/deleteBook":
			bookUtil.deleteBook(request);
			forwardPath = "/viewbook.jsp";
			break;
		}
		if (!reqUrl.contains("/search")) {
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
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String forwardPath = "Welcome.jsp";
		AuthorUtil authorUtil = new AuthorUtil();
		switch (reqUrl) {
		case "/addAuthor":
			authorUtil.addAuthor(request);
			forwardPath = "/viewauthor.jsp";
			break;
		case "/updateAuthor":
			authorUtil.editAuthor(request);
			forwardPath = "/viewauthor.jsp";
			break;
		case "/addGenre":
			genreUtil.addGenre(request);
			forwardPath = "/viewgenre.jsp";
			break;
		case "/updateGenre":
			genreUtil.editGenre(request);
			forwardPath = "/viewgenre.jsp";
			break;
		case "/addPublisher":
			publisherUtil.addPublisher(request);
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/updatePublisher":
			publisherUtil.editPublisher(request);
			forwardPath = "/viewpublisher.jsp";
			break;
		case "/addBranch":
			branchUtil.addBranch(request);
			forwardPath = "/viewbranch.jsp";
			break;
		case "/updatebranch":
			branchUtil.updateBranchAndCopies(request);
			forwardPath = "/viewbranch.jsp";
			break;
		case "/addBorrower":
			borrowerUtil.addBorrower(request);
			forwardPath = "/viewborrower.jsp";
			break;
		case "/updateBorrower":
			borrowerUtil.editBorrower(request);
			forwardPath = "/viewborrower.jsp";
			break;
		case "/addBook":
			int bookId=bookUtil.addBook(request);
			request.setAttribute("bookId", bookId);
			forwardPath = "/addbookcopies.jsp";
			break;
		case "/updateBook":
			bookUtil.editBook(request);
			forwardPath = "/viewbook.jsp";
			break;
		case "/updateBookCopies":
			branchUtil.updateBookCopies(request);
			forwardPath="/editbook.jsp";
			break;
		case "/updateBookLoan":
			bookLoanUtil.updateBookLoan(request);
			forwardPath="/viewbookloan.jsp";
			break;
		case "/addBookCopies":
			bookId = Integer.parseInt(request.getParameter("bookId"));
			branchUtil.addBookCopies(request);
			request.setAttribute("bookId", bookId);
			forwardPath="/viewbook.jsp";

		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);

	}

}

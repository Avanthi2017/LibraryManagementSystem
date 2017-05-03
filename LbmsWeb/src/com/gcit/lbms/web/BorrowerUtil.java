package com.gcit.lbms.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gcit.lbms.entity.BookCopies;
import com.gcit.lbms.entity.BookLoan;
import com.gcit.lbms.entity.Borrower;
import com.gcit.lbms.exception.IllegalNameException;
import com.gcit.lbms.service.BookLoanService;
import com.gcit.lbms.service.BookService;
import com.gcit.lbms.service.BorrowerService;
import com.gcit.lbms.service.BranchService;

public class BorrowerUtil {
	BorrowerService service = new BorrowerService();

	@SuppressWarnings("unused")
	public void editBorrower(HttpServletRequest request) {
		String message = "";
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		Integer borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
		Borrower borrower = new Borrower();
		try {
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);
			borrower.setCardNo(borrowerId);
			service.updateBorrower(borrower);
			message = "Borrower Updated sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	public void addBorrower(HttpServletRequest request) {
		String message = "";
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		Borrower borrower = new Borrower();
		try {
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);
			service.addBorrower(borrower);
			message = "Borrower added sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void deleteBorrower(HttpServletRequest request) {
		String message = "";
		Integer borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
		Borrower borrower = new Borrower();
		borrower.setCardNo(borrowerId);
		try {
			service.deleteBorrower(borrower);
			message = "Deleted Borrower sucessfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pageBorrowers(HttpServletRequest request) {

		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		try {
			request.setAttribute("borrowers", service.readAllBorrowers(pageNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String searchBorrowers(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		System.out.println("searchString" + searchString);
		StringBuffer strbuffer = new StringBuffer();
		try {
			// request.setAttribute("authors",service.readAuthorsByName(1,
			// searchString));
			List<Borrower> borrowers = service.readBorrowers(searchString);
			strbuffer.append(
					"<thead><tr><th>#</th><th>Borrower Name</th><th>Borrower Address</th><th>Borrower Phone</th><th>Books </th><th>Edit</th><th>Delete</th></tr></thead><tbody>");

			for (Borrower a : borrowers) {
				strbuffer.append("<tr><td>" + (borrowers.indexOf(a) + 1) + "</td><td>" + a.getName() + "</td><td>"
						+ a.getAddress() + "</td><td>" + a.getPhone() + "</td><td>");
				if (a.getBookloans() != null && !a.getBookloans().isEmpty()) {
					BookService bookService = new BookService();
					BranchService branchService = new BranchService();
					List<BookLoan> bookLoans = a.getBookloans();
					for (BookLoan b : bookLoans) {
						if (b.getBookId() != 0) {
							strbuffer.append("Book:" + bookService.readBookByPk(b.getBookId()).getTitle() + "Branch:"
									+ branchService.readBranchById(b.getBranchId()).getBranchName() + "DateOut:"
									+ b.getDateOut() + "DateIn:" + b.getDateIn() + "DueDate:" + b.getDueDate() + ",");
						}

					}

				}

				strbuffer.append("</td><td><button type='button' class='btn btn-primary' data-toggle='modal' "
						+ "data-target='#editBorrowerModal' href='editborrower.jsp?borrowerId=" + a.getCardNo()
						+ "'>update</button></td>");
				strbuffer.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' "
						+ "data-target='#deleteBorrowerModal' href='deleteborrower.jsp?borrowerId=" + a.getCardNo()
						+ "'>delete</button></td></tr>");

			}
			strbuffer.append("</tbody>");
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
		return strbuffer.toString();
	}

	public Boolean isBorrowerValid(HttpServletRequest request) {
		Borrower borrower=null;
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		try {
			borrower=service.readBorrowerByCardNo(cardNo);
		} catch ( IllegalNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(borrower!=null){
			return true;
		}
		return false;
	}

	public String checkOutBook(HttpServletRequest request) {
		String message="";
		BranchService branchService= new BranchService();
		BookLoanService bookLoanService = new BookLoanService();
		Integer borrowerId = Integer.parseInt(request.getParameter("cardNo"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		
		BookLoan bookloan= new BookLoan();
		bookloan.setBookId(bookId);
		bookloan.setBranchId(branchId);
		bookloan.setCardNo(borrowerId);
		int copies = 0;
		try {
			copies = branchService.readBookCopiesByBookIdAndBranchId(bookId, branchId);
			if(copies<=0){
				System.out.println("No book to checkout");
				return null;
			}
		} catch (SQLException | IllegalNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		BookCopies bookcopies=new BookCopies();
		bookcopies.setBookId(bookId);
		bookcopies.setBranchId(branchId);
		bookcopies.setNoOfCopies(copies-1);
		try {
		List<BookLoan> bookLoans=bookLoanService.readActiveBookLoansbyCardNo(borrowerId) ;
			for(BookLoan bLoan:bookLoans){
				if(bLoan.equals(bookloan)){
					message="<div class='alert alert-danger alert-dismissible' role='alert'>"
							+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'> "
							+ "<span aria-hidden='true'>&times;</span></button><strong>Warning!</strong> "
							+ "Same Book Cannot be Checked Out</div>";
					return message;
				}
			}
			bookLoanService.addBookLoan(bookloan);
			branchService.updateBookCopies(bookcopies);
			message="<div class='alert alert-success alert-dismissible' role='alert'>"
					+ "<button type='button' class='close' data-dismiss='alert' aria-label='Close'> "
					+ "<span aria-hidden='true'>&times;</span></button>"
					+ "Succefully checked in the book </div>";
		} catch (SQLException | IllegalNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
return message;

		
	}

	public void checkInBook(HttpServletRequest request) {
		String message="";
		BranchService branchService= new BranchService();
		BookLoanService bookLoanService = new BookLoanService();
		
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo").trim());
		Integer branchId = Integer.parseInt(request.getParameter("branchId").trim());
		Integer bookId = Integer.parseInt(request.getParameter("bookId").trim());
		System.out.println("cardNo"+cardNo+"branchId"+branchId+" bookId"+bookId);
		
		BookLoan bookloan = new BookLoan();
		bookloan.setBookId(bookId);
		bookloan.setBranchId(branchId);
		bookloan.setCardNo(cardNo);
		int copies = 0;
		try {
			copies = branchService.readBookCopiesByBookIdAndBranchId(bookId, branchId);
		} catch (SQLException | IllegalNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BookCopies bookcopies= new BookCopies();
		bookcopies.setBookId(bookId);
		bookcopies.setBranchId(branchId);
		bookcopies.setNoOfCopies(copies+1);
		try {
			bookLoanService.updateBookLoan(bookloan);
			branchService.updateBookCopies(bookcopies);
			message="Succefully checked in the book";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

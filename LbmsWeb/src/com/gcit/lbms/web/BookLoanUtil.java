package com.gcit.lbms.web;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.gcit.lbms.entity.BookLoan;
import com.gcit.lbms.exception.IllegalNameException;
import com.gcit.lbms.service.BookLoanService;

@WebServlet("/BookLoanServlet")
public class BookLoanUtil  {
	BookLoanService bookLoanService= new BookLoanService();
	public void updateBookLoan(HttpServletRequest request) {
	
	String message="";
		
		Integer noOfdays = Integer.parseInt(request.getParameter("noOfdays"));
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Integer borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
		String dueDate = null ;
		BookLoan bookloan = new BookLoan();
		try {
			bookloan=bookLoanService.readBookLoansbyIds(borrowerId, branchId, bookId);
		 dueDate = bookloan.getDueDate();
		} catch (IllegalNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dueDate, formatter);
	if(noOfdays<0|noOfdays>100){
		message="cannot override book";
		return;
	}
		dateTime = dateTime.plusDays(noOfdays);
		bookloan.setDueDate(dateTime.format(formatter));
		try {
			bookLoanService.overWriteDueDate(bookloan);
			System.out.println("Succefully ovrrided the due date:");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


		
		
	}

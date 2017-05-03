package com.gcit.lbms.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gcit.lbms.entity.Book;
import com.gcit.lbms.entity.BookCopies;
import com.gcit.lbms.entity.Borrower;
import com.gcit.lbms.entity.Branch;
import com.gcit.lbms.exception.IllegalNameException;
import com.gcit.lbms.service.BranchService;

public class BranchUtil {
	BranchService service = new BranchService();

	@SuppressWarnings("unused")
	public void updateBranch(HttpServletRequest request) {
		String message = "";
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Branch branch = new Branch();
		try {
			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			branch.setBranchId(branchId);
			service.updateBranch(branch);
			message = "Branch Updated sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	public void addBranch(HttpServletRequest request) {
		String message = "";
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		Branch branch = new Branch();
		try {
			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			service.addBranch(branch);
			message = "Branch added sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void deleteBranch(HttpServletRequest request) {
		String message = "";
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		try {
			service.deleteBranch(branch);
			message = "Deleted Branch sucessfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pageBranchs(HttpServletRequest request) {

		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		try {
			request.setAttribute("branchs", service.readAllBranches(pageNo));
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	public String searchBranchs(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		System.out.println("searchString" + searchString);
		StringBuffer strbuffer = new StringBuffer();
		try {
			
			List<Branch> branchs = service.readBranchs(searchString);
			strbuffer.append(
					"<thead><tr><th>#</th><th>Branch Name</th><th>Branch Address</th><th>Books</th><th>Borrower</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Branch a : branchs) {
				strbuffer.append("<tr><td>" + (branchs.indexOf(a) + 1) + "</td><td>" + a.getBranchName() + "</td><td>"+a.getBranchAddress()+"</td><td>");
				if (a.getBooks() != null && !a.getBooks().isEmpty()) {
					List<Book> books = a.getBooks();
					for (Book b : books) {
						strbuffer.append(b.getTitle() + ",");

					}
				}
				strbuffer.append("</td><td>");
				if (a.getBorrowers() != null && !a.getBorrowers().isEmpty()) {
					List<Borrower> borrowers = a.getBorrowers();
					for (Borrower b : borrowers) {
						strbuffer.append(b.getName() + ",");

					}
				}
				strbuffer.append("</td><td><button type='button' class='btn btn-primary' data-toggle='modal' "
						+ "data-target='#editBranchModal' href='editbranch.jsp?branchId=" + a.getBranchId()
						+ "'>update</button></td>");
				strbuffer.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' "
						+ "data-target='#deleteBranchModal' href='deletebranch.jsp?branchId=" + a.getBranchId()
						+ "'>delete</button></td></tr>");

			}

			strbuffer.append("</tbody>");
			System.out.println(strbuffer.toString());
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
		return strbuffer.toString();
	}

	public void updateBookCopies(HttpServletRequest request) {
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Map<String, String[]> tableData = request.getParameterMap();
		String[] noOfCopies = tableData.get("noOfCopies");
		String[] bookIds = tableData.get("bookId");
		
		for(int i=0;i<bookIds.length;i++){
			BookCopies bookcopies= new BookCopies();
			bookcopies.setBookId(Integer.parseInt(bookIds[i]));
			bookcopies.setBranchId(branchId);
			bookcopies.setNoOfCopies(Integer.parseInt(noOfCopies[i]));
		
			try {
				service.updateBookCopies(bookcopies);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		
		
	}
	public void updateBookCopies1(HttpServletRequest request) {
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));
		System.out.println("branchId"+branchId+"bookId "+bookId+"noOfCopies"+noOfCopies);
		
		BookCopies bookcopies= new BookCopies();
		bookcopies.setBookId(bookId);
		bookcopies.setBranchId(branchId);
		bookcopies.setNoOfCopies(noOfCopies);
		
		if(noOfCopies>=0){
			int noOfcopies=0;
			try {
				noOfcopies=service.readBookCopiesByBookIdAndBranchId(bookId, branchId);
				if(noOfcopies>0){
					service.updateBookCopies(bookcopies);
				}
				else{
					service.addBookCopies(bookcopies);
				}
				
				
			} catch (SQLException | IllegalNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public Boolean isLibrarianValid(HttpServletRequest request) {
	String message="";
		Branch branch=null;
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		try {
		 branch=service.readBranchById(branchId);
		} catch (SQLException | IllegalNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(branch!=null){
			return true;
			
		}
		return false;
	}

	public void addBookCopies(HttpServletRequest request) {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Map<String, String[]> tableData = request.getParameterMap();
		String[] noOfCopies = tableData.get("noOfCopies");
		String[] branchIds = tableData.get("branchId");
		for(int i=0;i<noOfCopies.length;i++){
			if(Integer.parseInt(noOfCopies[i])>0)  {
				
				BookCopies bookcopies= new BookCopies();
				bookcopies.setBookId(bookId);
				bookcopies.setBranchId(Integer.parseInt(branchIds[i]));
				bookcopies.setNoOfCopies(Integer.parseInt(noOfCopies[i]));
				try {
					service.addBookCopies(bookcopies);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

	public void updateBranchAndCopies(HttpServletRequest request) {
		updateBranch(request);
		updateBookCopies(request);
		
	}

}

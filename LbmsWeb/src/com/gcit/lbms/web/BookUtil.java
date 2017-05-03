package com.gcit.lbms.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gcit.lbms.entity.Author;
import com.gcit.lbms.entity.Book;
import com.gcit.lbms.entity.BookCopies;
import com.gcit.lbms.entity.BookLoan;
import com.gcit.lbms.entity.Branch;
import com.gcit.lbms.entity.Genre;
import com.gcit.lbms.entity.Publisher;
import com.gcit.lbms.exception.IllegalNameException;
import com.gcit.lbms.service.AuthorService;
import com.gcit.lbms.service.BookLoanService;
import com.gcit.lbms.service.BookService;
import com.gcit.lbms.service.BranchService;
import com.gcit.lbms.service.GenreService;

public class BookUtil {
	BookService service = new BookService();
	AuthorService authorService = new AuthorService();
	BranchService branchService = new BranchService();
	GenreService genreService = new GenreService();

	@SuppressWarnings("unused")
	public void editBook(HttpServletRequest request) {
		String message = "";
		String bookName = request.getParameter("bookName");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		try {
			genreService.deleteBookGenreByBookId(bookId);
			authorService.deleteBookAuthorsByBookId(bookId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Publisher pub = new Publisher();
		pub.setPublisherId(publisherId);

		if (request.getParameterValues("authorIds") != null) {
			String[] authorIds = request.getParameterValues("authorIds");
			System.out.println("authorIds" + authorIds);

			for (String id : authorIds) {
				try {
					authorService.addBookAuthours(bookId, Integer.parseInt(id));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (request.getParameterValues("genreIds") != null) {
			String[] genreIds = request.getParameterValues("genreIds");
			System.out.println("genreIds" + genreIds);
			for (String id : genreIds) {
				try {
					genreService.addBookGenre(Integer.parseInt(id), bookId);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		Map<String, String[]> tableData = request.getParameterMap();
		String[] noOfCopies = tableData.get("noOfCopies");
		String[] branchIds = tableData.get("branchId");
		for (int i = 0; i < noOfCopies.length; i++) {
			if (Integer.parseInt(noOfCopies[i]) > 0) {
			}
			BookCopies bookcopies = new BookCopies();
			bookcopies.setBookId(bookId);
			bookcopies.setBranchId(Integer.parseInt(branchIds[i]));
			bookcopies.setNoOfCopies(Integer.parseInt(noOfCopies[i]));
			try {
				branchService.addBookCopies(bookcopies);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			Book book = new Book();
			book.setBookId(bookId);
			book.setPublisher(pub);
			book.setTitle(bookName);
			service.udpateBook(book);
			message = "Book Updated sucessfully";
		} catch (IllegalNameException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int addBook(HttpServletRequest request) {
		String message = "";
		String bookName = request.getParameter("bookName");
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		int bookId = 0;
		Publisher pub = new Publisher();
		pub.setPublisherId(publisherId);
		try {
			Book book = new Book();
			book.setTitle(bookName);
			book.setPublisher(pub);
			bookId = service.addBookWithID(book);

			message = "Book Updated sucessfully";
		} catch (IllegalNameException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (request.getParameterValues("authorIds") != null) {
			String[] authorIds = request.getParameterValues("authorIds");
			for (String id : authorIds) {
				try {
					authorService.addBookAuthours(bookId, Integer.parseInt(id));
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		if (request.getParameterValues("genreIds") != null) {
			String[] genreIds = request.getParameterValues("genreIds");
			for (String id : genreIds) {
				try {
					genreService.addBookGenre(Integer.parseInt(id), bookId);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bookId;
	}

	@SuppressWarnings("unused")
	public void deleteBook(HttpServletRequest request) {
		String message="";
		BookLoanService bookLoanService = new BookLoanService();
		BranchService branchService = new BranchService();
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = new Book();
		book.setBookId(bookId);
		List<BookLoan> bookLoans;
		try {
			
			bookLoans = bookLoanService.readActiveBookLoansbyBookId(bookId);
			if (bookLoans.isEmpty() || bookLoans == null) {
				service.deleteBook(bookId);
				message = "Deleted Book sucessfully";
			} else {
				message = "Cannot delete the  book!! checkin book and retry";
			}
		} catch (IllegalNameException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void pageBooks(HttpServletRequest request) {

		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		try {
			request.setAttribute("books", service.readAllBooks(pageNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String searchBooks(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		StringBuffer strbuffer = new StringBuffer();
		try {
			// request.setAttribute("authors",service.readAuthorsByName(1,
			// searchString));
			List<Book> books = service.readBookbyTitle(searchString);
			strbuffer.append("<thead><tr><th>#</th><th>Book Name</th><th>Publisher</th><th>Genre</th>"
					+ "<th>Author</th><th>Branch</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Book a : books) {
				strbuffer.append("<tr><td>" + (books.indexOf(a) + 1) + "</td><td>"+a.getTitle()+"</td>");
				strbuffer.append("<td>" + a.getPublisher().getPublisherName() + "</td><td>");
				if (a.getGenres() != null && !a.getGenres().isEmpty()) {
					List<Genre> genres = a.getGenres();
					for (Genre g : genres) {
						strbuffer.append(g.getGenreName() + ",");
					}
				}
				strbuffer.append("</td><td>");
				if (a.getAuthors() != null && !a.getAuthors().isEmpty()) {
					List<Author> authors = a.getAuthors();
					for (Author author : authors) {
						strbuffer.append(author.getAuthorName() + ",");
					}
				}
				strbuffer.append("</td><td>");
				BranchService branchService = new BranchService();
				if (a.getBranchs() != null && !a.getBranchs().isEmpty()) {
					List<Branch> branchs = a.getBranchs();
					for (Branch branch : branchs) {
						int bookCopies = branchService.readBookCopiesByBookIdAndBranchId(a.getBookId(), branch.getBranchId());
						if (bookCopies > 0) {
							strbuffer.append("Branch:" + branch.getBranchName() + "NoOfCopies:" + bookCopies);
						}

					}
				}
				
				strbuffer.append("	</td><td><a href='editbook.jsp?bookId="+a.getBookId()+"' class='btn btn-info' role='button'>update</a></td>");
strbuffer.append("<td><button type='button' class='btn btn-sm btn-danger'data-toggle='modal' "
		+ "data-target='#deleteBookModal'href='deletebook.jsp?bookId="+a.getBookId()+"'>delete</button></td></tr>");
			}
			strbuffer.append("</tbody></table>");

		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
		return strbuffer.toString();
	}

}

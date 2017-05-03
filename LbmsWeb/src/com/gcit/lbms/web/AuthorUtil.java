package com.gcit.lbms.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gcit.lbms.entity.Author;
import com.gcit.lbms.entity.Book;
import com.gcit.lbms.exception.IllegalNameException;
import com.gcit.lbms.service.AuthorService;

public class AuthorUtil {
	AuthorService service = new AuthorService();

	@SuppressWarnings("unused")
	public void editAuthor(HttpServletRequest request) {
		String message = "";
		String authorName = request.getParameter("authorName");
		if (authorName == null | authorName.length() > 46) {
			return;
		}
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		Author author = new Author();
		try {
			author.setAuthorName(authorName);
			author.setAuthorId(authorId);
			service.udpateAuthor(author);
			message = "Author Updated sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	public void addAuthor(HttpServletRequest request) {
		String message = "";
		String authorName = request.getParameter("authorName");
		if (authorName == null) {
			return;
		} else if (authorName.length() < 3) {
			return;
		}
		Author author = new Author();
		try {
			System.out.println("authorName" + authorName);
			author.setAuthorName(authorName);
			service.addAuthor(author);
			message = "Author added sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void deleteAuthor(HttpServletRequest request) {
		String message = "";
		Integer authorId = Integer.parseInt(request.getParameter("authorId"));
		Author author = new Author();
		author.setAuthorId(authorId);
		try {
			service.deleteAuthor(author);
			message = "Deleted Author sucessfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Delete Author Failure";
		}
	}

	public void pageAuthors(HttpServletRequest request) {

		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		try {
			request.setAttribute("authors", service.readAllAuthors(pageNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String searchAuthors(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		System.out.println("searchString" + searchString);
		StringBuffer strbuffer = new StringBuffer();
		try {
			// request.setAttribute("authors",service.readAuthorsByName(1,
			// searchString));
			List<Author> authors = service.readAuthorsByName(1, searchString);
			strbuffer.append(
					"<thead><tr><th>#</th><th>Author Name</th><th>Books Written</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Author a : authors) {
				strbuffer.append("<tr><td>"
						+ (authors.indexOf(a) + 1 )+ "</td><td>" + a.getAuthorName() + "</td><td>");
				if (a.getBooks() != null && !a.getBooks().isEmpty()) {
					List<Book> books = a.getBooks();
					for (Book b : books) {
						strbuffer.append(b.getTitle() + ",");
						
					}
				}
				strbuffer.append("</td><td><button type='button' class='btn btn-primary' data-toggle='modal' "
						+ "data-target='#editAuthourModal' href='editauthor.jsp?authorId=" + a.getAuthorId()
						+ "'>update</button></td>");
				strbuffer.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' "
						+ "data-target='#deleteAuthourModal' href='deleteauthor.jsp?authorId=" + a.getAuthorId()
						+ "'>delete</button></td></tr>");

			}

			strbuffer.append("</tbody>");
System.out.println(strbuffer.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strbuffer.toString();
	}

}

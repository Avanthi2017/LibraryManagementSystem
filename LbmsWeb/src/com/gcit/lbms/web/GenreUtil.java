package com.gcit.lbms.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gcit.lbms.entity.Author;
import com.gcit.lbms.entity.Book;
import com.gcit.lbms.entity.Genre;
import com.gcit.lbms.exception.IllegalNameException;
import com.gcit.lbms.service.GenreService;

public class GenreUtil {
	GenreService service = new GenreService();

	@SuppressWarnings("unused")
	public void editGenre(HttpServletRequest request) {
		String message = "";
		String genreName = request.getParameter("genreName");
		if (genreName == null | genreName.length() > 46) {
			return;
		}
		Integer genreId = Integer.parseInt(request.getParameter("genreId"));
		Genre genre = new Genre();
		try {
			genre.setGenre_name(genreName);
			genre.setGenreId(genreId);
			service.updateGenre(genre);
			message = "Genre Updated sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	public void addGenre(HttpServletRequest request) {
		String message = "";
		String genreName = request.getParameter("genreName");
		if (genreName == null) {
			return;
		} else if (genreName.length() < 3) {
			return;
		}
		Genre genre = new Genre();
		try {
			genre.setGenre_name(genreName);
			service.addgenre(genre);
			message = "Genre added sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void deleteGenre(HttpServletRequest request) {
		String message = "";
		Integer genreId = Integer.parseInt(request.getParameter("genreId"));
		Genre genre = new Genre();
		genre.setGenreId(genreId);
		try {
			service.deleteGenre(genre);
			message = "Deleted Genre sucessfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pageGenres(HttpServletRequest request) {

		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		try {
			request.setAttribute("genres", service.readAllGenres(pageNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String searchGenres(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		System.out.println("searchString" + searchString);
		StringBuffer strbuffer = new StringBuffer();
		try {
			// request.setAttribute("authors",service.readAuthorsByName(1,
			// searchString));
			List<Genre> genres = service.readGenreByName(searchString);
			strbuffer.append(
					"<thead><tr><th>#</th><th>Genre Name</th><th>Books </th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Genre a : genres) {
				strbuffer.append("<tr><td>" + (genres.indexOf(a) + 1) + "</td><td>" + a.getGenreName() + "</td><td>");
				if (a.getBooks() != null && !a.getBooks().isEmpty()) {
					List<Book> books = a.getBooks();
					for (Book b : books) {
						strbuffer.append(b.getTitle() + ",");

					}
				}
				strbuffer.append("</td><td><button type='button' class='btn btn-primary' data-toggle='modal' "
						+ "data-target='#editGenreModal' href='editgenre.jsp?genreId=" + a.getGenreId()
						+ "'>update</button></td>");
				strbuffer.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' "
						+ "data-target='#deleteGenreModal' href='deletegenre.jsp?genreId=" + a.getGenreId()
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

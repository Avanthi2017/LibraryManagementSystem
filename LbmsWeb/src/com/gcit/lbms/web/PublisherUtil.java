package com.gcit.lbms.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gcit.lbms.entity.Author;
import com.gcit.lbms.entity.Book;
import com.gcit.lbms.entity.Publisher;
import com.gcit.lbms.exception.IllegalNameException;
import com.gcit.lbms.service.PublisherService;

public class PublisherUtil {
	PublisherService service = new PublisherService();

	@SuppressWarnings("unused")
	public void editPublisher(HttpServletRequest request) {
		String message = "";
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Publisher publisher = new Publisher();
		try {
			publisher.setPublisherName(publisherName);
			publisher.setPublisherAddress(publisherAddress);
			publisher.setPublisherPhone(publisherPhone);
			publisher.setPublisherId(publisherId);
			service.updatePublisher(publisher);
			message = "Publisher Updated sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	public void addPublisher(HttpServletRequest request) {
		String message = "";
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		Publisher publisher = new Publisher();
		try {
			publisher.setPublisherName(publisherName);
			publisher.setPublisherAddress(publisherAddress);
			publisher.setPublisherPhone(publisherPhone);
			service.addPublisher(publisher);
			message = "Publisher added sucessfully";
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void deletePublisher(HttpServletRequest request) {
		String message = "";
		Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		try {
			service.deletePublisher(publisher);
			message = "Deleted Publisher sucessfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pagePublishers(HttpServletRequest request) {

		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		try {
			request.setAttribute("publishers", service.readAllPublishers(pageNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String searchPublishers(HttpServletRequest request) {

		String searchString = request.getParameter("searchString");
		System.out.println("searchString" + searchString);
		StringBuffer strbuffer = new StringBuffer();
		try {
			// request.setAttribute("authors",service.readAuthorsByName(1,
			// searchString));
			List<Publisher> publishers = service.readPublishers(searchString);
			strbuffer.append(
					"<thead><tr><th>#</th><th>Publisher Name</th><th>Publisher Address</th><th>Publisher Phone</th><th>Books </th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Publisher a : publishers) {
				strbuffer.append("<tr><td>" + (publishers.indexOf(a) + 1) + "</td><td>" + a.getPublisherName() + "</td><td>"+a.getPublisherAddress()+"</td><td>"+a.getPublisherPhone()+"</td><td>");
				if (a.getBooks() != null && !a.getBooks().isEmpty()) {
					List<Book> books = a.getBooks();
					for (Book b : books) {
						strbuffer.append(b.getTitle() + ",");

					}
				}
				strbuffer.append("</td><td><button type='button' class='btn btn-primary' data-toggle='modal' "
						+ "data-target='#editPublisherModal' href='editpublisher.jsp?publisherId=" + a.getPublisherId()
						+ "'>update</button></td>");
				strbuffer.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' "
						+ "data-target='#deletePublisherModal' href='deletepublisher.jsp?publisherId=" + a.getPublisherId()
						+ "'>delete</button></td></tr>");

			}

			strbuffer.append("</tbody>");
			System.out.println(strbuffer.toString());
		} catch (SQLException | IllegalNameException e) {
			e.printStackTrace();
		}
		return strbuffer.toString();
	}

}

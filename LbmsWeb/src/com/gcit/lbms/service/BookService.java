package com.gcit.lbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lbms.dao.BookDAO;
import com.gcit.lbms.dao.ConnectionUtil;
import com.gcit.lbms.entity.Book;
import com.gcit.lbms.exception.IllegalNameException;

public class BookService {
	static ConnectionUtil util = new ConnectionUtil();
	
	public Integer getBookCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.getBooksCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public Book readBookByPk(Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO adao = new BookDAO(conn);
			return adao.readBookById(bookId);
		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public List<Book> readAllBooks(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks(pageNo);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Book> readBookbyTitle(String name) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooksByName(name);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public int addBookWithID(Book book) throws SQLException {
		int bookId = 0;
		Connection conn = null;

		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bookId = bdao.addBookWithID(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return bookId;
	}

	public void udpateBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO adao = new BookDAO(conn);
			adao.updateBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	

	public void deleteBook(int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(bookId);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

}

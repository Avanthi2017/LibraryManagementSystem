package com.gcit.lbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lbms.dao.AuthorDAO;
import com.gcit.lbms.dao.BookAuthorDAO;
import com.gcit.lbms.dao.BookDAO;
import com.gcit.lbms.dao.ConnectionUtil;
import com.gcit.lbms.entity.Author;
import com.gcit.lbms.exception.IllegalNameException;

public class AuthorService {
	static  ConnectionUtil util = new ConnectionUtil();
	public Author readAuthorById(Integer authorId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorById(authorId);
		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Author> readAllAuthors(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthors(pageNo);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Author> readAllAuthors() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthors();

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Author> readAuthorsByName(Integer pageNo,String authorName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAllAuthorsByName(authorName);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public Integer getAuthoursCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorsCount();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		
			return null;
			
		
	}
	
	public void addAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
				adao.addAuthor(author);
				conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	public void udpateAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	
	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	public void addBookAuthours(int bookId,int authorId) throws SQLException{
		Connection conn = null;
		try {
			conn=util.getConnection();
			BookAuthorDAO bdao= new BookAuthorDAO(conn);
			bdao.addBookAuthors(bookId,authorId);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	public void deleteBookAuthour(int authorId,int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn=util.getConnection();
			BookAuthorDAO bdao= new BookAuthorDAO(conn);
			bdao.deleteBookAuthors(authorId, bookId);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	public void deleteBookAuthorsByBookId(int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn=util.getConnection();
			BookAuthorDAO bdao= new BookAuthorDAO(conn);
			bdao.deleteBookAuthorsByBookId(bookId);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				conn.close();
			}
		}
		
	}
	public List<Integer> readBookAuthorByBookId(int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookAuthorDAO bdao= new BookAuthorDAO(conn);
			return bdao.readBookAuthorsbybookId(bookId);
		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				conn.close();
			}
	}
		return null;
		
	}
}
	

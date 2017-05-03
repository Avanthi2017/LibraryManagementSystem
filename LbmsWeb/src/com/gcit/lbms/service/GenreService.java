package com.gcit.lbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lbms.dao.AuthorDAO;
import com.gcit.lbms.dao.BookDAO;
import com.gcit.lbms.dao.BookGenreDAO;
import com.gcit.lbms.dao.ConnectionUtil;
import com.gcit.lbms.dao.GenreDAO;
import com.gcit.lbms.entity.Genre;
import com.gcit.lbms.exception.IllegalNameException;

public class GenreService {
	static  ConnectionUtil util = new ConnectionUtil();
	public Genre readGenreById(Integer genreId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenreById(genreId);
		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Genre> readGenreByName(String genreName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readBooksByGenreName(genreName);
		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Genre> readAllGenres(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAllGenres(pageNo);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Genre> readAllGenres() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAllGenres();

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public void addgenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.addGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void updateGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.updateGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void deleteGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.deleteGenre(genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	public Integer getGenreCount(){
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.getGenreCount();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
		
	}
	public void addBookGenre(int genre_id,int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn=util.getConnection();
			BookGenreDAO bdao = new BookGenreDAO(conn);
			bdao.addBookGenre(genre_id, bookId);
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
	public void deleteBookGenre(int genre_id,int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn=util.getConnection();
			BookGenreDAO bdao = new BookGenreDAO(conn);
		bdao.deleteBookGenre(genre_id, bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn != null)
				conn.close();
		}
		
	}
	public void deleteBookGenreByBookId(int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn=util.getConnection();
			BookGenreDAO bdao = new BookGenreDAO(conn);
		bdao.deleteBookGenreByBookId(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn != null)
				conn.close();
		}
		
	}
	public List<Integer> readBookGenreByBookId(int bookId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookGenreDAO bdao = new BookGenreDAO(conn);
			return bdao.readBookGenrebybookId(bookId);
		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
}

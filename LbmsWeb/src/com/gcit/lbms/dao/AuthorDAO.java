package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.Author;
import com.gcit.lbms.exception.IllegalNameException;


public class AuthorDAO extends BaseDAO {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public Integer addAuthor(Author author) throws SQLException, ClassNotFoundException {
		Integer authorid=saveWithID("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
		return authorid;
	}

	public void updateAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("update tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}

	public void deleteAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("delete from tbl_author where authorId = ?", new Object[] {author.getAuthorId()});
	}
	

	public List<Author> readAllAuthors(Integer pageNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		setPageNo(pageNo);
		return readAll("select * from tbl_author", null);
	}
	public List<Author> readAllAuthors() throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_author", null);
	}
	public Integer getAuthorsCount() throws ClassNotFoundException, SQLException{
		return getCount("tbl_author");
	}

	
	public List<Author> readAllAuthorsByName(String authorName) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_author where authorName like ?", new Object[]{"%"+authorName+"%"});
	}
	
	public Author readAuthorById(Integer authorId) throws ClassNotFoundException, SQLException, IllegalNameException{
		List<Author> authors = readAll("select * from tbl_author where authorId = ?", new Object[]{authorId});
		if(authors!=null && !authors.isEmpty()){
			return authors.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException{
		List<Author> authors = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(bdao.readAllFirstLevel("select * from tbl_book where bookId IN(select bookId from tbl_book_authors where authorId = ?)", new Object[]{rs.getInt("authorId")}));
			authors.add(a);
		}
		return authors;
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException{
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}

}

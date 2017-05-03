package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.exception.IllegalNameException;

public class BookAuthorDAO extends  BaseDAO{
	
	public BookAuthorDAO(Connection conn) {
		super(conn);
	}
	public void addBookAuthors(Integer bookId, Integer authorId ) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book_authors values (? , ?)", new Object[]{bookId, authorId});
	}
	public void deleteBookAuthors(Integer authorId, Integer bookId) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book_authors where authorId=? and bookId=? ", new Object[]{authorId,bookId});
	}
	public void deleteBookAuthorsByBookId(Integer bookId) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book_authors where  bookId=? ", new Object[]{bookId});
	}
	public List<Integer> readbyAuthorId(Integer authorId)throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAllFirstLevel("select bookId from tbl_book_authors where authorId=?",new Object[]{authorId});
		
	}
	public List<Integer> readBookAuthorsbybookId(Integer bookId)throws ClassNotFoundException, SQLException, IllegalNameException{
	return readAllFirstLevel("select authorId from tbl_book_authors where bookId=?",new Object[]{bookId});
		
	
	}
	


	@Override
	public List<Integer> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Integer>id= new ArrayList<>();
		int numberOfColumns = rs.getMetaData().getColumnCount();
		String name = null;
		// get the column names; column indexes start from 1
		for (int i = 1; i < numberOfColumns + 1; i++) {
		    String columnName = rs.getMetaData().getColumnName(i);
		    // Get the name of the column's table name
		    if ("bookId".equals(columnName)) {
		        name="bookId";
		    }
		    else if("authorId".equals(columnName)){
		    	name="authorId";
		    }
		}
		if(name!=null){
		while(rs.next()){
			id.add(rs.getInt(name));
		}
		}
		// TODO Auto-generated method stub
		return id;
	}


	@Override
	public  List<Integer> extractDataFirstLevel(ResultSet rs)
			throws SQLException, ClassNotFoundException, IllegalNameException {
		// TODO Auto-generated method stub
		return extractData(rs);
	}
}

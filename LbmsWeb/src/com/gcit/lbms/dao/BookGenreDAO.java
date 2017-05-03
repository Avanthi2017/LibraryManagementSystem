package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.exception.IllegalNameException;

//void add(book,genre)
public class BookGenreDAO extends BaseDAO{

	public BookGenreDAO(Connection con) {
		super(con);
	}

	public void addBookGenre(Integer genre_id, Integer bookId ) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book_genres values (? , ?)", new Object[]{genre_id, bookId});
	}
	public void deleteBookGenre(Integer genre_id, Integer bookId ) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book_genres where genre_id= ? and bookId=?", new Object[]{genre_id, bookId});
	}
	public void deleteBookGenreByBookId( Integer bookId ) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book_genres where bookId=?", new Object[]{bookId});
	}
	public List<Integer> readbygenreId(Integer genre_id)throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAllFirstLevel("select bookId from tbl_book_genres where genre_id=?",new Object[]{genre_id});
		
	}
	public List<Integer> readBookGenrebybookId(Integer bookId)throws ClassNotFoundException, SQLException, IllegalNameException{
	return readAllFirstLevel("select genre_id from tbl_book_genres where bookId=?",new Object[]{bookId});
		
	}
	@SuppressWarnings("unchecked")
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
		    else if("genre_id".equals(columnName)){
		    	name="genre_id";
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
	public List<Integer> extractDataFirstLevel(ResultSet rs)
			throws SQLException, ClassNotFoundException, IllegalNameException {
		// TODO Auto-generated method stub
		return extractData( rs);
	}
}

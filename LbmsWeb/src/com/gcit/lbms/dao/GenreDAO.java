package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.Genre;
import com.gcit.lbms.exception.IllegalNameException;

public  class GenreDAO extends BaseDAO {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("insert into tbl_genre  (genre_name) values (?)", new Object[]{genre.getGenreName()});
	}
	
	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("update tbl_genre set genre_name = ? where genre_id=?", new Object[]{genre.getGenreName(),genre.getGenreId()});
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("delete from tbl_genre where genre_id= ? ", new Object[]{genre.getGenreId()});
	}
	
	public Genre readGenreById(Integer genreId) throws ClassNotFoundException, SQLException, IllegalNameException{
		List<Genre> genre = readAll("select * from tbl_genre where genre_id = ?", new Object[]{genreId});
		if(genre!=null && !genre.isEmpty()){
			return genre.get(0);
		}
		return null;
	}
	
	public List<Genre> readBooksByGenreName(String genre_name) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_genre where genre_name like ?", new Object[]{"%"+genre_name+"%"});
	}
	public List<Genre> readAllGenres(Integer pageNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		setPageNo(pageNo);
		return readAll("select * from tbl_genre", null);
	}
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_genre", null);
	}
	public Integer getGenreCount() throws ClassNotFoundException, SQLException{
		return getCount("tbl_genre");
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		BookDAO bdao = new BookDAO(conn);
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenre_name(rs.getString("genre_name"));
			g.setGenreId(rs.getInt("genre_id"));
	
			if(rs.getInt("genre_id") > 0){
		     g.setBooks(bdao.readAllFirstLevel("select * from tbl_book where bookId IN(select bookId from tbl_book_genres where genre_id=? )", new Object[]{rs.getInt("genre_id")}));
			}
			
			genres.add(g);
		}
		return genres;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenre_name(rs.getString("genre_name"));
			g.setGenreId(rs.getInt("genre_id"));
			genres.add(g);
		}
		return genres;
	}

}

package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.Book;
import com.gcit.lbms.exception.IllegalNameException;

public class BookDAO extends BaseDAO{
	
	public BookDAO(Connection conn) {
		super(conn);
	}

	public Integer addBookWithID(Book book) throws ClassNotFoundException, SQLException{
		return saveWithID("insert into tbl_book (title, pubId) values (? , ?)", new Object[]{book.getTitle(), book.getPublisher().getPublisherId()});
	}
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException{
		save("update tbl_book set title = ?, pubId = ? where bookId = ?", new Object[]{book.getTitle(),book.getPublisher().getPublisherId(), book.getBookId()});
	}
	public Book readBookById(Integer bookId) throws ClassNotFoundException, SQLException, IllegalNameException{
		List<Book> books = readAll("select * from tbl_book where bookId = ?", new Object[]{bookId});
		if(books!=null && !books.isEmpty()){
			return books.get(0);
		}
		return null;
	}
	public List<Book> readAllBooks(Integer pageNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		setPageNo(pageNo);
		return readAll("select * from tbl_book", null);
	}
	public Integer getBooksCount() throws ClassNotFoundException, SQLException{
		return getCount("tbl_book");
	}
	public List<Book> readAllBooksByName(String title) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_book where title like ?", new Object[]{title+"%"});
	}
	
	public void deleteBook(int bookId) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book where bookId= ? ", new Object[]{bookId});
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		PublisherDAO pdao = new PublisherDAO(conn);
		List<Book> books = new ArrayList<>();
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao= new GenreDAO(conn);
		BranchDAO bdao= new BranchDAO(conn);
		while(rs.next()){
			Book b = new Book();
			b.setTitle(rs.getString("title"));
			b.setBookId(rs.getInt("bookId"));
			System.out.println(rs.getString("title")+rs.getInt("pubId"));
			if(rs.getInt("pubId") > 0){
				b.setPublisher(pdao.readPublisherById(rs.getInt("pubId")));
				//b.setPublisher((Publisher) pdao.readAllFirstLevel("select * from tbl_publisher where  publisherId=?", new Object[]{rs.getInt("pubId")}));
			}
			b.setAuthors(adao.readAllFirstLevel("select * from tbl_author where authorId IN(select authorId from tbl_book_authors where bookId = ?)", new Object[]{rs.getInt("bookId")}));
			//b.setAuthors(adao.readAuthorByPk(authorId));
			//poopulate genres
			
			b.setGenres(gdao.readAllFirstLevel("select * from tbl_genre where genre_id IN(select genre_id from tbl_book_genres where bookId = ?)", new Object[]{rs.getInt("bookId")}));
			b.setBranchs(bdao.readAllFirstLevel("select * from tbl_library_branch where branchId IN(select branchId from tbl_book_copies where bookId=?)" ,new Object[]{rs.getInt("bookId")}));
			books.add(b);
		}
		return books;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book b = new Book();
			if(rs.getString("title")!=null){
			b.setTitle(rs.getString("title"));
			}
			b.setBookId(rs.getInt("bookId"));
			books.add(b);
		}
		return books;
	}

}

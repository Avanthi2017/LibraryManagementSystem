package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.BookCopies;
import com.gcit.lbms.exception.IllegalNameException;

public class BookCopiesDAO extends BaseDAO {

	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBookCopies(BookCopies bookcopies) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies)values(?,?,? )",
				new Object[] { bookcopies.getBookId(), bookcopies.getBranchId(), bookcopies.getNoOfCopies() });
	}

	public void updateBookCopies(BookCopies bookcopies) throws SQLException, ClassNotFoundException {
		save("update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?",
				new Object[] { bookcopies.getNoOfCopies(), bookcopies.getBookId(), bookcopies.getBranchId() });
	}

	public void deleteBookCopies(BookCopies bookcopies) throws SQLException, ClassNotFoundException {
		save("delete * from tbl_book_copies where bookId=? and branchId=?",
				new Object[] { bookcopies.getBookId(),bookcopies.getBranchId() });
	}

	public List<BookCopies> readAllBookCopies() throws SQLException, ClassNotFoundException, IllegalNameException {
		return readAll("select * from tbl_book_copies", null);
	}

	public int readBookCopiesByBranchIdAndBookId(int bookId, int branchId) throws SQLException, ClassNotFoundException, IllegalNameException {
		List<BookCopies> bookCopies2 = readAll("select  * from tbl_book_copies where bookId=? and branchId=?",new Object[] { bookId, branchId});
		if (bookCopies2 != null && !bookCopies2.isEmpty()) {

			return bookCopies2.get(0).getNoOfCopies();

		}
		return 0;
	}

	public List<BookCopies> readAllBookCopiesByBranchId(int branchId)
			throws SQLException, ClassNotFoundException, IllegalNameException {
		return readAll("select  * from tbl_book_copies where branchId=?", new Object[] { branchId });
	}

	public List<BookCopies> readAllBookCopiesByBookId(int bookId)
			throws SQLException, ClassNotFoundException, IllegalNameException {
		return readAll("select  * from tbl_book_copies where bookId=?", new Object[] { bookId });
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<BookCopies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		return extractDataFirstLevel(rs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookCopies> bookcopies = new ArrayList<>();
		while(rs.next()){
			BookCopies bc = new BookCopies();
			bc.setBookId(rs.getInt("bookId"));
			bc.setBranchId(rs.getInt("branchId"));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));
			bookcopies.add(bc);
		}
		return bookcopies;
	}

}

package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.Book;
import com.gcit.lbms.entity.BookLoan;
import com.gcit.lbms.entity.Borrower;
import com.gcit.lbms.entity.Branch;
import com.gcit.lbms.exception.IllegalNameException;

public class BookLoansDAO extends BaseDAO {
	LocalDateTime date;
	DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
	public BookLoansDAO(Connection conn) {
		super(conn);

	}

	public void bookCheckOut(BookLoan bookloans) throws SQLException, ClassNotFoundException {
		save("insert into  tbl_book_loans (tbl_book_loans.bookId,tbl_book_loans.branchId,tbl_book_loans.cardNo,tbl_book_loans.dateOut,tbl_book_loans.dueDate)values(?,?,?,?,?)",
				new Object[] { bookloans.getBookId(), bookloans.getBranchId(), bookloans.getCardNo(),
						bookloans.getDateOut(), bookloans.getDueDate()});
	}

	public void bookCheckIn(BookLoan bookloans) throws SQLException, ClassNotFoundException {
		save("update  tbl_book_loans set tbl_book_loans.dateIn=? "
				+ " where( (tbl_book_loans.bookId=?) and(tbl_book_loans.branchId=?)and(tbl_book_loans.cardNo=?))",
				new Object[] { bookloans.getDateIn(),bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo()});
	}
	public void overrideDueDate(BookLoan bookloans) throws SQLException, ClassNotFoundException {
		save("update  tbl_book_loans set tbl_book_loans.dueDate=? "
				+ " where( (tbl_book_loans.bookId=?) and(tbl_book_loans.branchId=?)and(tbl_book_loans.cardNo=?))",
				new Object[] { bookloans.getDueDate(), bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo()});
	}
	public List<BookLoan> readLoansByCardNo(Integer cardNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_book_loans where cardNo = ?", new Object[]{cardNo});
		
		
	}
	public List<BookLoan> readActiveBookLoansbyCardNo(Integer cardNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_book_loans where cardNo = ? and dateIn is null", new Object[]{cardNo});
		
	}
	public List<BookLoan> readActiveBookLoansbyBookId(Integer bookId) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_book_loans where bookId = ? and dateIn is null", new Object[]{bookId});
		
	}
	public List<BookLoan> readLoansByCardNoAndBranchId(Integer cardNo,Integer branchId) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_book_loans where cardNo=? and branchId=? and dateIn is null", new Object[]{cardNo,branchId});
		
	}
	public BookLoan readBookLoanByIds(Integer cardNo,Integer branchId,Integer bookId) throws ClassNotFoundException, SQLException, IllegalNameException{
		return (BookLoan) readAll("select * from tbl_book_loans where cardNo=? and branchId=? and bookId=? and dateIn is null", new Object[]{cardNo,branchId,bookId}).get(0);
		
	}
	public List<BookLoan> readBookLoansbyBookIdAndBranchId(Integer bookId,Integer branchId) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_book_loans where bookId=? and branchId=? and dateIn is null", new Object[]{bookId,branchId});
		
	}
	public List<BookLoan> readAllBookLoans() throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("SELECT * FROM tbl_book_loans", null);
	}
	public void deleteBookLoans(BookLoan bookloans) throws SQLException, ClassNotFoundException {
		save("delete * from tbl_book_loans where cardNo=?", new Object[] { bookloans.getCardNo() });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		return extractDataFirstLevel(rs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<BookLoan> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoan> bookloans= new ArrayList<>();
		while(rs.next()){
			BookLoan b= new BookLoan();
			b.setDateIn(rs.getString("dateIn"));
			b.setDateOut(rs.getString("dateOut"));
			b.setDueDate(rs.getString("dueDate"));
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setCardNo(rs.getInt("cardNo"));
			bookloans.add(b);
			
		}
		return bookloans;
	}

}

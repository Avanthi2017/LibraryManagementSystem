package com.gcit.lbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.gcit.lbms.dao.BookLoansDAO;
import com.gcit.lbms.dao.ConnectionUtil;
import com.gcit.lbms.entity.BookLoan;
import com.gcit.lbms.exception.IllegalNameException;

public class BookLoanService {
	static ConnectionUtil util = new ConnectionUtil();

	public List<BookLoan> readAllBookLoans() throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.readAllBookLoans();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;

	}

	public List<BookLoan> readbookloansbyCardNo(int cardNo) throws IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.readLoansByCardNo(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<BookLoan> readActiveBookLoansbyCardNo(int cardNo) throws IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.readActiveBookLoansbyCardNo(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<BookLoan> readActiveBookLoansbyBookId(int bookId) throws IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.readActiveBookLoansbyBookId(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<BookLoan> readBookLoansbyCardNoAndBranchId(int cardNo, int branchId) throws IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.readLoansByCardNoAndBranchId(cardNo, branchId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<BookLoan> readBookLoansbyBookIdAndBranchId(int bookId, int branchId) throws IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.readBookLoansbyBookIdAndBranchId(bookId, branchId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public BookLoan readBookLoansbyIds(int cardNo, int branchId,int bookId) throws IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.readBookLoanByIds(cardNo, branchId, bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void addBookLoan(BookLoan bookloan) throws SQLException {
		Connection conn = null;

		DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		LocalDateTime date = LocalDateTime.now();
		// bookloan.get
		bookloan.setDateOut(date.format(ft));
		bookloan.setDueDate(date.plusDays(7).format(ft));

		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.bookCheckOut(bookloan);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			if (conn != null)
				conn.close();
		}

	}

	public void updateBookLoan(BookLoan bookloan) throws SQLException {
		Connection conn = null;
		DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		LocalDateTime date = LocalDateTime.now();
		// bookloan.get
		bookloan.setDateIn(date.format(ft));
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.bookCheckIn(bookloan);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void overWriteDueDate(BookLoan bookloan) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO adao = new BookLoansDAO(conn);
			adao.overrideDueDate(bookloan);
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

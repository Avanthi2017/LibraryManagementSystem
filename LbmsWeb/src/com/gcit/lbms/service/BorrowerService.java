package com.gcit.lbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lbms.dao.BorrowerDAO;
import com.gcit.lbms.dao.ConnectionUtil;
import com.gcit.lbms.dao.PublisherDAO;
import com.gcit.lbms.entity.Borrower;
import com.gcit.lbms.exception.IllegalNameException;

public class BorrowerService {
	static ConnectionUtil util = new ConnectionUtil();

	public List<Borrower> readAllBorrowers(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			return adao.readAllBorrowers(pageNo);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Borrower> readBorrowers(String borrowerName) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			return adao.readAllBorrowersbyName(borrowerName);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public Borrower readBorrowerByCardNo(int cardNo) throws IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowerByCardNo(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public Integer getBorrowerCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.getBorrowerCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public void addBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.addBorrower(borrower);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void updateBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.updateBorrower(borrower);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void deleteBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borrower);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

}

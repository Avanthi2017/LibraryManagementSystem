package com.gcit.lbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lbms.dao.BookCopiesDAO;
import com.gcit.lbms.dao.BranchDAO;
import com.gcit.lbms.dao.ConnectionUtil;
import com.gcit.lbms.entity.BookCopies;
import com.gcit.lbms.entity.Branch;
import com.gcit.lbms.exception.IllegalNameException;

public class BranchService {
	static ConnectionUtil util = new ConnectionUtil();

	public List<Branch> readAllBranches(Integer pageNo) throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readAllBranchs(pageNo);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;

	}
	public List<Branch> readAllBranches() throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readAllBranchs();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;

	}
	public Integer getBranchCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.getBranchCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Branch> readBranchs(String branchName) throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readAllBranchesByName(branchName);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;

	}

	public Branch readBranchById(Integer branchId) throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readBranchById(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public void addBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.addBranch(branch);
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

	public void updateBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.updateBranch(branch);
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

	public void deleteBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.deleteBranch(branch);
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

	public void addBookCopies(BookCopies bookcopies) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcdao.addBookCopies(bookcopies);
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

	public void updateBookCopies(BookCopies bookcopies) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			bdao.updateBookCopies(bookcopies);
			conn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	public List<BookCopies> readAllBookCopies() throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			return bdao.readAllBookCopies();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public void deleteBookCopies(BookCopies bookcopies) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			bdao.deleteBookCopies(bookcopies);
			conn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}


	public List<BookCopies> readBookCopiesbyBookId(int bookId) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			return bcdao.readAllBookCopiesByBookId(bookId);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<BookCopies> readBookCopiesByBranchId(int branchId) throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			return bcdao.readAllBookCopiesByBranchId(branchId);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public int readBookCopiesByBookIdAndBranchId(int bookId,int branchId) throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			return bcdao.readBookCopiesByBranchIdAndBookId(bookId,branchId);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return 0;
	}
	

}

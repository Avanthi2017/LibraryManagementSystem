package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.Branch;
import com.gcit.lbms.exception.IllegalNameException;

public class BranchDAO extends BaseDAO {

	public BranchDAO(Connection conn) {
		super(conn);
	}
		// TODO Auto-generated constructor stub
		public void addBranch(Branch branch) throws SQLException, ClassNotFoundException {
			save("insert into tbl_library_branch (branchName,branchAddress) values (?,?)", new Object[] {branch.getBranchName(),branch.getBranchAddress()});
		}

		public void updateBranch(Branch branch) throws SQLException, ClassNotFoundException {
			save("update tbl_library_branch set branchName=?,branchAddress=? where branchId = ?", new Object[] {branch.getBranchName(),branch.getBranchAddress(),branch.getBranchId()});
		}

		public void deleteBranch(Branch branch) throws SQLException, ClassNotFoundException {
			save("delete from tbl_library_branch where branchId = ?", new Object[] {branch.getBranchId()});
		}
		
		
		public List<Branch> readAllBranchesByName(String branchName) throws ClassNotFoundException, SQLException, IllegalNameException{
			return readAll("select * from tbl_library_branch where branchName like ?", new Object[]{"%"+branchName+"%"});
		}
		public List<Branch> readAllBranchs() throws ClassNotFoundException, SQLException, IllegalNameException{
			return readAll("select * from tbl_library_branch", null);
		}
		public List<Branch> readAllBranchs(Integer pageNo) throws ClassNotFoundException, SQLException, IllegalNameException{
			setPageNo(pageNo);
			return readAll("select * from tbl_library_branch", null);
		}
		public Integer getBranchCount() throws ClassNotFoundException, SQLException{
			return getCount("tbl_library_branch");
		}

		public Branch readBranchById(Integer branchId) throws ClassNotFoundException, SQLException, IllegalNameException{
			List<Branch> branches = readAll("select * from tbl_library_branch where branchId = ?", new Object[]{branchId});
			if(branches!=null && !branches.isEmpty()){
				return branches.get(0);
			}
			return null;
		}
		
		@SuppressWarnings("unchecked")
		public List<Branch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException{
			List<Branch> branches = new ArrayList<>();
			BookDAO bdao = new BookDAO(conn);
			BookLoansDAO bldao = new BookLoansDAO(conn);
			BorrowerDAO borrowerDAO= new BorrowerDAO(conn);
			while(rs.next()){
				Branch b = new Branch();
				b.setBranchId(rs.getInt("branchId"));
				b.setBranchName(rs.getString("branchName"));
				b.setBranchAddress(rs.getString("branchAddress"));
			//	b.setBookcopies(bcdao.readAllFirstLevel("select * from tbl_book_copies where branchId=?", new Object[]{rs.getInt("branchId")}));
				b.setBooks(bdao.readAllFirstLevel("select * from tbl_book right join tbl_book_copies on "
						+ "tbl_book.bookId=tbl_book_copies.bookId where tbl_book_copies.branchId="
						+ "(select tbl_library_branch.branchId from tbl_library_branch where tbl_library_branch.branchId=?)",new Object[]{rs.getInt("branchId")}));
				//b.setBookloans(bldao.readAllFirstLevel("select * from tbl_book_loans where branchId=?", new Object[]{rs.getInt("branchId")}));
				b.setBorrowers(borrowerDAO.readAllFirstLevel("select distinct * from tbl_borrower left join "
						+ "tbl_book_loans on tbl_book_loans.cardNo=tbl_borrower.cardNo "
						+ "where tbl_book_loans.branchId= (select tbl_library_branch.branchId "
						+ "from tbl_library_branch where tbl_library_branch.branchId=?) && tbl_book_loans.dateIn is null", new Object[]{rs.getInt("branchId")}));
				branches.add(b);
			}
			return branches;
		}
		
		@SuppressWarnings("unchecked")
		public List<Branch> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException{
			List<Branch> branches = new ArrayList<>();
			while(rs.next()){
				Branch b = new Branch();
				b.setBranchId(rs.getInt("branchId"));
				b.setBranchName(rs.getString("branchName"));
				b.setBranchAddress(rs.getString("branchAddress"));
				branches.add(b);
			}
			return branches;
		}

	}


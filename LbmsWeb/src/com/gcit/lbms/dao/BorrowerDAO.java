package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.Borrower;
import com.gcit.lbms.exception.IllegalNameException;

public class BorrowerDAO extends BaseDAO {

	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("insert into tbl_borrower (name,address,phone) values(?,?,?)",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone() });
	}

	public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("update tbl_borrower set name=?,address=?,phone=? where cardNo=?",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() });
	}
	public void deleteBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("delete from tbl_borrower where cardNo=?",
				new Object[] {borrower.getCardNo()});
	}
	public Integer getBorrowerCount() throws ClassNotFoundException, SQLException{
		return getCount("tbl_borrower");
	}
	public Borrower readBorrowerByCardNo(Integer cardNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		List<Borrower> borrowers = readAll("select * from tbl_borrower where cardNo = ?", new Object[]{cardNo});
		if(borrowers!=null && !borrowers.isEmpty()){
			return borrowers.get(0);
		}
		return null;
	}
	public List<Borrower> readAllBorrowersbyName(String borrower_name) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_borrower where name like ?", new Object[]{"%"+borrower_name+"%"});
	}
	public List<Borrower> readAllBorrowers(Integer pageNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		setPageNo(pageNo);
		return readAll("select * from tbl_borrower", null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
      BookLoansDAO bldao= new BookLoansDAO(conn); 
		List<Borrower>borrowers= new ArrayList<>();
		
		while(rs.next())
		{
			Borrower borrower =new Borrower();
		borrower.setCardNo(rs.getInt("cardNo"));
		borrower.setName(rs.getString("name"));
		borrower.setPhone(rs.getString("phone"));
		borrower.setAddress(rs.getString("address"));
		borrower.setBookloans(bldao.readAllFirstLevel("select * from tbl_book_loans where cardNo=?", new Object[]{rs.getInt("cardNo")}));
		borrowers.add(borrower);
		}
		return borrowers;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		// TODO Auto-generated method stub
		List<Borrower>borrowers= new ArrayList<>();
		
		while(rs.next())
		{
			Borrower borrower =new Borrower();
		borrower.setCardNo(rs.getInt("cardNo"));
		borrower.setName(rs.getString("name"));
		borrower.setPhone(rs.getString("phone"));
		borrower.setAddress(rs.getString("address"));
		borrowers.add(borrower);
		}
		return borrowers;
	}

}

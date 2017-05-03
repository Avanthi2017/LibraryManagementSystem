package com.gcit.lbms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lbms.entity.Publisher;
import com.gcit.lbms.exception.IllegalNameException;

public class PublisherDAO extends BaseDAO{
	
	public PublisherDAO(Connection conn) {
		super(conn);
	}
	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("insert into tbl_publisher (publisherId, publisherName,publisherAddress,publisherPhone) values (? , ?,?,?)", new Object[]{publisher.getPublisherId(),publisher.getPublisherName(),
				publisher.getPublisherAddress(),publisher.getPublisherPhone()});
	}

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?,publisherPhone=? where publisherId = ?", new Object[]{publisher.getPublisherName(),
				publisher.getPublisherAddress(),publisher.getPublisherPhone(),publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("delete from tbl_publisher where publisherId= ?", new Object[]{publisher.getPublisherId()});
	}
	
	public List<Publisher> readAllPublisherByName(String publisherName) throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_publisher where tbl_publisher like ?", new Object[]{"%"+publisherName+"%"});
	}
	public List<Publisher> readAllPublisher(Integer pageNo) throws ClassNotFoundException, SQLException, IllegalNameException{
		setPageNo(pageNo);
		return readAll("select * from tbl_publisher", null);
	}
	public List<Publisher> readAllPublisher() throws ClassNotFoundException, SQLException, IllegalNameException{
		return readAll("select * from tbl_publisher", null);
	}
	public Integer getPublisherCount() throws ClassNotFoundException, SQLException{
		return getCount("tbl_publisher");
	}

	
	
	public Publisher readPublisherById(Integer publisherId) throws ClassNotFoundException, SQLException, IllegalNameException{
		List<Publisher> publishers = readAll("select * from tbl_publisher where publisherId = ?", new Object[]{publisherId});
		if(publishers!=null && !publishers.isEmpty()){
			return publishers.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IllegalNameException {
		BookDAO bdao= new BookDAO(conn);
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			//Populate BOOKS
			if(rs.getInt("publisherId")>0){
				p.setBooks(bdao.readAllFirstLevel("select * from tbl_book where pubId=?", new Object[]{rs.getInt("publisherId")}));
			}
			publishers.add(p);
		}
		return publishers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException, IllegalNameException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			publishers.add(p);
		}
		return publishers;
	}

}

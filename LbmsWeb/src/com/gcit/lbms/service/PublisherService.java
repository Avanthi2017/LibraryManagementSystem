package com.gcit.lbms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lbms.dao.ConnectionUtil;
import com.gcit.lbms.dao.PublisherDAO;
import com.gcit.lbms.entity.Publisher;
import com.gcit.lbms.exception.IllegalNameException;

public class PublisherService {
	static  ConnectionUtil util = new ConnectionUtil();
	public List<Publisher> readAllPublishers(Integer PageNo ) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readAllPublisher(PageNo);

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Publisher> readAllPublishers() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readAllPublisher();

		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public Publisher readPublisherById(Integer publisherId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublisherById(publisherId);
		} catch (ClassNotFoundException | SQLException | IllegalNameException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.addPublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void updatePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.updatePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(publisher);

			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	public List<Publisher> readPublishers(String publisherName) throws SQLException, IllegalNameException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
				return pdao.readAllPublisherByName(publisherName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public Integer getPublisherCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			return adao.getPublisherCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

}

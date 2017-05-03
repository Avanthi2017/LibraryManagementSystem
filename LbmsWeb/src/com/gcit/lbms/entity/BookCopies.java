package com.gcit.lbms.entity;

import java.io.Serializable;

public class BookCopies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8698462912587446557L;
	 private int noOfCopies;
	 private int bookId;
	 private int branchId;
	
	public int getNoOfCopies() {
		return noOfCopies;
	}
	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + branchId;
		result = prime * result + noOfCopies;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BookCopies other = (BookCopies) obj;
		if (bookId != other.bookId) {
			return false;
		}
		if (branchId != other.branchId) {
			return false;
		}
		if (noOfCopies != other.noOfCopies) {
			return false;
		}
		return true;
	}
	

	 
}
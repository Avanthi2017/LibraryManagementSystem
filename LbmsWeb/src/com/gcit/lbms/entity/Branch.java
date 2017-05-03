package com.gcit.lbms.entity;

import java.io.Serializable;
import java.util.List;

import com.gcit.lbms.exception.IllegalNameException;

public class Branch implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -1296611266689497146L;
private String branchName;
private String branchAddress;
private int branchId;
private List<Book>books;
private List<BookLoan> bookloans;
private List<Borrower>borrowers;
/**
 * @return the branchName
 */
public String getBranchName() {
	return branchName;
}
/**
 * @return the borrowers
 */
public List<Borrower> getBorrowers() {
	return borrowers;
}
/**
 * @param borrowers the borrowers to set
 */
public void setBorrowers(List<Borrower> borrowers) {
	this.borrowers = borrowers;
}
/**
 * @param branchName the branchName to set
 */
public void setBranchName(String branchName) throws IllegalNameException {
	if (branchName.length() > 46) {
		throw new IllegalNameException("Name Should be less than 45 characters");
	}
	this.branchName = branchName;
}
/**
 * @return the branchAddress
 */
public String getBranchAddress() {
	return branchAddress;
}
/**
 * @param branchAddress the branchAddress to set
 */
public void setBranchAddress(String branchAddress) {
	this.branchAddress = branchAddress;
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
/**
 * @return the books
 */
public List<Book> getBooks() {
	return books;
}
/**
 * @param books the books to set
 */
public void setBooks(List<Book> books) {
	this.books = books;
}
/**
 * @return the bookloans
 */
public List<BookLoan> getBookloans() {
	return bookloans;
}
/**
 * @param bookloans the bookloans to set
 */
public void setBookloans(List<BookLoan> bookloans) {
	this.bookloans = bookloans;
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bookloans == null) ? 0 : bookloans.hashCode());
	result = prime * result + ((books == null) ? 0 : books.hashCode());
	result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
	result = prime * result + branchId;
	result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
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
	Branch other = (Branch) obj;
	if (bookloans == null) {
		if (other.bookloans != null) {
			return false;
		}
	} else if (!bookloans.equals(other.bookloans)) {
		return false;
	}
	if (books == null) {
		if (other.books != null) {
			return false;
		}
	} else if (!books.equals(other.books)) {
		return false;
	}
	if (branchAddress == null) {
		if (other.branchAddress != null) {
			return false;
		}
	} else if (!branchAddress.equals(other.branchAddress)) {
		return false;
	}
	if (branchId != other.branchId) {
		return false;
	}
	if (branchName == null) {
		if (other.branchName != null) {
			return false;
		}
	} else if (!branchName.equals(other.branchName)) {
		return false;
	}
	return true;
}




}

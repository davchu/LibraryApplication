package com.davidchung.libraryapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

/**
 * Simple class to store records for library members
 * that have borrowed books
 * 
 * @author David Chung
 *
 */
@Entity
@Table(name="BOOK_LOAN")
public class BookLoan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seqNumber", nullable=false, unique=true, length=11)
	private int seqNum;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="ID")
	private LibraryMember libraryMember;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="ISBN")
	private Book book;
	
	@Column(name="BORROW_START_DATE")
	private Date startDate;
	
	@Column(name="BORROW_END_DATE")
	private Date endDate;
	
	public String getMemberName() {
		return libraryMember.getName();
	}

	public String getTitle() {
		return book.getBookTitle();
	}

	public String getIsbn() {
		return book.getIsbn();
	}
	
	public String getAuthor() {
		return book.getAuthor();
	}

	@JsonIgnore
	public LibraryMember getLibraryMember() {
		return libraryMember;
	}

	public void setLibraryMember(LibraryMember libraryMember) {
		this.libraryMember = libraryMember;
	}

	@JsonIgnore
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	@JsonSerialize(using=DateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public String getDateAsString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using=DateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "\nBook Loan Details: " + book.getIsbn() + ", " + book.getBookTitle() 
				+ ", " + libraryMember.getId() + ", " + libraryMember.getName();
	}

	public String getStartDateAsString() {
		return getDateAsString(startDate);
	}

	public String getEndDateAsString() {
		return getDateAsString(endDate);
	}
}
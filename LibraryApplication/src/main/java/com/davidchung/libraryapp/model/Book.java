package com.davidchung.libraryapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Simple Book class to store book details 
 * 
 * @author David Chung
 *
 */
@Entity
@Table(name="BOOK")
public class Book {

	@Id
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="BOOK_TITLE")
	private String bookTitle;

	@Column(name="AUTHOR")
	private String author;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="book")
	private List<BookLoan> bookLoan;

	public List<BookLoan> getBookLoan() {
		return bookLoan;
	}

	public void setBookLoan(List<BookLoan> bookLoan) {
		this.bookLoan = bookLoan;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Book(String isbn, String bookTitle, String author) {
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.author = author;
	}

	public Book() {
		
	}

	@Override
	public String toString() {
		return "\nisbn: " + this.isbn + " book title: " + this.bookTitle + " author: " + this.author;
	}
}

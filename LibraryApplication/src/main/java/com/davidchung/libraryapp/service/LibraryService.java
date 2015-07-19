package com.davidchung.libraryapp.service;

import java.util.List;

import com.davidchung.libraryapp.model.Book;
import com.davidchung.libraryapp.model.BookLoan;
import com.davidchung.libraryapp.model.LibraryMember;

/**
 * Interface for library services such as reading and writing 
 * to the database
 * 
 * @author David Chung
 *
 */
public interface LibraryService {
	
	public void addMember(LibraryMember member);
	
	public LibraryMember getMember(int id);
	
	public List<LibraryMember> getAllMembers();
	
	public void removeMember(int id);
	
	public void addBook(Book book);

	public List<Book> getAllBooks();
	
	public Book getBook(String isbn);

	public void addBookLoan(int memId, String isbn);
	
	public void removeBookLoan(int memberId, String isbn);
	
	public List<BookLoan> getAllBookLoans();

	public List<BookLoan> getLoanByMemberId(int id);
}
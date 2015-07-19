package com.davidchung.libraryapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.davidchung.libraryapp.model.Book;
import com.davidchung.libraryapp.model.BookLoan;
import com.davidchung.libraryapp.model.LibraryMember;

public class LibrarySession implements LibraryService {
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(LibrarySession.class);
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addMember(LibraryMember member) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(member);
		tx.commit();
		session.close();
	}
	
	@Override
	public LibraryMember getMember(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		LibraryMember m = (LibraryMember) session.load(LibraryMember.class,  new Integer(id));
		logger.info("Member loaded successfully, Member details=" + m);
		session.getTransaction().commit();
		return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LibraryMember> getAllMembers() {
		
		Session session = this.sessionFactory.openSession();
		List<LibraryMember> membersList = session.createQuery("from LibraryMember").list();
		for(LibraryMember m : membersList) {
			logger.info("MemberList::" + m);
		}
		session.close();
		return membersList;
	}

	@Override
	public void removeMember(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		LibraryMember m = (LibraryMember) session.load(LibraryMember.class, new Integer(id));
		if(null != m) {
			session.delete(m);
		}
		session.getTransaction().commit();
		session.close();
		logger.info("Library member deleted successfully, member details=" + m);

	}
	
	@Override
	public void addBook(Book book) {
		Session session = this.sessionFactory.openSession();
		Transaction tx =session.beginTransaction();
		session.persist(book);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		Session session = this.sessionFactory.openSession();
		List<Book> bookList = session.createQuery("from Book").list();
		for(Book b : bookList) {
			logger.info("BookList::" + b);
		}
		session.close();
		return bookList;
	}
	
	@Override
	public Book getBook(String isbn) {
		Session session = this.sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		Book b =  (Book) session.load(Book.class,  new String(isbn));
		logger.info("Book loaded successfully, Book details=" + b);
		session.getTransaction().commit();
		session.close();
		return b;
	}

	@Override
	public void addBookLoan(int memId, String isbn) {
		Session session = this.sessionFactory.openSession();
		Transaction tx =session.beginTransaction();
		BookLoan bookLoan = new BookLoan();
		LibraryMember m = (LibraryMember) session.load(LibraryMember.class,  new Integer(memId));
		Book b = (Book) session.load(Book.class, isbn);
		Date startDate = new Date();
		Date dueDate = new Date();
		bookLoan.setLibraryMember(m);
		bookLoan.setBook(b);
		bookLoan.setStartDate(startDate);
		bookLoan.setEndDate(dueDate);
		session.persist(bookLoan);
		tx.commit();
		session.close();
	}
	
	@Override
	public void removeBookLoan(int memberId, String isbn) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookLoan> getAllBookLoans() {
		Session session = this.sessionFactory.openSession();
		List<BookLoan> bookLoanList = session.createQuery("from BookLoan").list();
		for(BookLoan b : bookLoanList) {
			logger.info("BookLoanList::" + b);
		}
		session.close();
		return bookLoanList;
	}

	@SuppressWarnings("unchecked")
	public List<BookLoan> getLoanByMemberId(int id) {
		Session session = this.sessionFactory.openSession();
		List<BookLoan> bookLoanList = session.createQuery("from BookLoan").list();
		List<BookLoan> bl = new ArrayList<BookLoan>();
		for(BookLoan b : bookLoanList) {
			if(b.getLibraryMember().getId() == id) {
				bl = b.getLibraryMember().getBookLoans();
			}
		}
		
		return bl;
	}

}
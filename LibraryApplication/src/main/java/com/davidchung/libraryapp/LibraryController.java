package com.davidchung.libraryapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.davidchung.libraryapp.model.Book;
import com.davidchung.libraryapp.model.BookLoan;
import com.davidchung.libraryapp.model.LibraryMember;
import com.davidchung.libraryapp.service.LibraryService;

/**
 * Handles requests for the application home page.
 * 
 * @author: David Chung
 */
@Controller
public class LibraryController {
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

	LibraryService libraryService;
	
	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );

		List<LibraryMember> members = LibraryDataSetup.createLibraryMembers();
		List<Book> books = LibraryDataSetup.createBooks();

		if(libraryService.getAllMembers().isEmpty()) {
			for(LibraryMember m : members) {
				libraryService.addMember(m);
			}
			
			for(Book b : books) {
				libraryService.addBook(b);
			}
			libraryService.addBookLoan(1, "12345");
			libraryService.addBookLoan(2, "12346");
			libraryService.addBookLoan(2, "12347");
			libraryService.addBookLoan(3, "12348");
			libraryService.addBookLoan(4, "12349");
			libraryService.addBookLoan(5, "12350");
			libraryService.addBookLoan(5, "12351");
		}
		libraryService.getAllMembers();
		libraryService.getAllBooks();
		libraryService.getAllBookLoans();

		return "home";
	}
	
	/**
	 * Used to return JSON data for all library members to populate the select list
	 */
	@RequestMapping(value = "/getAllLibraryMembers", method = RequestMethod.GET)
	public @ResponseBody List<LibraryMember> getAllLibraryMembers() {
		logger.info("In getAllLibraryMembers");
		List<LibraryMember> members = null;
		members = libraryService.getAllMembers();
		return members;		
	}
	
	/**
	 * Used to obtain the book loan for the member selected
	 */
	@RequestMapping(value= "/getLibraryMemberDetails/{id}", method = RequestMethod.GET)
	public @ResponseBody List<BookLoan> getLibraryMemberDetails(@PathVariable("id") int id) {
		logger.info("In getLibraryMemberDetails");
		List<BookLoan> bl = new ArrayList<BookLoan>();
		bl = libraryService.getLoanByMemberId(id);
		logger.info("Book loan: " + bl);
		return bl;
	}
	
	public static void main(String args[]) {
	}
}

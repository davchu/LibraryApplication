package com.davidchung.libraryapp;

import java.util.ArrayList;
import java.util.List;

import com.davidchung.libraryapp.model.Book;
import com.davidchung.libraryapp.model.LibraryMember;

/**
 * A class to load all data on start up of 
 * the application
 * 
 * @author David Chung
 *
 */
public class LibraryDataSetup {	
	public static List<LibraryMember> createLibraryMembers() {
		
		List<LibraryMember> members = new ArrayList<LibraryMember>();
		
		LibraryMember member = new LibraryMember("David Myers", "12345678", "dmyer@libraryapp.com.au");
		members.add(member);
		member = new LibraryMember("David Scott", "22345678", "dscott@libraryapp.com.au");
		members.add(member);
		member = new LibraryMember("David Smith", "32345678", "dsmith@libraryapp.com.au");
		members.add(member);
		member = new LibraryMember("David Jones", "42345678", "djones@libraryapp.com.au");
		members.add(member);
		member = new LibraryMember("David Stump", "52345678", "dstump@libraryapp.com.au");
		members.add(member);
		member = new LibraryMember("David Frost", "62345678", "dfrost@libraryapp.com.au");
		members.add(member);
		
		return members;		
	}
	
	public static List<Book> createBooks() {
		
		List<Book> books = new ArrayList<Book>();
		Book book = new Book("12345", "The House of God", "Samuel Shem");
		books.add(book);
		book = new Book("12346", "The Stranger", "Albert Camus");
		books.add(book);
		book = new Book("12347", "On the road", "Jack Kerouac");
		books.add(book);
		book = new Book("12348", "Steve Jobs", "Walter Isaacson");
		books.add(book);
		book = new Book("12349", "The God Delusion", "Richard Dawkins");
		books.add(book);
		book = new Book("12350", "Heart of Darkness", "Joseph Conrad");
		books.add(book);
		book = new Book("12351", "Flat Stanley", "Jeff Brown");
		books.add(book);
		
		return books;
	}
}
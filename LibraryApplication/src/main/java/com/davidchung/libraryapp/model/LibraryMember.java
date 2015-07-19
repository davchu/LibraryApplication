package com.davidchung.libraryapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple class to store membership details for
 * members of the Library
 * 
 * @author David Chung
 *
 */
@Entity
@Table(name="LIBRARY_MEMBER")
public class LibraryMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "libraryMember")
	private List<BookLoan> bookLoans;
		
	@JsonIgnore
	public List<BookLoan> getBookLoans() {
		return bookLoans;
	}

	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LibraryMember() {
		
	}
	
	public LibraryMember(String name, String phoneNumber, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "\nid: "+ this.id+ " name: " + this.name + " number: " + this.phoneNumber + " email: " + this.email;
	}

	
}

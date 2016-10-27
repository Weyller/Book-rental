package model;

import java.util.LinkedList;
import java.util.List;

import exc.BookNotFoundException;

public class Client {
	private String firstName;
	private String lastName;
	private final long ID;
	private List<Book>rentedBooks;
	private int nrOfRentedBooks;
	
	public Client(String firstName, String lastName, long ID){
		rentedBooks = new LinkedList<Book>();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
		this.nrOfRentedBooks = 0;
	}
	
	public List<Book> getListOfRentedBooks(){
		return rentedBooks;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public long getID() {
		return ID;
	}

	public int getNrOfRentedBooks() {
		return nrOfRentedBooks;
	}
	
	public void rentBook(Book b, int days){
		rentedBooks.add(b);
		this.nrOfRentedBooks++;
		b.rentBook(days);
	}
	
	public void returnBook(Book b) throws BookNotFoundException{
		if(!rentedBooks.remove(b)){
			throw new BookNotFoundException("Looks like client did not rent that book.");
		}
		else{
			this.nrOfRentedBooks--;
			b.returnBook();
		}
	}
	
}

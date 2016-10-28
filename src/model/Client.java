package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import exc.BookNotFoundException;

public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private final long ID;
	private List<Book>rentedBooks;
	private int nrOfRentedBooks;
	private static long clientsID = 0;
	
	public Client(String firstName, String lastName){
		rentedBooks = new LinkedList<Book>();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = clientsID++;
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
	
	public static long getClientsID(){
		return clientsID;
	}
	
	public static void setClientsID(long backupID){
		Client.clientsID = backupID;
	}
	
	public void rentBook(Book b){
		rentedBooks.add(b);
		this.nrOfRentedBooks++;
		b.rentBook();
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

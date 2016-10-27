package controller;

import java.io.File;
import java.util.List;

import model.Book;
import model.Client;
import model.Database;
import model.Rental;
import view.BookFormEvent;
import view.ClientFormEvent;

public class Controller {
	private Database db = new Database();
	
	public Controller(){
		
	}
	
	public List<Client> getClients(){
		return db.getClients();
	}
	
	public List<Book> getBooks(){
		return db.getBooks();
	}
	
	public List<Rental> getRentals(){
		return db.getRentals();
	}
	
	public void loadFile(File f){
		db.loadFromFile(f);
	}
	
	public void saveToFile(File f){
		db.saveToFile(f);
	}
	
	public void addClient(ClientFormEvent e){
		String firstName = e.getFirstName();
		String lastName = e.getLastName();
		Client c = new Client(firstName, lastName);
		db.addClient(c);
	}
	
	public void removeClient(int index){
		db.removeClient(index);
	}
	
	public void addBook(BookFormEvent e){
		String title = e.getTitle();
		String author = e.getAuthor();
		Book b = new Book(title, author);
		db.addBook(b);
	}
	
	public void removeBook(int index){
		db.removeBook(index);
	}
}

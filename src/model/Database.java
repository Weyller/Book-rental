package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import exc.UnclearedException;

public class Database {
	private List<Client>clients;
	private List<Book>books;
	private List<Rental>rentalsHistory;
	
	public Database(){
		clients = new LinkedList<>();
		books = new LinkedList<>();
		rentalsHistory = new LinkedList<>();
	}
	
	public void loadFromFile(File file){
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Client[] clientsArray = (Client[])ois.readObject();
			clients.clear();
			clients.addAll(Arrays.asList(clientsArray));
			
			Book[] booksArray = (Book[])ois.readObject();
			books.clear();
			books.addAll(Arrays.asList(booksArray));
			
			Rental[] rentalsArray = (Rental[])ois.readObject();
			rentalsHistory.clear();
			rentalsHistory.addAll(Arrays.asList(rentalsArray));
			
			long cIDbackup = ois.readLong();
			Client.setClientsID(cIDbackup);
			
			long bIDbackup = ois.readLong();
			Book.setBooksID(bIDbackup);
			
			ois.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		catch (IOException | ClassNotFoundException e) {
			System.out.println("Error occured while saving objects to file.");
			e.printStackTrace();
		}
	}
	
	public void saveToFile(File file){
		try{
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			Client[] clientsArray = clients.toArray(new Client[clients.size()]);
			oos.writeObject(clientsArray);
			
			Book[] booksArray = books.toArray(new Book[books.size()]);
			oos.writeObject(booksArray);
			
			Rental[] rentalsArray = rentalsHistory.toArray(new Rental[rentalsHistory.size()]);
			oos.writeObject(rentalsArray);
			
			long cID = Client.getClientsID();
			oos.writeLong(cID);
			
			long bID = Book.getBooksID();
			oos.writeLong(bID);
			
			oos.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		catch(IOException e){
			System.out.println("Error occured while saving objects to file.");
			e.printStackTrace();
		}
	}
	
	public void addClient(Client c){
		clients.add(c);
	}
	
	public void removeClient(int index) throws UnclearedException{
		Client c = clients.get(index);
		if(c.getNrOfRentedBooks() > 0){
			throw new UnclearedException("Client still has some books rented! Can not delete.");
		}
		clients.remove(index);
	}
	
	public void addBook(Book b){
		books.add(b);
	}
	
	public void removeBook(int index) throws UnclearedException{
		Book b = books.get(index);
		if(b.getIsRented() == true){
			throw new UnclearedException("Book is rented, client must return it! Can not delete.");
		}
		books.remove(index);
	}
	
	public void addRental(Rental r){
		rentalsHistory.add(r);
	}
	
	public List<Client> getClients(){
		return Collections.unmodifiableList(clients);
	}
	
	public List<Book> getBooks(){
		return Collections.unmodifiableList(books);
	}
	
	public List<Rental> getRentals(){
		return Collections.unmodifiableList(rentalsHistory);
	}
}

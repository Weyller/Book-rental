package test;

import java.util.List;

import model.Book;
import model.Client;

public class Demo {
	public static void main(String[]args){
		Client c = new Client("Johny", "Bravo", 12344);
		Book b1 = new Book("a", "b", 134);
		c.rentBook(b1,2);
		System.out.println(c.getNrOfRentedBooks());
		List<Book> cRented = c.getListOfRentedBooks();
		for(int i = 0; i < cRented.size(); i++){
			System.out.println(cRented.get(i).toString());
		}
	}
}

package viewEvents;

import java.util.EventObject;

import model.Book;

public class RentBookEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	private Book bookToRent;
	
	public RentBookEvent(Object source) {
		super(source);
	}
	
	public RentBookEvent(Object source, Book bookToRent){
		super(source);
		this.bookToRent = bookToRent;
	}
	
	public Book getBookToRent(){
		return bookToRent;
	}
}

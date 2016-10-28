package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rental implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Client c;
	private Book b;
	private Date rentalDate;
	
	public Rental(Client c, Book b, Date rentalDate){
		this.c = c;
		this.b = b;
		this.rentalDate = rentalDate;
	}
	
	public String getClientName(){
		return c.getFirstName() + " " + c.getLastName();
	}
	
	public long getClientID(){
		return c.getID();
	}
	
	public String getBookTitle(){
		return b.getTitle();
	}
	
	public String getBookTitleAndAuthor(){
		return b.getTitle() + " " + b.getAuthor();
	}
	
	public long getBookID(){
		return b.getID();
	}
	
	public String getRentalDate(){
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return f.format(rentalDate).toString();
	}
}

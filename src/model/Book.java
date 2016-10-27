package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	private String author;
	private String title;
	private boolean isRented;
	private final long ID;
	private Date returnDate;
	
	public Book(String author, String title, long ID){
		this.author = author;
		this.title = title;
		this.ID = ID;
	}
	
	public String toString(){
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return author + " " + title + " " + ID + " " + f.format(returnDate).toString();
	}
	
	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public long getID() {
		return ID;
	}
	
	public Date getReturnDate(){
		return returnDate;
	}
	
	public boolean getIsRented(){
		return this.isRented;
	}
	
	private void rentFor(int days){
		Date d = new Date();
		d.setTime(d.getTime() + days * 24 * 60 * 60 * 1000);
		this.returnDate = d;
	}
	
	public void rentBook(int days){
		this.isRented = true;
		rentFor(days);
	}
	
	public void returnBook(){
		this.isRented = false;
		this.returnDate = null;
	}
	
}

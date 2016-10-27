package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String author;
	private String title;
	private boolean isRented;
	private final long ID;
	private Date returnDate;
	private static long booksID = 0;
	
	public Book(String author, String title){
		this.author = author;
		this.title = title;
		this.ID = booksID++;
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
	
	public static long getBooksID(){
		return Book.booksID;
	}
	
	public static void setBooksID(long bookIdBackup){
		Book.booksID = bookIdBackup;
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

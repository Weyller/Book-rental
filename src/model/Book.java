package model;

import java.io.Serializable;

public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String author;
	private String title;
	private boolean isRented;
	private final long ID;
	private static long booksID = 0;
	
	public Book(String author, String title){
		this.author = author;
		this.title = title;
		this.ID = booksID++;
	}
	
	public String toString(){
		return author + " " + title + " " + ID ;
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
	
	public boolean getIsRented(){
		return this.isRented;
	}
	
	public static long getBooksID(){
		return Book.booksID;
	}
	
	public static void setBooksID(long bookIdBackup){
		Book.booksID = bookIdBackup;
	}
	
	public void rentBook(){
		this.isRented = true;
	}
	
	public void returnBook(){
		this.isRented = false;
	}
	
}

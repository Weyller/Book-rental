package view;

import java.util.EventObject;

public class BookFormEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	
	public BookFormEvent(Object source) {
		super(source);
	}
	
	public BookFormEvent(Object source, String title, String author){
		this(source);
		this.title = title;
		this.author = author;
	}

	public String getTitle(){
		return title;
	}
	
	public String getAuthor(){
		return author;
	}
}

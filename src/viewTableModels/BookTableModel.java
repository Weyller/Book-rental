package viewTableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Book;

public class BookTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"ID", "title", "author", "is rented"}; 
	private List<Book>books;
	
	public BookTableModel(){
		
	}
	
	public void setData(List<Book>data){
		this.books = data;
	}
	
	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book b = books.get(rowIndex);
		switch(columnIndex){
		case 0:
			return b.getID();
		case 1:
			return b.getTitle();
		case 2:
			return b.getAuthor();
		case 3:
			return b.getIsRented();
		}
		return null;
	}
	
	public String getColumnName(int index){
		return columnNames[index];
	}
	
	public Book getBookFromRow(int rowIndex){
		return books.get(rowIndex);
	}

}

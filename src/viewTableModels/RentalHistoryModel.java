package viewTableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Rental;

public class RentalHistoryModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"client ID","client name", "book ID", "book title", "rental date"};
	private List<Rental> rentalsList;
	
	public RentalHistoryModel(){
	}
	
	public void setData(List<Rental>data){
		this.rentalsList = data;
	}
	
	@Override
	public int getRowCount() {
		return rentalsList.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Rental r = rentalsList.get(rowIndex);
		 // "client ID", "book ID", "book title", "rental date"
		switch(columnIndex){
		case 0:
			return r.getClientID();
		case 1:
			return r.getClientName();
		case 2:
			return r.getBookID();
		case 3:
			return r.getBookTitle();
		case 4:
			return r.getRentalDate();
		}
		return null;
	}
	
	public String getColumnName(int index){
		return columnNames[index];
	}

}

package viewTableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Client;

public class ClientTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnName = {"ID", "First name", "Last name", "#Rented books"};
	private List<Client> clientList;
	
	public ClientTableModel(){
	}
	
	public void setData(List<Client>list){
		this.clientList = list;
	}
	
	@Override
	public int getRowCount() {
		return clientList.size();
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Client c = clientList.get(rowIndex);
		switch(columnIndex){
			case 0:
				return c.getID();
			case 1:
				return c.getFirstName();
			case 2:
				return c.getLastName();
			case 3:
				return c.getNrOfRentedBooks();
		}
		return null;
	}
	
	public String getColumnName(int index){
		return columnName[index];
	}
}

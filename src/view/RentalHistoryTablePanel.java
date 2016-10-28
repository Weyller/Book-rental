package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Rental;
import viewTableModels.RentalHistoryModel;

public class RentalHistoryTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable rentalsTable;
	private RentalHistoryModel model;
	
	public RentalHistoryTablePanel(){
		model = new RentalHistoryModel();
		rentalsTable = new JTable(model);
		setLayout(new BorderLayout());
		add(new JScrollPane(rentalsTable), BorderLayout.CENTER);
	}
	
	public void setData(List<Rental>data){
		model.setData(data);
	}
	
	public void refresh(){
		model.fireTableDataChanged();
	}
}

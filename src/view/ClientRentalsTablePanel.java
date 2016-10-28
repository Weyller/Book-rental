package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Book;
import viewListeners.ClientRentalsTableListener;
import viewTableModels.BookTableModel;

public class ClientRentalsTablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable clientRentalsTable;
	private BookTableModel model;
	private RightPanel rightPanel;
	private ClientRentalsTableListener listener;
	
	public ClientRentalsTablePanel(){
		model = new BookTableModel();
		clientRentalsTable = new JTable(model);
		rightPanel = new RightPanel();
		
		setLayout(new BorderLayout());
		add(new JScrollPane(clientRentalsTable), BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
	}
	
	public void refresh(){
		model.fireTableDataChanged();
	}
	
	public void setClientBooksData(List<Book>data){
		model.setData(data);
	}
	
	public void setCurrentClientData(String ID, String name){
		rightPanel.setCurrentClientData(ID, name);
	}
	
	public void setListener(ClientRentalsTableListener listener){
		this.listener = listener;
	}
	
	private void noBookSelectedDialog(){
		JOptionPane.showMessageDialog(null, "No row selected!", "Warning", JOptionPane.ERROR_MESSAGE);
	}
	
	class RightPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		
		private JLabel clientInfo;
		private JLabel ID;
		private JLabel name;
		private JButton returnBook;
		private JButton closePanel;
		
		public RightPanel(){
			clientInfo = new JLabel("Client info: ");
			ID = new JLabel();
			name = new JLabel();
			
			returnBook = new JButton("Return book");
			returnBook.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int selected = clientRentalsTable.getSelectedRow();
					if(selected == -1){
						noBookSelectedDialog();
					}
					else if(listener != null){
						listener.returnBook(selected);
					}
				}
			});
			
			closePanel = new JButton("Close panel");
			closePanel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(listener != null){
						listener.closePanel();
					}
				}
			});
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			
			Insets standard = new Insets(5,0,0,0);
			gbc.insets = standard;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.CENTER;
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(clientInfo, gbc);
			gbc.gridy = 1;
			add(ID, gbc);
			gbc.gridy = 2;
			add(name, gbc);
			gbc.gridy = 3;
			add(returnBook, gbc);
			gbc.gridy = 4;
			add(closePanel, gbc);
		}
		
		private void setCurrentClientData(String ID, String name){
			this.ID.setText("ID: " + ID);
			this.name.setText("Name: " + name);
		}
	}
}

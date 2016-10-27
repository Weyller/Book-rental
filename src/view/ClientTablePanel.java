package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Client;
import viewListeners.ClientTableListener;

public class ClientTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable clientTable;
	private ClientTableModel model;
	private ClientTableListener listener;
	
	public ClientTablePanel(){
		model = new ClientTableModel();
		clientTable  = new JTable(model);
		
		clientTable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getClickCount() == 2){
					int row = clientTable.rowAtPoint(e.getPoint());
					if(listener != null){
						listener.showRentedBooks(row);
					}
				}
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(clientTable), BorderLayout.CENTER);
		add(new RightPanel(), BorderLayout.EAST);
	}
	
	public void setData( List<Client> clientsList){
		model.setData(clientsList);
	}
	
	public void refresh(){
		model.fireTableDataChanged();
	}
	
	public void setPersonTableListener(ClientTableListener listener){
		this.listener = listener;
	}
	
	private void noClientSelectedDialog(){
		JOptionPane.showMessageDialog(null, "No row selected!", "Warning", JOptionPane.ERROR_MESSAGE);
	}
	
	class RightPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private JButton rentBook;
		private JButton returnBook;
		private JButton deleteClient;
		
		public RightPanel(){
			rentBook = new JButton("Rent book");
			rentBook.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int selected = clientTable.getSelectedRow();
					if(selected == -1){
						noClientSelectedDialog();
					}
					else if(listener != null){
						listener.rentBook(selected);
					}
				}
			});
			
			deleteClient = new JButton("Delete client");
			deleteClient.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int selected = clientTable.getSelectedRow();
					if(selected == -1){
						noClientSelectedDialog();
					}
					else if(listener != null){
						listener.deleteClient(selected);
					}
				}
			});
			
			returnBook = new JButton("Return book");
			returnBook.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int selected = clientTable.getSelectedRow();
					if(selected == -1){
						noClientSelectedDialog();
					}
					else if(listener != null){
						listener.showRentedBooks(selected);
					}
				}
			});
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			Insets buttonsInsets = new Insets(5,0,0,0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = buttonsInsets;
			gbc.gridx = 0;
			gbc.gridy = 0;
			add(rentBook, gbc);
			gbc.gridy = 1;
			add(returnBook, gbc);
			gbc.gridy = 2;
			add(deleteClient, gbc);
		}
	}
}



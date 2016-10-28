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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Client;
import viewListeners.ClientTableListener;
import viewTableModels.ClientTableModel;

public class ClientTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable clientTable;
	private ClientTableModel model;
	private ClientTableListener listener;
	private RightPanel rightPanel;
	
	public ClientTablePanel(){
		model = new ClientTableModel();
		clientTable  = new JTable(model);
		rightPanel = new RightPanel();
		this.setConfirmPanelVisibility(false);
		
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
		add(rightPanel, BorderLayout.EAST);
	}
	
	public void setData( List<Client> clientsList){
		model.setData(clientsList);
	}
	
	public void refresh(){
		model.fireTableDataChanged();
	}
	
	public void setClientTableListener(ClientTableListener listener){
		this.listener = listener;
	}
	
	private void noClientSelectedDialog(){
		JOptionPane.showMessageDialog(null, "No row selected!", "Warning", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setConfirmPanelVisibility(boolean visibility){
		rightPanel.setRentalPanelVisibility(visibility);
	}
	
	public void setBookToRentInfo(String id, String title, String author){
		rightPanel.setBookToRentInfo(id, title, author);
	}
	
	class RightPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private JButton returnBook;
		private JButton deleteClient;
		
		private JButton confirmButton;
		private JButton cancelRentalButton;
		private JLabel currentBook;
		private JLabel currentBookId;
		private JLabel currentBookTitle;
		private JLabel currentBookAuthor;
		
		public RightPanel(){
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
			
			confirmButton = new JButton("Confirm");
			confirmButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int selected = clientTable.getSelectedRow();
					if(selected == -1){
						noClientSelectedDialog();
					}
					else if(listener != null){
						listener.confirmRenting(selected);
					}
				}
			});
			
			cancelRentalButton = new JButton("Cancel");
			cancelRentalButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(listener != null){
						listener.cancelRenting();
					}
				}
			});
			
			currentBook = new JLabel("Selected book: ");
			currentBookId = new JLabel();
			currentBookTitle = new JLabel();
			currentBookAuthor = new JLabel();
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			Insets buttonsInsets = new Insets(5,0,0,5);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = buttonsInsets;
			gbc.gridx = 0;
			gbc.gridy = 0;
			
			add(currentBook, gbc);
			gbc.gridy = 1;
			add(currentBookId, gbc);
			gbc.gridy = 2;
			add(currentBookTitle, gbc);
			gbc.gridy = 3;
			add(currentBookAuthor, gbc);
			gbc.gridy = 4;
			add(confirmButton, gbc);
			gbc.gridx = 1;
			gbc.insets = new Insets(5,0,0,0);
			add(cancelRentalButton, gbc);
			
			gbc.insets = new Insets(20,0,0,0);
			gbc.gridwidth = 2;
			gbc.gridy = 5;
			gbc.gridx = 0;
			add(returnBook, gbc);
			gbc.insets = new Insets(5,0,0,0);
			gbc.gridy = 6;
			add(deleteClient, gbc);
		}
		
		private void setRentalPanelVisibility(boolean visibility){
			confirmButton.setVisible(visibility);
			cancelRentalButton.setVisible(visibility);
			currentBook.setVisible(visibility);
			currentBookId.setVisible(visibility);
			currentBookTitle.setVisible(visibility);
			currentBookAuthor.setVisible(visibility);
		}
		
		private void setBookToRentInfo(String id, String title, String author){
			currentBookId.setText("ID: " + id);
			currentBookTitle.setText("Title: " + title);
			currentBookAuthor.setText("Author: " + author);
		}
	}
}



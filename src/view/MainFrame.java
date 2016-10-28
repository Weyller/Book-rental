package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import controller.Controller;
import exc.UnclearedException;
import model.Book;
import model.Client;
import viewEvents.BookFormEvent;
import viewEvents.ClientFormEvent;
import viewListeners.BookFormListener;
import viewListeners.BookTableListener;
import viewListeners.ClientFormListener;
import viewListeners.ClientTableListener;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane formsTabbed;
	private JTabbedPane tablesTabbed;
	
	private ClientFormPanel clientFormPanel;
	private BookFormPanel bookFormPanel;
	
	private ClientTablePanel clientTablePanel;
	private BookTablePanel bookTablePanel;
	private RentalHistoryTablePanel rentalsTablePanel;
	private Controller controller;
	
	public MainFrame(String title){
		super(title);
		controller = new Controller();
		
		formsTabbed = new JTabbedPane();
		formsTabbed.setBorder(BorderFactory.createTitledBorder("Add"));
		
		tablesTabbed = new JTabbedPane();
		
		clientFormPanel = new ClientFormPanel();
		clientFormPanel.setClientFormListener(new ClientFormListener(){
			public void formClientEventOccured(ClientFormEvent e) {
				controller.addClient(e);
				System.out.println("Adding client");
				clientTablePanel.refresh();
			}
		});
		
		bookFormPanel = new BookFormPanel();
		bookFormPanel.setBookFormListener(new BookFormListener(){
			public void formBookEventOccured(BookFormEvent e) {
				controller.addBook(e);
				bookTablePanel.refresh();
			}
		});
		
		clientTablePanel = new ClientTablePanel();
		clientTablePanel.setData(controller.getClients());
		clientTablePanel.setClientTableListener(new ClientTableListener(){
			public void showRentedBooks(int clientRow) {
				//new tabbed with clientBooks list ??
			}
			
			public void deleteClient(int clientRow) {
				try {
					controller.removeClient(clientRow);
				} catch (UnclearedException e) {
					errorMessage("Client has some books rented. Can not delete!");
				}
				clientTablePanel.refresh();
			}
			
			public void confirmRenting(int clientRow) {
				Client c = controller.getClients().get(clientRow);
				controller.setCurrentClient(c);
				controller.proceedCurrent();
				cancelRenting();
				clientTablePanel.refresh();
				bookTablePanel.refresh();
				rentalsTablePanel.refresh();
			}
			
			public void cancelRenting() {
				clientTablePanel.setConfirmPanelVisibility(false);
				controller.clearCurrent();
			}
		});
		
		bookTablePanel = new BookTablePanel();
		bookTablePanel.setData(controller.getBooks());
		bookTablePanel.setTableListener(new BookTableListener(){
			public void deleteBook(int index) {
				try {
					controller.removeBook(index);
				} catch (UnclearedException e) {
					errorMessage("Book is rented. Can not delete!");
				}
				bookTablePanel.refresh();
			}
			public void rentBook(int index) {
				Book b = controller.getBooks().get(index);
				if(b.getIsRented() == true){
					errorMessage("Book is not available!");
					return;
				}
				controller.setCurrentBook(b);
				tablesTabbed.setSelectedIndex(0);
				instructUser("Choose client and press confirm button.");
				clientTablePanel.setBookToRentInfo(String.valueOf(b.getID()), b.getTitle(), b.getAuthor());
				clientTablePanel.setConfirmPanelVisibility(true);
			}
		});
		
		rentalsTablePanel = new RentalHistoryTablePanel();
		rentalsTablePanel.setData(controller.getRentals());
		
		formsTabbed.add("Client", clientFormPanel);
		formsTabbed.addTab("Book", bookFormPanel);
		
		tablesTabbed.add("Clients", clientTablePanel);
		tablesTabbed.add("Books", bookTablePanel);
		tablesTabbed.add("History", rentalsTablePanel);
		
		this.add(formsTabbed, BorderLayout.WEST);
		this.add(tablesTabbed, BorderLayout.CENTER);
	}
	
	private void errorMessage(String message){
		JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
	}
	
	private void instructUser(String message){
		JOptionPane.showMessageDialog(null, message, "Follow instruction", JOptionPane.INFORMATION_MESSAGE);
	}
}

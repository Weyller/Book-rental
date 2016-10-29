package view;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import controller.Controller;
import exc.BookNotFoundException;
import exc.UnclearedException;
import model.Book;
import model.Client;
import utils.FileFilterBookRental;
import viewEvents.BookFormEvent;
import viewEvents.ClientFormEvent;
import viewListeners.BookFormListener;
import viewListeners.BookTableListener;
import viewListeners.ClientFormListener;
import viewListeners.ClientRentalsTableListener;
import viewListeners.ClientTableListener;
import viewListeners.MainBarListener;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private MainMenuBar mainBar;
	private JFileChooser fileChooser;
	
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
		
		initiateFileChooser();
		mainBar = new MainMenuBar();
		mainBar.setMainBarListener(new MainBarListener(){
			public void exit() {
				int userChoice = JOptionPane.showConfirmDialog(null, "Really wanna leave?", "Exit", JOptionPane.YES_NO_OPTION);
				if(userChoice == 0){
					System.exit(0);
				}
				else{
					return;
				}
			}
			public void loadFromFile() {
				if(fileChooser.showOpenDialog(mainBar.getParent()) == JFileChooser.APPROVE_OPTION){
					controller.loadFile(fileChooser.getSelectedFile());
				}
				refreshAllTables();
			}
			public void saveToFile() {
				if(fileChooser.showSaveDialog(mainBar.getParent()) == JFileChooser.APPROVE_OPTION){
					controller.saveToFile(fileChooser.getSelectedFile());
				}
			}
			public void about() {
				JOptionPane.showMessageDialog(null, controller.getAboutMessage(), "about", JOptionPane.PLAIN_MESSAGE);
			}
		});
		setJMenuBar(mainBar);
		
		formsTabbed = new JTabbedPane();
		formsTabbed.setBorder(BorderFactory.createTitledBorder("Add"));
		
		tablesTabbed = new JTabbedPane();
		
		clientFormPanel = new ClientFormPanel();
		clientFormPanel.setClientFormListener(new ClientFormListener(){
			public void formClientEventOccured(ClientFormEvent e) {
				controller.addClient(e);
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
				ClientRentalsTablePanel clientBooks = new ClientRentalsTablePanel();
				Client c = controller.getClients().get(clientRow);
				controller.setCurrentClient(c);
				if(controller.getCurrentClient().getListOfRentedBooks() == null){
					System.out.println("Null list. Closing.");
					return;
				}
				clientBooks.setCurrentClientData(String.valueOf(c.getID()), c.getFirstName() + " " + c.getLastName());
				clientBooks.setClientBooksData(c.getListOfRentedBooks());
				
				tablesTabbed.add(String.valueOf("client" + c.getID()), clientBooks);
				tablesTabbed.setSelectedIndex(tablesTabbed.getTabCount() - 1);
				
				clientBooks.setListener(new ClientRentalsTableListener(){
					public void returnBook(int rowIndex) {
						try{
							c.returnBook(c.getListOfRentedBooks().get(rowIndex));
						}
						catch(BookNotFoundException e){
							errorMessage("Book not found.");
						}
						clientBooks.refresh();
						refreshAllTables();
					}
					
					public void closePanel() {
						tablesTabbed.remove(tablesTabbed.getSelectedIndex());
						controller.clearCurrent();
					}
				});
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
				controller.proceedRentingCurrents();
				cancelRenting();
				refreshAllTables();
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
				clientTablePanel.setBookToRentInfo(String.valueOf(b.getID()), b.getTitle(), b.getAuthor());
				clientTablePanel.setConfirmPanelVisibility(true);
				instructUser("Choose client and press confirm button.");
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
	
	private void initiateFileChooser(){
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileFilterBookRental());
		fileChooser.setCurrentDirectory(new File("./resource/savedData"));
	}
	
	private void refreshAllTables(){
		clientTablePanel.refresh();
		bookTablePanel.refresh();
		rentalsTablePanel.refresh();
	}
}

package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import controller.Controller;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane formsTabbed;
	private JTabbedPane tablesTabbed;
	
	private ClientFormPanel clientFormPanel;
	private BookFormPanel bookFormPanel;
	
	private ClientTablePanel clientTablePanel;
	private Controller controller;
	
	public MainFrame(String title){
		super(title);
		controller = new Controller();
		
		formsTabbed = new JTabbedPane();
		formsTabbed.setBorder(BorderFactory.createTitledBorder("Add"));
		tablesTabbed = new JTabbedPane();
		
		clientFormPanel = new ClientFormPanel();
		formsTabbed.add("Client", clientFormPanel);
		
		bookFormPanel = new BookFormPanel();
		formsTabbed.addTab("Book", bookFormPanel);
		
		clientTablePanel = new ClientTablePanel();
		clientTablePanel.setData(controller.getClients());
		tablesTabbed.add("Clients", clientTablePanel);
		
		this.add(formsTabbed, BorderLayout.WEST);
		this.add(tablesTabbed, BorderLayout.CENTER);
	}
}

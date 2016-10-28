package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import viewEvents.ClientFormEvent;
import viewListeners.ClientFormListener;

public class ClientFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ClientFormListener formListener;
	
	private JLabel firstNameLabel;
	private JTextField firstNameField;
	private JLabel lastNameLabel;
	private JTextField lastNameField;
	private JButton addClientBttn;
	
	public ClientFormPanel(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		
		firstNameLabel = new JLabel("First name:");
		firstNameField = new JTextField(15);
		
		lastNameLabel = new JLabel("Last name:");
		lastNameField = new JTextField(15);
		
		addClientBttn = new JButton("Add");
		addClientBttn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = firstNameField.getText();
				String lName = lastNameField.getText();
				ClientFormEvent event = new ClientFormEvent(e, fName, lName);
				if(formListener != null){
					formListener.formClientEventOccured(event);
				}
			}
		});
		
		Insets labels = new Insets(7,0,0,5);
		Insets none = new Insets(7,0,0,0);
		
		grid.anchor = GridBagConstraints.LINE_START;
		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = labels;
		this.add(firstNameLabel, grid);
		grid.gridx = 1;
		grid.insets = none;
		this.add(firstNameField, grid);
		
		grid.gridx = 0;
		grid.gridy = 1;
		grid.insets = labels;
		this.add(lastNameLabel, grid);
		grid.gridx = 1;
		grid.insets = none;
		this.add(lastNameField, grid);
		
		grid.gridx = 0;
		grid.gridy = 2;
		grid.gridwidth = 2;
		grid.anchor = GridBagConstraints.CENTER;
		this.add(addClientBttn, grid);
		
	}
	
	public void setClientFormListener(ClientFormListener listener){
		this.formListener = listener;
	}
}

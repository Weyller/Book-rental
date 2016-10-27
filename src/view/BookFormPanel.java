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

import viewListeners.BookFormListener;

public class BookFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BookFormListener formListener;
	
	private JLabel titleLabel;
	private JTextField titleField;
	private JLabel authorLabel;
	private JTextField authorField;
	private JButton addBookBtn;
	
	public BookFormPanel(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		
		titleLabel = new JLabel("Title:");
		titleField = new JTextField(15);
		
		authorLabel = new JLabel("Author:");
		authorField = new JTextField(15);
		
		addBookBtn = new JButton("Add");
		addBookBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = titleField.getText();
				String author = authorField.getText();
				BookFormEvent event = new BookFormEvent(e, title, author);
				if(formListener != null){
					formListener.formBookEventOccured(event);
				}
			}
		});
		
		Insets labels = new Insets(7,0,0,5);
		Insets none = new Insets(7,0,0,0);
		
		grid.anchor = GridBagConstraints.LINE_START;
		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = labels;
		this.add(titleLabel, grid);
		grid.gridx = 1;
		grid.insets = none;
		this.add(titleField, grid);
		
		grid.gridx = 0;
		grid.gridy = 1;
		grid.insets = labels;
		this.add(authorLabel, grid);
		grid.gridx = 1;
		grid.insets = none;
		this.add(authorField, grid);
		
		grid.gridx = 0;
		grid.gridy = 2;
		grid.gridwidth = 2;
		grid.anchor = GridBagConstraints.CENTER;
		this.add(addBookBtn, grid);
	}
	
	public void setBookFormListener(BookFormListener listener){
		this.formListener = listener;
	}
}

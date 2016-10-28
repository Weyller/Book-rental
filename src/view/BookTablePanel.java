package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Book;
import viewListeners.BookTableListener;
import viewTableModels.BookTableModel;

public class BookTablePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private BookTableModel model;
	private BookTableListener listener;
	private JTable bookTable;
	
	public BookTablePanel(){
		model = new BookTableModel();
		bookTable = new JTable(model);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(bookTable), BorderLayout.CENTER);
		add(new RightBookPanel(), BorderLayout.EAST);
		
	}
	
	class RightBookPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private JButton rentBook;
		private JButton deleteBook;
		
		public RightBookPanel(){
			rentBook = new JButton("Rent book");
			rentBook.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int selected = bookTable.getSelectedRow();
					if(selected == -1){
						noBookSelectedDialog();
					}
					else if(listener != null){
						listener.rentBook(selected);
					}
				}
			});
			
			deleteBook = new JButton("Delete book");
			deleteBook.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int selected = bookTable.getSelectedRow();
					if(selected == -1){
						noBookSelectedDialog();
					}
					else if(listener != null){
						listener.deleteBook(selected);
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
			add(deleteBook, gbc);
		}
	}
	
	public void refresh(){
		model.fireTableDataChanged();
	}
	
	public void setData(List<Book>data){
		model.setData(data);
	}
	
	public void setTableListener(BookTableListener listener){
		this.listener = listener;
	}
	
	private void noBookSelectedDialog(){
		JOptionPane.showMessageDialog(null, "No row selected!", "Warning", JOptionPane.ERROR_MESSAGE);
	}
}

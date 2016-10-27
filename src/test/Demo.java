package test;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.MainFrame;

public class Demo {
	public static void main(String[]args){
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainFrame frame = new MainFrame("Book rental");
				frame.setMinimumSize(new Dimension(1200,740));
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((int)(screenSize.getWidth() - frame.getWidth())/2, (int)(screenSize.getHeight() - frame.getHeight()) / 2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		/*
		Client c = new Client("Johny", "Bravo", 12344);
		Book b1 = new Book("a", "b", 134);
		c.rentBook(b1,2);
		System.out.println(c.getNrOfRentedBooks());
		List<Book> cRented = c.getListOfRentedBooks();
		for(int i = 0; i < cRented.size(); i++){
			System.out.println(cRented.get(i).toString());
		}
		Rental r = new Rental(c, b1, new Date());
		System.out.println(r.getClientName() + " " + r.getClientID() + " " + r.getBookTitleAndAuthor() + " " + r.getBookID() + " " + r.getRentalDate());
		*/
	}
}

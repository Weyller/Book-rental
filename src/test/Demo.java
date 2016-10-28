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
	}
}

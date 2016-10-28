package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import viewListeners.MainBarListener;

public class MainMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private JMenu app;
	private JMenuItem exit;
	private JMenuItem info;
	private JMenu fileOptions;
	private JMenuItem loadFromFile;
	private JMenuItem saveToFile;
	private MainBarListener listener;
	
	public MainMenuBar(){
		fileOptions = new JMenu("File");
		
		loadFromFile = new JMenuItem("Load from file");
		loadFromFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(listener != null){
					listener.loadFromFile();
				}
			}
		});
		
		saveToFile = new JMenuItem("Save to file");
		saveToFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(listener != null){
					listener.saveToFile();
				}
			}
		});
		fileOptions.add(loadFromFile);
		fileOptions.add(saveToFile);
		
		app = new JMenu("App");
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(listener != null){
					listener.exit();
				}
			}
		});
		
		info = new JMenuItem("Info");
		info.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(listener != null){
					listener.about();
				}
			}
		});
		app.add(info);
		app.add(exit);
		
		this.add(fileOptions);
		this.add(app);
		
	}
	
	public void setMainBarListener(MainBarListener listener){
		this.listener = listener;
	}
}

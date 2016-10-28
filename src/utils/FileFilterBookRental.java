package utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class FileFilterBookRental extends FileFilter {

	@Override
	public boolean accept(File f) {
		if(f.isDirectory()){
			return true;
		}
		String name = f.getName();
		
		String extension = Utils.getFileExtension(name);
		
		if(extension == null){
			return false;
		}
		if(extension.equals("brd")){
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "book rental data (*.brd)";
	}

}

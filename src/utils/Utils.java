package utils;

public class Utils {
	public static String getFileExtension(String s){
		int lastDot = s.lastIndexOf('.');
		
		if(lastDot == -1){
			return null;
		}
		
		if(lastDot == s.length() - 1){
			return null;
		}
		
		return s.substring(lastDot + 1);
	}
}

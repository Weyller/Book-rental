package exc;

public class UnclearedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UnclearedException(){
		super();
	}
	
	public UnclearedException(String message){
		super(message);
	}
}

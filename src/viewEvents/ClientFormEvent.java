package viewEvents;

import java.util.EventObject;

public class ClientFormEvent extends EventObject{

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;

	public ClientFormEvent(Object source) {
		super(source);
	}
	
	public ClientFormEvent(Object source, String firstName, String lastName){
		this(source);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
}

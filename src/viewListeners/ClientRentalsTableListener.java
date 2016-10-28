package viewListeners;

import java.util.EventListener;

public interface ClientRentalsTableListener extends EventListener {
	void returnBook(int rowIndex);
	void closePanel();
}

package viewListeners;

import java.util.EventListener;

public interface ClientTableListener extends EventListener {
	void rowDeleted(int clientRow);
	void showRentedBooks(int clientRow);
	void rentBook(int clientRow);
	void deleteClient(int clientRow);
}

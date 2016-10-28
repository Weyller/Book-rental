package viewListeners;

import java.util.EventListener;

public interface ClientTableListener extends EventListener {
	void showRentedBooks(int clientRow);
	void deleteClient(int clientRow);
	void confirmRenting(int clientRow);
	void cancelRenting();
}

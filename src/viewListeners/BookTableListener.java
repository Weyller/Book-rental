package viewListeners;

import java.util.EventListener;

public interface BookTableListener extends EventListener{
	void deleteBook(int index);
	void rentBook(int index);
}

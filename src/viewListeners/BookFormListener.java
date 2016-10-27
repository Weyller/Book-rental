package viewListeners;

import java.util.EventListener;

import view.BookFormEvent;

public interface BookFormListener extends EventListener {
	void formBookEventOccured(BookFormEvent e);
}

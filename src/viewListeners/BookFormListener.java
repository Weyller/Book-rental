package viewListeners;

import java.util.EventListener;

import viewEvents.BookFormEvent;

public interface BookFormListener extends EventListener {
	void formBookEventOccured(BookFormEvent e);
}

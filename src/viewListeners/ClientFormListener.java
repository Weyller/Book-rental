package viewListeners;

import java.util.EventListener;

import viewEvents.ClientFormEvent;

public interface ClientFormListener extends EventListener {
	void formClientEventOccured(ClientFormEvent e);
}

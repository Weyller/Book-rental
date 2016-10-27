package viewListeners;

import java.util.EventListener;

import view.ClientFormEvent;

public interface ClientFormListener extends EventListener {
	void formClientEventOccured(ClientFormEvent e);
}

package viewListeners;

import java.util.EventListener;

public interface MainBarListener extends EventListener {
	void exit();
	void loadFromFile();
	void saveToFile();
	void about();
}

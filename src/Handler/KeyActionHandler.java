package Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyActionHandler implements KeyListener {
	
	private boolean remove;
	
	public KeyActionHandler() {
		
	}

	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.VK_1) {
			remove=true;
		}
	}

	public void keyReleased(KeyEvent event) {
		
	}
	
	public void keyTyped(KeyEvent event) {
		
	}
	
	public boolean getRemove() {
		return remove;
	}
	
}

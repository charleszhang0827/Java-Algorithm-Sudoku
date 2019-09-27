package State;

import Interface.Renderable;
import Interface.Tickable;
import Main.Display;

public abstract class State implements Tickable,Renderable {
	public static State currentState;
	
	public static void setState(State state) {
		currentState=state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	protected Display display;
	
	public State(Display display) {
		this.display=display;
	}
}

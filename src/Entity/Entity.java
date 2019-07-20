package Entity;

import java.awt.Graphics2D;

import Main.Display;

public abstract class Entity {
	
	protected Display display;
	
	protected int x;
	
	protected int y;
	
	public Entity(Display display,int x,int y) {
		this.display=display;
		this.x=x;
		this.y=y;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

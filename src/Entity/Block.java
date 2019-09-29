package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Interface.Renderable;
import Interface.Tickable;
import Main.Display;

public class Block extends Entity implements Tickable,Renderable {
	
	private int value;
	
	public Block(Display display,int x,int y,int value) {
		super(display,x,y);
		this.value=value;
	}

	public void tick() {
		if(display.getKeyActionHandler().getRemove()) {
			if(display.getMouseActionHandler().getPressed()) {
				int x=display.getMouseMotionHandler().getX();
				int y=display.getMouseMotionHandler().getY();
				
				if(x>this.x && x<this.x+display.getResolution()) {
					if(y>this.y && y<this.y+display.getResolution()) {
						display.getRunState().getEntities()[x/display.getResolution()][y/display.getResolution()]=null;
					}
				}
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x,y,display.getResolution(),display.getResolution());
			
		Font font=new Font("TimesRoman",Font.BOLD,30);
			
		Rectangle rectangle=new Rectangle(x,y,display.getResolution(),display.getResolution());
			
		this.drawCenterText(g,font,String.valueOf(value),rectangle);
	}
	
	public void setValue(int value) {
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
}

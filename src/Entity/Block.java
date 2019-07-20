package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import Main.Display;

public class Block extends Entity {
	
	private int number;
	
	private LinkedList<Integer> possibleNumbers=new LinkedList<Integer>();
	
	public Block(Display display,int x,int y,int number) {
		super(display,x,y);
		this.number=number;
		
		if(number==0) {
			for(int i=1;i<10;i++) {
				possibleNumbers.add(i);
			}
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		
		if(number!=0) {
			g.setColor(Color.CYAN);
			g.fillRect(x,y,display.getResolution(),display.getResolution());
			g.setColor(Color.BLACK);
			g.drawRect(x,y,display.getResolution(),display.getResolution());
			g.drawString(String.valueOf(number),x+display.getResolution()/2,y+display.getResolution()/2);
		}else {
			g.setColor(Color.YELLOW);
			g.fillRect(x,y,display.getResolution(),display.getResolution());
			g.setColor(Color.BLACK);
			g.drawRect(x,y,display.getResolution(),display.getResolution());
			g.drawString(String.valueOf(number),x+display.getResolution()/2,y+display.getResolution()/2);
		}
	}
	
	public int getNumber() {
		return number;
	}
	
	public void addNumber(int number) {
		possibleNumbers.add(number);
	}
	
	public void removeNumber(int number) {
		possibleNumbers.remove(number);
	}
	
	public void setPossibleNumbers(LinkedList<Integer> possibleNumbers) {
		this.possibleNumbers=possibleNumbers;
	}
	
	public LinkedList<Integer> getPossibleNumbers(){
		return possibleNumbers;
	}
}

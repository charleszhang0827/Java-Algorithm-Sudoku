package State;

import java.awt.Graphics2D;

import Core.Brain;
import Entity.Block;
import Main.Display;

public class RunState extends State {
	
	private Block[][] blocks=new Block[9][9];
	
	private Brain brain;
	
	private boolean created=false;
	
	public RunState(Display display) {
		super(display);
		
		createBlock();
	}
	
	private void createBlock() {
		for(int x=0;x<display.getData().length;x++) {
			for(int y=0;y<display.getData()[x].length;y++) {
				blocks[x][y]=new Block(display,x*display.getResolution(),y*display.getResolution(),Integer.valueOf(display.getData()[x][y]));
			}
		}
	}
	
	public void tick() {
		if(display.getStartThinking()==true) {
			if(!created) {
				brain=new Brain(display);
				brain.start();
				created=true;
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(int x=0;x<blocks.length;x++) {
			for(int y=0;y<blocks[x].length;y++) {
				blocks[x][y].render(g);
			}
		}
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
}

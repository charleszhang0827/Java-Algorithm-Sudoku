package State;

import java.awt.Color;
import java.awt.Graphics2D;

import Entity.Block;
import Main.Display;
import Runner.Maker.DFSMaker;
import Runner.Solver.DFSSolver;

public class RunState extends State {
	
	private boolean DFSMaking;
	
	private DFSMaker dFSMaker;
	
	private boolean DFSSolving;
	
	private DFSSolver dFSSolver;
	
	private Block[][] blocks;
	
	public RunState(Display display) {
		super(display);
		
		createObject();
	}
	
	private void createObject() {
		blocks=new Block[display.getWidth()/display.getResolution()][display.getHeight()/display.getResolution()];
		
		for(int x=0;x<blocks.length;x++) {
			for(int y=0;y<blocks[x].length;y++) {
				blocks[x][y]=new Block(display,x*display.getResolution(),y*display.getResolution(),0);
			}
		}
	}
	
	public void tick() {
		DFSMakerTick();
		
		DFSSolverTick();
		
		for(int x=0;x<blocks.length;x++) {
			for(int y=0;y<blocks[x].length;y++) {
				blocks[x][y].tick();
			}
		}
	}
	
	private void DFSMakerTick() {
		if(DFSMaking==true) {
			dFSMaker=new DFSMaker(display);
			dFSMaker.start();
			DFSMaking=false;
		}
	}
	
	private void DFSSolverTick() {
		if(DFSSolving==true) {
			dFSSolver=new DFSSolver(display);
			dFSSolver.start();
			DFSSolving=false;
		}
	}
	
	public void render(Graphics2D g) {
		for(int x=0;x<blocks.length;x++) {
			for(int y=0;y<blocks[x].length;y++) {
				blocks[x][y].render(g);
			}
		}
		
		for(int x=0;x<display.getWidth()/display.getResolution();x++) {
			for(int y=0;y<display.getHeight()/display.getResolution();y++) {
				int resolution=display.getResolution();
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x*resolution,y*resolution,resolution,resolution);
			}
		}
	}
	
	public void setDFSMaking(boolean DFSMaking) {
		this.DFSMaking=DFSMaking;
	}
	
	public boolean getDFSMaking() {
		return DFSMaking;
	}
	
	public void setDFSolving(boolean DFSSolving) {
		this.DFSSolving=DFSSolving;
	}
	
	public boolean getDFSSolving() {
		return DFSSolving;
	}
	
	public Block[][] getBlocks() {
		return blocks;
	}
	
	public void reset() {
		for(int x=0;x<blocks.length;x++) {
			for(int y=0;y<blocks[x].length;y++) {
				blocks[x][y].setValue(0);
			}
		}
		
		if(dFSMaker!=null) {
			dFSMaker.stop();
			dFSMaker=null;
		}
		
		if(dFSSolver!=null) {
			dFSSolver.stop();
			dFSSolver=null;
		}
	}
}

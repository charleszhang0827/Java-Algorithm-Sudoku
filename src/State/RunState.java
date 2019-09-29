package State;

import java.awt.Color;
import java.awt.Graphics2D;

import Entity.Block;
import Entity.Entity;
import Main.Display;
import Runner.Maker.DFSMaker;
import Runner.Solver.DFSSolver;

public class RunState extends State {
	
	private boolean DFSMaking;
	
	private DFSMaker dFSMaker;
	
	private boolean DFSSolving;
	
	private DFSSolver dFSSolver;
	
	private Entity[][] entities;
	
	public RunState(Display display) {
		super(display);
		
		createObject();
	}
	
	private void createObject() {
		entities=new Entity[display.getWidth()/display.getResolution()][display.getHeight()/display.getResolution()];
	}
	
	public void tick() {
		DFSMakerTick();
		
		DFSSolverTick();
		
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				if(entities[x][y] instanceof Block) {
					((Block)(entities[x][y])).tick();
				}
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
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				if(entities[x][y] instanceof Block) {
					((Block)(entities[x][y])).render(g);
				}
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
	
	public Entity[][] getEntities() {
		return entities;
	}
	
	public void reset() {
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				entities[x][y]=null;
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

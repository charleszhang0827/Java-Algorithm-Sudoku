package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import Handler.ComponentActionHandler;
import Handler.KeyActionHandler;
import Handler.MouseActionHandler;
import Handler.MouseMotionHandler;
import State.RunState;
import State.State;

public class Display implements Runnable {
	
	private Thread thread;
	
	private Window window;
	
	private boolean running;
	
	private double tpt=1000000000/60;
	
	private BufferStrategy bs;
	
	private Graphics g;
	
	private int resolution=90;
	
	private ComponentActionHandler componentActionHandler;
	
	private KeyActionHandler keyActionHandler;
	
	private MouseActionHandler mouseActionHandler;
	
	private MouseMotionHandler mouseMotionHandler;
	
	private RunState runState;
	
	public Display(Window window) {
		thread=new Thread(this);
		
		this.window=window;
		
		createHandler();
		
		createState();
	}
	
	private void createHandler() {
		componentActionHandler=new ComponentActionHandler(this);
		window.getTextField().addActionListener(componentActionHandler);
		
		keyActionHandler=new KeyActionHandler();
		window.getCanvas().addKeyListener(keyActionHandler);
		
		mouseActionHandler=new MouseActionHandler();
		window.getCanvas().addMouseListener(mouseActionHandler);
		
		mouseMotionHandler=new MouseMotionHandler();
		window.getCanvas().addMouseMotionListener(mouseMotionHandler);
	}
	
	private void createState() {
		runState=new RunState(this);
		State.setState(runState);
	}
	
	public synchronized void start() {
		running=true;
		thread.start();
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void stop() {
		running=false;
		thread.stop();
	}
	
	public void run() {
		
		double delta=0;
		
		long last=System.nanoTime();
		
		long now;
		
		while(running) {
			now=System.nanoTime();
			delta+=(now-last)/tpt;
			last=now;
			
			if(delta>=1) {
				tick();
				render();
				delta--;
			}
		}
	}
	
	private void tick() {
		if(State.getState()==runState) {
			runState.tick();
		}
	}
	
	private void render() {
		bs=window.getCanvas().getBufferStrategy();
		
		if(bs==null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g=bs.getDrawGraphics();
		
		g.clearRect(0,0,window.getWidth(),window.getHeight());
		
		if(State.getState()==runState) {
			runState.render((Graphics2D)g);
		}
		
		bs.show();
		g.dispose();
	}
	
	public Window getWindow() {
		return window;
	}
	
	public int getWidth() {
		return window.getWidth();
	}
	
	public int getHeight() {
		return window.getHeight();
	}
	
	public void setResolution(int resolution) {
		this.resolution=resolution;
	}
	
	public int getResolution() {
		return resolution;
	}
	
	public ComponentActionHandler getComponentActionHandler() {
		return componentActionHandler;
	}
	
	public KeyActionHandler getKeyActionHandler() {
		return keyActionHandler;
	}
	
	public MouseActionHandler getMouseActionHandler() {
		return mouseActionHandler;
	}
	
	public MouseMotionHandler getMouseMotionHandler() {
		return mouseMotionHandler;
	}
	
	public RunState getRunState() {
		return runState;
	}
}

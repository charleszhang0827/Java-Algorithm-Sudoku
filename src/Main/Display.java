package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.Scanner;

import State.RunState;
import State.State;

public class Display implements Runnable,ActionListener {
	
	private Thread thread;
	
	private boolean running;
	
	private Window window;
	
	private double tpt=1000000000/60;
	
	private BufferStrategy bs;
	
	private Graphics g;
	
	private final int resolution=80;
	
	private Scanner scan;
	
	private String[][] data;
	
	private boolean startThinking;
	
	private RunState runState;
	
	public Display(Window window,String path) {
		thread=new Thread(this);
		this.window=window;
		data=new String[9][9];
		readData(path);
		createHandler();
		createState();
	}
	
	private void readData(String path) {
		int index=0;
		try {
		
			scan=new Scanner(new File(path));
			
			
			while(scan.hasNextLine()) {
				String[] thing=scan.nextLine().split(",");
				for(int i=0;i<thing.length;i++) {
					data[index][i]=thing[i];
				}
				
				index++;
			}
		}catch(Exception exception) {
			System.out.println("Cannot find file");
		}
	}
	
	private void createHandler() {
		window.getTextField().addActionListener(this);
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
		
		if(State.getState()==runState) {
			runState.render((Graphics2D)g);
		}
		
		bs.show();
		g.dispose();
	}
	
	public int getWidth() {
		return window.getWidth();
	}
	
	public int getHeight() {
		return window.getHeight();
	}
	
	public int getResolution() {
		return resolution;
	}
	
	public String[][] getData() {
		return data;
	}
	
	public RunState getRunState() {
		return runState;
	}
	
	public void actionPerformed(ActionEvent event) {
		if(window.getTextField().getText().equalsIgnoreCase("start")) {
			startThinking=true;
		}
	}
	
	public boolean getStartThinking() {
		return startThinking;
	}
}

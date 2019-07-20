package Core;

import java.util.ArrayList;

import Entity.Block;
import Main.Display;

public class Brain implements Runnable {
	
	private Thread thread;
	
	private Display display;
	
	private Block[][] blocks;
	
	public Brain(Display display) {
		thread=new Thread(this);
		this.blocks=display.getRunState().getBlocks();
		this.display=display;
	}
	
	public synchronized void start() {
		thread.start();
	}
	
	public void run() {
		for(int x=0;x<blocks.length;x++) {
			for(int y=0;y<blocks[x].length;y++) {
				ArrayList<Integer> columnList=columnLeft(blocks[x][y]);
			
				System.out.println("At "+blocks[x][y].getX()+"/"+blocks[x][y].getY());
				
				print("Column",columnList);
				
				try {
					Thread.sleep(2000);
				} catch (Exception exception) {}
			}
		}
	}
	
	private ArrayList<Integer> columnLeft(Block block) {
		
		ArrayList<Integer> thing=new ArrayList<Integer>();
		
		int column=block.getY()/display.getResolution();
		
		for(int i=0;i<9;i++) {
			
			int number=blocks[column][i].getNumber();
			
			thing.add(number);
		}
		return thing;
	}
	
	private void print(String prefix,ArrayList<Integer> list) {
		System.out.print("\n"+prefix+": [");
		
		for(int i=0;i<list.size();i++) {
			if(i!=list.size()-1) {
				System.out.print(i+",");
			}else {
				System.out.print(i);
			}
		}
		
		System.out.println("]");
	}
	
}

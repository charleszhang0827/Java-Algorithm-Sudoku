package Runner.Solver;

import java.util.ArrayList;
import java.util.Stack;

import Main.Display;
import Runner.Runner;

public class DFSSolver extends Runner {
	
	private Thread thread;
	
	private int[][] map;
	
	private ArrayList<int[][]> records=new ArrayList<int[][]>();
	
	private Stack<int[]> stacks=new Stack<int[]>();
	
	public DFSSolver(Display display) {
		super(display);
		
		thread=new Thread(this);
	}
	
	public synchronized void start() {
		thread.start();
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void stop() {
		thread.stop();
	}
	
	public void run() {
		map=new int[display.getRunState().getBlocks().length][display.getRunState().getBlocks().length];
		
		
		for(int x=0;x<map.length;x++) {
			for(int y=0;y<map[x].length;y++) {
				map[x][y]=display.getRunState().getBlocks()[y][x].getValue();
			}
		}
		
		int x=0; int y=0;
		
		while(true) {
			
			try {
				if(map[x][y]==0) {
					
				}
			}catch(Exception e) {
				break;
			}
			
			if(map[x][y]==0) {
			
				ArrayList<Integer> fits=getPossibleFits(x,y);
			
				if(fits.size()!=0) {
					map[x][y]=chooseRandomFit(fits);
					
					stacks.add(new int[] {x,y});
					
					if(y==map.length-1) {
						y=0;
						x++;
					}else {
						y++;
					}	
				}else {
					
					records.add(record());
					
					x=stacks.peek()[0];
					
					y=stacks.peek()[1];
					
					stacks.pop();
					
					map[x][y]=0;
			}
			
			}else {
				if(y==map.length-1) {
					y=0;
					x++;
				}else {
					y++;
				}
			}
			
		}
		
		for(int q=0;q<map.length;q++) {
			for(int w=0;w<map[q].length;w++) {
				display.getRunState().getBlocks()[q][w].setValue(map[w][q]);
			}
		}
		stop();
		
	}
	
	private int[][] record() {
		int[][] array=new int[map.length][map.length];
		
		for(int x=0;x<array.length;x++) {
			for(int y=0;y<array[x].length;y++) {
				array[x][y]=map[x][y];
			}
		}
		
		return array;
	}
	
	private ArrayList<Integer> getPossibleFits(int x,int y) {
		ArrayList<Integer> fits=new ArrayList<Integer>();
		
		for(int i=1;i<map.length+1;i++) {
			fits.add(i);
		}
		
		for(int i=0;i<map.length;i++) {
			if(fits.contains(map[x][i])) {
				fits.remove(fits.indexOf(map[x][i]));
			}
		}
		
		for(int i=0;i<map.length;i++) {
			if(fits.contains(map[i][y])) {
				fits.remove(fits.indexOf(map[i][y]));
			}
		}
		
		int xPosition=(x/3)*3+3;
		int yPosition=(y/3)*3+3;
		
		for(int q=xPosition-3;q<xPosition;q++) {
			for(int w=yPosition-3;w<yPosition;w++) {
				if(fits.contains(map[q][w])) {
					fits.remove(fits.indexOf(map[q][w]));
				}
			}
		}
		
		for(int i=1;i<map.length+1;i++) {
			
			if(fits.contains(i)) {
				
				map[x][y]=i;
				
				for(int q=0;q<records.size();q++) {
					if(isSame(records.get(q),record())) {
						fits.remove(fits.indexOf(i));
						break;
					}
				}
			}
		}
		
		map[x][y]=0;
		
		return fits;
	}
	
	private int chooseRandomFit(ArrayList<Integer> fits) {
		return fits.get((int)(Math.random()*fits.size()));
	}
	
	private boolean isSame(int[][] one,int [][] two) {
		for(int x=0;x<one.length;x++) {
			for(int y=0;y<one[x].length;y++) {
				if(one[x][y]!=two[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
}

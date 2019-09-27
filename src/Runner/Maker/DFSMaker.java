package Runner.Maker;

import java.util.ArrayList;

import Entity.Block;
import Main.Display;
import Runner.Runner;

public class DFSMaker extends Runner {
	
	private Thread thread;
	
	private int[][] map;
	
	private ArrayList<int[][]> thing=new ArrayList<int[][]>();
	
	public DFSMaker(Display display) {
		super(display);
		
		thread=new Thread(this);
		
		createObject();
	}
	
	private void createObject() {
		map=new int[display.getWidth()/display.getResolution()][display.getHeight()/display.getResolution()];
		
		for(int x=0;x<map.length;x++) {
			for(int y=0;y<map.length;y++) {
				map[x][y]=0;
			}
		}
	}
	
	public synchronized void start() {
		thread.start();
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void stop() {
		thread.stop();
	}
	
	public void run() {
		int x=0; int y=0;
		
		while(x<=map.length-1 || y<=map.length-1) {
			
			ArrayList<Integer> fits;
			
			try {
				fits=getPossibleFits(x,y);
			}catch(Exception exception) {
				break;
			}
			
			if(fits.size()!=0) {
				map[x][y]=chooseFit(fits);
				
				if(y==map.length-1) {
					y=0;
					x++;
				}else {
					y++;
				}	
			}else {
				thing.add(record());
				
				if(y==0) {
					x--;
					y=map.length-1;
				}else {
					y--;
				}
				
				map[x][y]=0;
			}
		}
		
		Block[][] blocks=display.getRunState().getBlocks();
		
		for(int w=0;w<blocks.length;w++) {
			for(int q=0;q<blocks[w].length;q++) {
				blocks[w][q].setValue(map[q][w]);
			}
		}
		
		stop();
	}
	
	private int[][] record() {
		int[][] record=new int[map.length][map.length];
		
		for(int x=0;x<record.length;x++) {
			for(int y=0;y<record[x].length;y++) {
				record[x][y]=map[x][y];
			}
		}
		return record;
	}
	
	public boolean isSame(int[][] one,int[][] two) {
		for(int x=0;x<one.length;x++) {
			for(int y=0;y<one[x].length;y++) {
				if(one[x][y]!=two[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private ArrayList<Integer> getPossibleFits(int x,int y) {
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		for(int i=1;i<map.length+1;i++) {
			list.add(i);
		}
		
		for(int i=0;i<map.length;i++) {
			if(list.contains(map[x][i])) {
				list.remove(list.indexOf(map[x][i]));
			}
			
			if(list.contains(map[i][y])) {
				list.remove(list.indexOf(map[i][y]));
			}
		}
		
		int xNumber=(x/3)*3+3;
		int yNumber=(y/3)*3+3;
		
		for(int w=xNumber-3;w<xNumber;w++) {
			for(int q=yNumber-3;q<yNumber;q++) {
				if(list.contains(map[w][q])) {
					list.remove(list.indexOf(map[w][q]));
				}
			}
		}
		
		for(int i=1;i<10;i++) {
			if(list.contains(i)) {
				map[x][y]=i;
				
				for(int w=0;w<thing.size();w++) {
					if(isSame(record(),thing.get(w))) {
						list.remove(list.indexOf(i));
						break;
					}
				}
			}
		}
		
		map[x][y]=0;
		
		return list;
	}
	
	private int chooseFit(ArrayList<Integer> list) {
		return list.get((int)(list.size()*Math.random()));
	}
}

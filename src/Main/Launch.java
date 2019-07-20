package Main;

public class Launch {
	
	private final String title="Sudoku Solver";
	
	private String path="/Users/tengdajiang/Desktop/test.txt";
	
	private final int width=720;
	
	private final int height=720;
	
	public Window window;
	
	private Display display;
	
	public static void main(String[]args) {
		new Launch();
	}
	
	public Launch() {
		window=new Window(title,width,height);
		display=new Display(window,path);
		display.start();
	}
}

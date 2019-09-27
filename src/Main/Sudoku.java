package Main;

public class Sudoku {
	private final String title="Sudoku";
	
	private final int width=810;
	
	private final int height=810;
	
	public Window window;
	
	private Display display;
	
	public static void main(String[]args) {
		new Sudoku();
	}
	
	private Sudoku() {
		window=new Window(title,width,height);
		display=new Display(window);
		display.start();
	}
}

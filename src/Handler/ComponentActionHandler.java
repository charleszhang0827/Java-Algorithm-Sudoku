package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entity.Block;
import Entity.Entity;
import Main.Display;

public class ComponentActionHandler implements ActionListener {

	private Display display;
	
	private Scanner scanner;
	
	public ComponentActionHandler(Display display) {
		this.display=display;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		JTextField textField=display.getWindow().getTextField();
		
		if(textField.getText().equals("make")) {
			display.getRunState().reset();
			display.getRunState().setDFSMaking(true);
			textField.setText("");
		}else if(textField.getText().equals("solve")) {
			display.getRunState().setDFSolving(true);
			textField.setText("");
		}else if(textField.getText().equals("reset")) {
			display.getRunState().reset();
			textField.setText("");
		}else if(textField.getText().equals("import")) {
			String directory=JOptionPane.showInputDialog(display.getWindow().getFrame(),"Directory");
			
			importMap(directory);
			
		}
	}
	
	private void importMap(String directory) {
		
		String[][] data=new String[9][9];
		
		int index=0;
		
		try {
		
			scanner=new Scanner(new File(directory));
			
			while(scanner.hasNextLine()) {
				data[index]=scanner.nextLine().split(",");
				index++;
			}
			
		}catch(Exception exception) {}
		
		Entity[][] entities=display.getRunState().getEntities();
		
		for(int x=0;x<entities.length;x++) {
			for(int y=0;y<entities[x].length;y++) {
				entities[x][y]=new Block(display,x*display.getResolution(),y*display.getResolution(),Integer.valueOf(data[y][x]));
			}
		}
		
	}
}

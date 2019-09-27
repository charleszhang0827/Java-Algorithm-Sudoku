package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import Main.Display;

public class ComponentActionHandler implements ActionListener {

	private Display display;
	
	public ComponentActionHandler(Display display) {
		this.display=display;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		JTextField textField=display.getWindow().getTextField();
		
		if(textField.getText().equals("make")) {
			display.getRunState().setDFSMaking(true);
			textField.setText("");
		}else if(textField.getText().equals("solve")) {
			display.getRunState().setDFSolving(true);
			textField.setText("");
		}else if(textField.getText().equals("reset")) {
			display.getRunState().reset();
			textField.setText("");
		}
	}
}

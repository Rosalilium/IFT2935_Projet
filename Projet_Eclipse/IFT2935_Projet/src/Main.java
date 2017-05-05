import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import gui.*;

// Notre main, démarre l'application et crée le JFrame.

public class Main {
	
	public static void main(String[] args){
		
		UIManager.put("Label.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 18)));
	    UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.BOLD, 18)));
	    UIManager.put("TextField.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 18)));
	    UIManager.put("PasswordField.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 18)));
	    UIManager.put("TabbedPane.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 18)));
	    UIManager.put("ComboBox.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 18)));
	    UIManager.put("Table.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 18)));

		JFrame frame = new JFrame("Projet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MainPanel(frame));
		frame.setSize(new Dimension(800, 600));;
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}

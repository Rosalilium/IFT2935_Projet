package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Panneau de login, à afficher au départ de l'application.

public class LoginPanel extends JPanel {
	
	private JLabel usernameLabel = new JLabel("Pseudonyme");
	private JLabel passwordLabel = new JLabel("Mot de passe");
	private JTextField username;
	private JPasswordField password;
	private JButton send = new JButton("Soumettre");
	private MainPanel mainpanel;
	
	public LoginPanel(MainPanel panel){
		mainpanel = panel;

		username = new JTextField(20);
		password = new JPasswordField(20);
		
		JPanel pan1 = new JPanel();
		pan1.setLayout(new BoxLayout(pan1, BoxLayout.PAGE_AXIS));
		pan1.add(usernameLabel);
		pan1.add(username);
		
		JPanel pan2 = new JPanel();
		pan2.setLayout(new BoxLayout(pan2, BoxLayout.PAGE_AXIS));
		pan2.add(passwordLabel);
		pan2.add(password);
		pan2.setBorder(new EmptyBorder(10, 0, 20, 0));
		
		JPanel pan3 = new JPanel();
		send.setAlignmentX(RIGHT_ALIGNMENT);
		pan3.add(send);
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(300, 200));
		this.add(pan1);
		this.add(pan2);
		this.add(pan3);
		
		send.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainpanel.checkUserPassword(username.getText(), String.valueOf(password.getPassword()));
			}
		});
		
	}

}

package gui;

import java.awt.*;

import javax.swing.*;

// Panneau principal, contiendra les tabs.
// Contiendra une instance de la classe SQLHelper (ou la classe qui fait le lien avec
// la base de données).
// On appelera d'ici les méthodes correspondantes aux différentes requêtes qui seront 
// accessible par SQLHelper.

public class MainPanel extends JPanel {
	
	private LoginPanel login;
	private JTabbedPane tabs;
	private JFrame frame;
	
	public MainPanel(JFrame f){
		frame = f;
		
		login = new LoginPanel(this);
		
		tabs = new JTabbedPane();
		tabs.add("Acheteur", new JScrollPane(new AcheteurPanel(this)));
		tabs.addTab("Vendeur", new JScrollPane(new VendeurPanel(this)));
		tabs.add("Expert", new JScrollPane(new ExpertPanel(this)));
		
		this.setPreferredSize(new Dimension(950, 900));
		this.add(login);
		
	}
	
	public void checkUserPassword(String username, String password){
		this.removeAll();
		
		this.setLayout(new BorderLayout());
		this.add(tabs, BorderLayout.CENTER);
		
		frame.revalidate();
		frame.repaint();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}

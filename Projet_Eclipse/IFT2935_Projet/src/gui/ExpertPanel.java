package gui;

import java.awt.Dimension;

import javax.swing.*;

// Panneau contenant l'affichage pour la vue Expert.

public class ExpertPanel extends JPanel {

	private JLabel objects = new JLabel("Objets à expertisés");
	private JScrollPane objectsScroll;
	private JTable objectResult;
	private JLabel historique = new JLabel("Historique");
	private JScrollPane histoScroll;
	private JTable histoResult;
	private MainPanel mainpanel;
	
	public ExpertPanel(MainPanel panel){
		mainpanel = panel;
		
		objectResult = new JTable();
		objectsScroll = new JScrollPane(objectResult);
		objectsScroll.setPreferredSize(new Dimension(500,150));
		
		histoResult = new JTable();
		histoScroll = new JScrollPane(histoResult);
		histoScroll.setPreferredSize(new Dimension(500,150));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(objects);
		this.add(objectsScroll);
		this.add(historique);
		this.add(histoScroll);
	}
}

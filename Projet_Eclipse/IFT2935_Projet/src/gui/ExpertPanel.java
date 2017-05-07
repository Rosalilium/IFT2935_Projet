package gui;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sql.ResultTableModel;

// Panneau contenant l'affichage pour la vue Expert.

public class ExpertPanel extends JPanel {

	private JLabel objects = new JLabel("Produits a expertises");
	private JScrollPane objectsScroll;
	private JTable objectResult;
	private JLabel historique = new JLabel("Historique");
	private JScrollPane histoScroll;
	private JTable histoResult;
	private MainPanel mainpanel;
	
	public ExpertPanel(MainPanel panel){
		mainpanel = panel;
		
		JPanel oPanel = makeItemPanel();
		JPanel hPanel = makeHistoPanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(oPanel);
		this.add(hPanel);
	}
	
	private JPanel makeItemPanel(){
		JPanel tPanel = new JPanel();
		tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.PAGE_AXIS));
		
		ResultTableModel res = mainpanel.getProductsToEstimate();
		objectResult = new JTable(res);
		objectsScroll = new JScrollPane(objectResult);
		objectsScroll.setPreferredSize(new Dimension(300,100));
		
		tPanel.add(objects);
		tPanel.add(objectsScroll);
		tPanel.setBorder(new EmptyBorder(10,10,10,10));
		return tPanel;
	}
	
	private JPanel makeHistoPanel(){
		JPanel tPanel = new JPanel();
		tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.PAGE_AXIS));
		
		ResultTableModel res = mainpanel.getExpertHistorique();
		histoResult = new JTable(res);
		histoScroll = new JScrollPane(histoResult);
		histoScroll.setPreferredSize(new Dimension(300,100));
		
		tPanel.add(historique);
		tPanel.add(histoScroll);
		tPanel.setBorder(new EmptyBorder(10,10,10,10));
		return tPanel;
	}
}

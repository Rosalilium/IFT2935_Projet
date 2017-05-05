package gui;

import javax.swing.*;

// Panneau contennant les éléments pour la vue Vendeur.

public class AnnonceurPanel extends JPanel{
	
	private MainPanel mainpanel;
	private JLabel vente = new JLabel("En vente");
	private JLabel offers = new JLabel("Offres");
	private JLabel historique = new JLabel("Historique des ventes");
	private JTable venteResult;
	private JTable offerResult;
	private JTable histoResult;
	private JScrollPane venteScroll;
	private JScrollPane offerScroll;
	private JScrollPane histoScroll;
	private JLabel warning;

	public AnnonceurPanel(MainPanel panel){
		mainpanel = panel;
		
		JPanel vPanel = new JPanel();
		vPanel.setLayout(new BoxLayout(vPanel, BoxLayout.PAGE_AXIS));
		venteResult = new JTable();
		venteScroll = new JScrollPane(venteResult);
		vPanel.add(vente);
		vPanel.add(venteScroll);
		
		JPanel oPanel = new JPanel();
		oPanel.setLayout(new BoxLayout(oPanel, BoxLayout.PAGE_AXIS));
		offerResult = new JTable();
		offerScroll = new JScrollPane(offerResult);
		oPanel.add(offers);
		oPanel.add(offerScroll);
		
		JPanel pan1 = new JPanel();
		pan1.add(vPanel);
		pan1.add(oPanel);
		
		JPanel hPanel = new JPanel();
		hPanel.setLayout(new BoxLayout(hPanel, BoxLayout.PAGE_AXIS));
		histoResult = new JTable();
		histoScroll = new JScrollPane(histoResult);
		hPanel.add(historique);
		hPanel.add(histoScroll);
		
		//warning = new JLabel("WARNING");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.add(warning);
		this.add(pan1);
		this.add(hPanel);
		
	}
}

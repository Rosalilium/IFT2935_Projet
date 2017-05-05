package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Panneau contennant les éléments pour la vue Acheteur.

public class AcheteurPanel extends JPanel{

	private JComboBox categories;
	private JLabel priceLabel = new JLabel("Prix :");
	private JTextField price = new JTextField(10);
	private JButton search = new JButton("Rechercher");
	private JLabel res = new JLabel("Résultats");
	private JScrollPane resultsPane;
	private JTable results;
	private JLabel offers = new JLabel("Offre en cours");
	private JScrollPane offerPane;
	private JTable offersResults;
	private JLabel historique = new JLabel("Historique des achats");
	private JScrollPane histoPane;
	private JTable histoResults;
	private MainPanel mainpanel;
	
	public AcheteurPanel(MainPanel panel){
		mainpanel = panel;
		
		JPanel pricePanel = new JPanel();
		pricePanel.add(priceLabel);
		pricePanel.add(price);
		
		String[] cat = {"catégories"};
		categories = new JComboBox(cat);
		categories.setBorder(new EmptyBorder(0, 20 , 0, 50));
		
		JPanel searchPanel = new JPanel();
		searchPanel.add(categories);
		searchPanel.add(pricePanel);
		searchPanel.add(search);
		
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.PAGE_AXIS));
		results = new JTable();
		resultsPane = new JScrollPane(results);
		resultsPane.setPreferredSize(new Dimension(500,250));
		sPanel.add(searchPanel);
		sPanel.add(res);
		sPanel.add(resultsPane);
		
		
		JPanel oPanel = new JPanel();
		oPanel.setLayout(new BoxLayout(oPanel, BoxLayout.PAGE_AXIS));
		offersResults = new JTable();
		offerPane = new JScrollPane(offersResults);
		offerPane.setPreferredSize(new Dimension(500,150));
		oPanel.add(offers);
		oPanel.add(offerPane);
		
		JPanel hPanel = new JPanel();
		hPanel.setLayout(new BoxLayout(hPanel, BoxLayout.PAGE_AXIS));
		histoResults = new JTable();
		histoPane =  new JScrollPane(histoResults);
		histoPane.setPreferredSize(new Dimension(500,150));
		hPanel.add(historique);
		hPanel.add(histoPane);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(searchPanel);
		this.add(sPanel);
		this.add(oPanel);
		this.add(hPanel);
	
		
	}
}

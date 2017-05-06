package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sql.ResultTableModel;

// Panneau contennant les éléments pour la vue Acheteur.

public class AcheteurPanel extends JPanel{

	private JComboBox categories;
	private JLabel priceLabel = new JLabel("Prix :");
	private JTextField price = new JTextField(10);
	private JButton search = new JButton("Rechercher");
	private JLabel result = new JLabel("Resultats");
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
		
		JPanel sPanel = this.makeSeachPanel();
		JPanel oPanel = this.makeOffersPanel();
		JPanel hPanel = this.makeHistoPanel();
	
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(sPanel);
		this.add(oPanel);
		this.add(hPanel);
		
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Appeler recherche dans mainpanel
			}
		});
	
	}
	
	private JPanel makeSeachPanel(){
		
		JPanel pricePanel = new JPanel();
		pricePanel.add(priceLabel);
		pricePanel.add(price);
		
		String[] cat = {"categories"};
		// TODO: get catégories
		categories = new JComboBox(cat);
		categories.setBorder(new EmptyBorder(0, 20 , 0, 50));
		
		JPanel searchPanel = new JPanel();
		searchPanel.add(categories);
		searchPanel.add(pricePanel);
		searchPanel.add(search);
		
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.PAGE_AXIS));
		
		ResultTableModel res = mainpanel.getAllProducts();
		results = new JTable(res);
		resultsPane = new JScrollPane(results);
		resultsPane.setPreferredSize(new Dimension(300,250));
		
		sPanel.add(searchPanel);
		sPanel.add(result);
		sPanel.add(resultsPane);
		sPanel.setBorder(new EmptyBorder(10,10,10,10));
		return sPanel;
	}
	
	private JPanel makeOffersPanel(){
		JPanel oPanel = new JPanel();
		oPanel.setLayout(new BoxLayout(oPanel, BoxLayout.PAGE_AXIS));
		
		ResultTableModel res = mainpanel.getAcheteurOffers();
		offersResults = new JTable(res);
		offerPane = new JScrollPane(offersResults);
		offerPane.setPreferredSize(new Dimension(300,150));
		
		oPanel.add(offers);
		oPanel.add(offerPane);
		oPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		return oPanel;
	}
	
	private JPanel makeHistoPanel(){
		JPanel hPanel = new JPanel();
		hPanel.setLayout(new BoxLayout(hPanel, BoxLayout.PAGE_AXIS));
		
		ResultTableModel res = mainpanel.getAcheteurHistorique();
		histoResults = new JTable(res);
		histoPane =  new JScrollPane(histoResults);
		histoPane.setPreferredSize(new Dimension(300,150));
		
		hPanel.add(historique);
		hPanel.add(histoPane);
		hPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		return hPanel;
	}
}

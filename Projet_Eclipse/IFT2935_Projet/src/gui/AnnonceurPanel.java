package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sql.ResultTableModel;

// Panneau contennant les éléments pour la vue Vendeur.

public class AnnonceurPanel extends JPanel implements ListSelectionListener{
	
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
		
		JPanel vPanel = this.makeVentePanel();
		JPanel oPanel = this.makeOfferPanel();
		
		JPanel pan1 = new JPanel();
		pan1.setLayout(new BoxLayout(pan1, BoxLayout.LINE_AXIS));
		pan1.setPreferredSize(new Dimension(300,250));
		pan1.add(vPanel);
		pan1.add(oPanel);
		pan1.setBorder(new EmptyBorder(10,10,10,10));
		JPanel hPanel = this.makeHistoPanel();
		
		//TODO: check for expired products
		
		//warning = new JLabel("WARNING");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.add(warning);
		this.add(pan1);
		this.add(hPanel);
		
	}
	
	private JPanel makeVentePanel(){
		JPanel vPanel = new JPanel();
		vPanel.setLayout(new BoxLayout(vPanel, BoxLayout.PAGE_AXIS));
		
		ResultTableModel res = mainpanel.getVendeurProducts();
		venteResult = new JTable(res);
		venteResult.setRowSelectionAllowed(true);
		venteResult.setColumnSelectionAllowed(false);
		venteResult.getSelectionModel().addListSelectionListener(this);
		venteScroll = new JScrollPane(venteResult);
		
		vPanel.add(vente);
		vPanel.add(venteScroll);
		return vPanel;
	}
	
	private JPanel makeOfferPanel(){
		JPanel oPanel = new JPanel();
		oPanel.setLayout(new BoxLayout(oPanel, BoxLayout.PAGE_AXIS));
		
		offerResult = new JTable();
		offerScroll = new JScrollPane(offerResult);
		
		oPanel.add(offers);
		oPanel.add(offerScroll);
		
		return oPanel;
	}
	
	private JPanel makeHistoPanel(){
		JPanel hPanel = new JPanel();
		hPanel.setLayout(new BoxLayout(hPanel, BoxLayout.PAGE_AXIS));
		hPanel.setPreferredSize(new Dimension(300,250));
		
		ResultTableModel res = mainpanel.getVendeurHistorique();
		histoResult = new JTable(res);
		histoScroll = new JScrollPane(histoResult);
		
		hPanel.add(historique);
		hPanel.add(histoScroll);
		hPanel.setBorder(new EmptyBorder(10,10,10,10));
		return hPanel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int row = e.getFirstIndex();
		int col = 0;
		String productName = (String) venteResult.getModel().getValueAt(row, col);
		ResultTableModel res = mainpanel.getOffersOnProduct(productName);
		offerResult.setModel(res);
		this.repaint();
	}
}

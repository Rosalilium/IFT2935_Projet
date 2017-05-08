package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import sql.ResultTableModel;
import sql.SQLHelper;

// Panneau principal, contiendra les tabs.
// Contiendra une instance de la classe SQLHelper (ou la classe qui fait le lien avec
// la base de donn�es).
// On appelera d'ici les m�thodes correspondantes aux diff�rentes requ�tes qui seront 
// accessible par SQLHelper.

public class MainPanel extends JPanel {
	
	private LoginPanel login;
	private JTabbedPane tabs;
	private JFrame frame;
	private SQLHelper sql;
	private String username;
	
	public MainPanel(JFrame f){
		frame = f;
		sql = new SQLHelper();
		login = new LoginPanel(this);
		login.setBorder(new EmptyBorder(10,10,10,10));
		tabs = new JTabbedPane();
		JButton info = new JButton("?");
		
		this.setPreferredSize(new Dimension(1250, 600));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 3;
		gc.gridy = 0;
		this.add(info, gc);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		this.add(login, c);
		
		info.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame,
					    "Utilisateur : \n" 
					    		+ "Pseudonyme = Nanaxo \n" 
					    		+ "Mot de passe = hhjj");
			}
		});
	}
	
	
	// Lorsqu'on appuie sur Soumettre dans LoginPanel 
	// V�rifie le username et password
	// Si ok cr�er les tabs, sinon afficher un message d'erreur
	public void checkUserPassword(String username, String password){
		
		boolean ok = sql.isValidLogin(username, password);
		
		if(ok){
			this.username = username;
			makeTabs();
			this.removeAll();
			this.setLayout(new BorderLayout());
			this.add(tabs, BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		else{
			JLabel error = new JLabel("Mot de passe ou pseudonyme invalide");
			error.setForeground(Color.red);
			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = 1;
			gc.gridy = 3;
			this.add(error, gc);
			this.revalidate();
			this.repaint();
		}
	}
	
	
	private void makeTabs(){
		if(this.checkIfAcheteur()){
			tabs.add("Acheteur", new JScrollPane(new AcheteurPanel(this)));
		}
		if(this.checkIfAnnonceur()){
			tabs.addTab("Annonceur", new JScrollPane(new AnnonceurPanel(this)));
		}
		if(checkIfExpert()){
			tabs.add("Expert", new JScrollPane(new ExpertPanel(this)));
		}
	}
	
	
	// Cr�ation du ExpertPanel ///////////////////////////////////////////////////////////////////

	/**
	 * V�rifie si l'utilisateur est un expert
	 */
	public boolean checkIfExpert(){
		return sql.isExpert(username);
	}
	
	/**
	 * R�cup�re le tableau des produits � estim�s faisant partie de la cat�gorie d'expertise
	 * de l'utilisateur.
	 */
	public ResultTableModel getProductsToEstimate(){
		ResultSet res = sql.getUnevaluatedProducts(username);
		return new ResultTableModel(res);
	}
	
	
	/**
	 * R�cup�re le tableau des produits estim�s par l'utilisateur.
	 */
	public ResultTableModel getExpertHistorique(){
		ResultSet res = sql.getExpertHistory(username);
		return new ResultTableModel(res);
	}
	
	
	
	// Cr�ation du AnnonceurPanel ///////////////////////////////////////////////////////////////////////
	
	/**
	 * V�rifie si l'utilisateur est un vendeur
	 */
	public boolean checkIfAnnonceur(){
		return sql.isSeller(username);
	}
	
	/**
	 * V�rifie si l'utilisateur a des produits non vendus dont la date est expir�e.
	 */
	public ResultSet checkExpiredProducts(){
		return sql.getExpiredItems(username);
	}
	
	/**
	 * R�cup�re les produits actuellement mis vente par l'utilisateur.
	 */
	public ResultTableModel getVendeurProducts(){
		ResultSet res = sql.getUnsoldItems(username);
		return new ResultTableModel(res);
	}
	
	/**
	 * R�cup�re l'historique des produits vendus par l'utilisateur.
	 */
	public ResultTableModel getVendeurHistorique(){
		ResultSet res = sql.getSellerHistory(username);
		return new ResultTableModel(res);
	}
	
	
	// Cr�ation du AcheteurPanel /////////////////////////////////////////////////////////////////
	
	/**
	 * V�rifie si l'utilisateur est un Acheteur.
	 */
	public boolean checkIfAcheteur(){
		return sql.isBuyer(username);
	}
	
	/**
	 * R�cup�re la liste des cat�gories disponibles pour le ComboBox (menu d�roulant).
	 */
	public ResultSet getCategories(){
		return sql.getCategoryNames();
	}
	
	/**
	 * R�cup�re le tableau de tous les produits en vente.
	 */
	public ResultTableModel getAllProducts(){
		ResultSet res = sql.getAvailableItems();
		return new ResultTableModel(res);
	}
	
	/**
	 * R�cup�re le tableau des offres faites par l'utilisateur.
	 */
	public ResultTableModel getAcheteurOffers(){
		ResultSet res = sql.getBuyerOffers(username);
		return new ResultTableModel(res);
	}
	
	/**
	 * R�cup�re l'historique d'achat de l'utilisateur.
	 */
	public ResultTableModel getAcheteurHistorique(){
		ResultSet res = sql.getBuyerHistory(username);
		return new ResultTableModel(res);
	}


	
	// Losqu'on s�l�ctionne un produit dans VendeurPanel. ///////////////////////////////////////
	
	/**
	 * R�cup�rer les offres faites sur un produit.
	 */
	public ResultTableModel getOffersOnProduct(String productName){
		ResultSet res = sql.getProductOffers(username, productName);
		return new ResultTableModel(res);
	}
	
	
	// Losqu'on fait une recherche dans AcheteurPanel. ///////////////////////////////////////////
	
	/**
	 * R�cup�rer les produits disponibles selon la s�l�ction.
	 */
	public ResultTableModel searchProducts(String category, int price){
		ResultSet res = null;
		if(!category.equalsIgnoreCase("Toutes") && price != 0){
			res = sql.getItemsByCategoryAndPrice(category, price);
		}
		else if(category.equalsIgnoreCase("Toutes") && price != 0){
			res = sql.getItemsCheaperThan(price);
		}
		else if(!category.equalsIgnoreCase("Toutes") && price == 0){
			res = sql.getItemsByCategory(category);
		}
		else{
			res = sql.getAvailableItems();
		}
		return new ResultTableModel(res);
	}

}

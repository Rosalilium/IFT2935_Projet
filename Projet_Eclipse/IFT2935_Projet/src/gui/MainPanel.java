package gui;

import java.awt.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.TableModel;

import sql.ResultTableModel;
import sql.SQLHelper;

// Panneau principal, contiendra les tabs.
// Contiendra une instance de la classe SQLHelper (ou la classe qui fait le lien avec
// la base de données).
// On appelera d'ici les méthodes correspondantes aux différentes requêtes qui seront 
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
		tabs = new JTabbedPane();
		this.setPreferredSize(new Dimension(950, 900));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		this.add(login, c);
	}
	
	
	// Lorsqu'on appuie sur Soumettre dans LoginPanel 
	// Vérifie le username et password
	// Si ok créer les tabs, sinon afficher un message d'erreur
	public void checkUserPassword(String username, String password){
		
		boolean ok = true;
		ok = sql.isValidLogin(username, password);
		
		if(ok){
			this.username = username;
			tabs.add("Acheteur", new JScrollPane(new AcheteurPanel(this)));
			tabs.addTab("Annonceur", new JScrollPane(new AnnonceurPanel(this)));
			tabs.add("Expert", new JScrollPane(new ExpertPanel(this)));
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
			gc.gridy = 2;
			this.add(error, gc);
			this.revalidate();
			this.repaint();
		}
	}
	
	
	
	////// Lorsqu'on créer le ExpertPanel

	// Vérifier si l'utilisateur est un expert
	public boolean checkIfExpert(){
		return sql.isExpert(username);
	}
	
	// Récupérer le tableau des produits à estimés faisant partie de la catégorie d'expertise
	// de l'utilisateur.
	public ResultTableModel getProductsToEstimate(){
		ResultSet res = sql.getUnevaluatedProducts(username);
		return new ResultTableModel(res);
	}
	
	// Récupérer le tableau des produits estimés par l'utilisateur.
	public ResultTableModel getExpertHistorique(){
		ResultSet res = sql.getExpertHistory(username);
		return new ResultTableModel(res);
	}
	
	
	
	////// Lorsqu'on créer le AnnonceurPanel
	
	// Vérifier si l'utilisateur est un vendeur
	public boolean checkIfAnnonceur(){
		return sql.isSeller(username);
	}
	
	// Vérifier si l'utilisateur a des produits non vendus dont la date est expirée.
	public ResultSet checkExpiredProducts(){
		return sql.getExpiredItems(username);
	}
	
	//Récupérer les produits actuellement mis vente par l'utilisateur.
	public ResultTableModel getVendeurProducts(){
		ResultSet res = sql.getUnsoldItems(username);
		return new ResultTableModel(res);
	}
	
	//Récupérer l'historique des produits vendus par l'utilisateur.
	public ResultTableModel getVendeurHistorique(){
		ResultSet res = sql.getSellerHistory(username);
		return new ResultTableModel(res);
	}
	
	
	///// Lorsqu'on crée le AcheteurPanel.
	
	// Vérifier si l'utilisateur est un Acheteur.
	public boolean checkIfAcheteur(){
		return sql.isBuyer(username);
	}
	
	// Récupérer la liste des catégories disponibles pour le ComboBox (menu déroulant).
	public String[] getCategories(){
		// TODO: Appeler une méthode de SQLHelper qui récupère les noms des catégories existantes.
		//       Aucun paramètre. L'idéal serait que cette méthode retourne un Array de String
		//       réprésentant chacune le nom d'une catégorie. Mais ça peut également être un ResultSet
		//       et on fera le String[] ici.
		return null;
	}
	
	// Récupérer le tableau de tous les produits en vente.
	public ResultTableModel getAllProducts(){
		ResultSet res = sql.getAvailableItems();
		return new ResultTableModel(res);
	}
	
	//Récupérer le tableau des offres faites par l'utilisateur.
	public ResultTableModel getAcheteurOffers(){
		// TODO: Appeler la méthode de SQLHelper qui récupère les offres faites par l'utilisateur.
		return null;
	}
	
	// Récupérer l'historique d'achat de l'utilisateur.
	public ResultTableModel getAcheteurHistorique(){
		ResultSet res = sql.getBuyerHistory(username);
		return new ResultTableModel(res);
	}


	
	///// Losqu'on séléctionne un produit dans VendeurPanel.
	
	// Récupérer les offres faites sur un produit.
	public ResultTableModel getOffersOnProduct(String productName){
		ResultSet res = sql.getProductOffers(username, productName);
		return new ResultTableModel(res);
	}
	
	
	//// Losqu'on fait une recherche dans AcheteurPanel.
	
	// Récupérer les produits disponibles selon la séléction.
	public TableModel searchProducts(String categorie, double price){
		// TODO: Appeler la méthode qui effectue la recherche dans SQLHelper.
		//       On lui passe en paramètre categorie et price.
		
		// Si un des critères n'est pas spécifié on modifira les paramètres ici.
		// Si price n'est pas spécifié on passera en paramètre un nombre très grand.
		// La catégorie par défaut sera "Toutes". 
		// Il faut alors adapter la requête SQL en conséquence.
		return null;
	}

}

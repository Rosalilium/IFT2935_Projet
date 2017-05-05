package gui;

import java.awt.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.TableModel;

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
		//sql = new SQLHelper();
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
		
		//TODO: Appeler la méthode de SQLHelper qui vérifie la validité du username/password
		//      cette méthode devrait retourner true ou false.
		//      On lui donne en paramètres le username et le password.
		boolean ok = true;
		
		if(ok){
			this.username = username;
			tabs.add("Acheteur", new JScrollPane(new AcheteurPanel(this)));
			tabs.addTab("Vendeur", new JScrollPane(new VendeurPanel(this)));
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
		boolean res = true;
		// TODO: Appeler la méthode de SQLHelper qui vérifie si un username est Expert.
		//       On lui donne en paramètre le username. Elle retourne true/false.
		return res;
	}
	
	// Récupérer le tableau des produits à estimés faisant partie de la catégorie d'expertise
	// de l'utilisateur.
	public TableModel getProductsToEstimate(){
		// TODO: Appeler la méthode de SQLHelper qui touve les produits à estimer.
		//       On lui donne en paramètres le username.
		//       Elle retourne soit une implémentation de AbstactTableModel ou un ResultSet.
		//       Dans le second cas, la transformation en TableModel sera effectuée ici.
		
		return null;
	}
	
	// Récupérer le tableau des produits estimés par l'utilisateur.
	public TableModel getExpertHistorique(){
		// TODO: Appeler la méthode de SQLHelper qui touve les produits déjà estimés par cet utilisateur.
	    //       On lui donne en paramètres le username.
		//       Elle retourne soit une implémentation de AbstactTableModel ou un ResultSet.
		//       Dans le second cas, la transformation en TableModel sera effectuée ici.
		return null;
	}
	
	
	
	////// Lorsqu'on créer le VendeurPanel
	
	// Vérifier si l'utilisateur est un vendeur
	public boolean checkIfVendeur(){
		boolean res = true;
		// TODO: Appeler la méthode de SQLHelper qui vérifie si un utilisateur est vendeur.
		//       On lui donne en paramètre le username. Elle retourne true/false.
		return res;
	}
	
	// Vérifier si l'utilisateur a des produits non vendus dont la date est expirée.
	// Dans ce cas si on retourne directement le ResultSet qui devrait contenir le nom des produits
	// expirés ainsi que la date d'expiration. Ce sera plus simple pour l'affichage.
	public ResultSet checkExpiredProducts(){
		// TODO: Appeler la méthode de SQLHelper qui récupère les produits expirés pour un utilisateur.
		//       On lui passe en paramètre le username et la date actuelle.
		return null;
	}
	
	//Récupérer les produits actuellement mis vente par l'utilisateur.
	public TableModel getVendeurProducts(){
		// TODO: Appeler la méthode de SQLHelper qui récupère les produits d'un vendeur.
		//       On lui passe en paramètre le username. Elle peut retourner soit ResultSet ou TableModel.
		return null;
	}
	
	//Récupérer l'historique des produits vendus par l'utilisateur.
	public TableModel getVendeurHistorique(){
		// TODO: Appeler la méthode de SQLHelper qui récupère les produits vendus.
		//       On lui passe en parmètre le username.
		return null;
	}
	
	
	
	///// Lorsqu'on crée le AcheteurPanel.
	
	// Vérifier si l'utilisateur est un Acheteur.
	public boolean checkIfAcheteur(){
		boolean res = true;
		// TODO: Appeler la méthode de SQLHelper qui vérifie si un utilisateur est Acheteur.
		//       On lui donne en paramètre le username. Elle retourne true/false.
		return res;
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
	public TableModel getAllProducts(){
		// TODO: Appeler la méthode de SQLHelper qui récupère tous les produits actuellement en vente.
		//       Aucun paramètre. Elle peut retourner soit ResultSet ou TableModel.
		return null;
	}
	
	//Récupérer le tableau des offres faites par l'utilisateur.
	public TableModel getAcheteurOffers(){
		// TODO: Appeler la méthode de SQLHelper qui récupère les offres faites par l'utilisateur.
		//       On passe en paramètre le username. Elle retourne soit ResultSet ou TableModel.
		return null;
	}
	
	// Récupérer l'historique d'achat de l'utilisateur.
	public TableModel getAcheteurHistorique(){
		// TODO: Appeler la méthode de SQLHelper qui récupère l'historique des achats de l'utilisateur.
		//       On passe en paramètre le username. Elle retourne soit ResultSet ou TableModel.
		return null;
	}


	
	///// Losqu'on séléctionne un produit dans VendeurPanel.
	
	// Récupérer les offres faites sur un produit.
	public TableModel getOffersOnProduct(String productName){
		// TODO: Appeler la méthode dans SQLHelper qui récupère les offres sur le produit X.
		//       On passe en paramètre productName. Elle retourne soit ResultSet ou TableModel.
		return null;
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

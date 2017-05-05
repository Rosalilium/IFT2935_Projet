package gui;

import java.awt.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.TableModel;

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
	// V�rifie le username et password
	// Si ok cr�er les tabs, sinon afficher un message d'erreur
	public void checkUserPassword(String username, String password){
		
		//TODO: Appeler la m�thode de SQLHelper qui v�rifie la validit� du username/password
		//      cette m�thode devrait retourner true ou false.
		//      On lui donne en param�tres le username et le password.
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
	
	
	
	////// Lorsqu'on cr�er le ExpertPanel

	// V�rifier si l'utilisateur est un expert
	public boolean checkIfExpert(){
		boolean res = true;
		// TODO: Appeler la m�thode de SQLHelper qui v�rifie si un username est Expert.
		//       On lui donne en param�tre le username. Elle retourne true/false.
		return res;
	}
	
	// R�cup�rer le tableau des produits � estim�s faisant partie de la cat�gorie d'expertise
	// de l'utilisateur.
	public TableModel getProductsToEstimate(){
		// TODO: Appeler la m�thode de SQLHelper qui touve les produits � estimer.
		//       On lui donne en param�tres le username.
		//       Elle retourne soit une impl�mentation de AbstactTableModel ou un ResultSet.
		//       Dans le second cas, la transformation en TableModel sera effectu�e ici.
		
		return null;
	}
	
	// R�cup�rer le tableau des produits estim�s par l'utilisateur.
	public TableModel getExpertHistorique(){
		// TODO: Appeler la m�thode de SQLHelper qui touve les produits d�j� estim�s par cet utilisateur.
	    //       On lui donne en param�tres le username.
		//       Elle retourne soit une impl�mentation de AbstactTableModel ou un ResultSet.
		//       Dans le second cas, la transformation en TableModel sera effectu�e ici.
		return null;
	}
	
	
	
	////// Lorsqu'on cr�er le VendeurPanel
	
	// V�rifier si l'utilisateur est un vendeur
	public boolean checkIfVendeur(){
		boolean res = true;
		// TODO: Appeler la m�thode de SQLHelper qui v�rifie si un utilisateur est vendeur.
		//       On lui donne en param�tre le username. Elle retourne true/false.
		return res;
	}
	
	// V�rifier si l'utilisateur a des produits non vendus dont la date est expir�e.
	// Dans ce cas si on retourne directement le ResultSet qui devrait contenir le nom des produits
	// expir�s ainsi que la date d'expiration. Ce sera plus simple pour l'affichage.
	public ResultSet checkExpiredProducts(){
		// TODO: Appeler la m�thode de SQLHelper qui r�cup�re les produits expir�s pour un utilisateur.
		//       On lui passe en param�tre le username et la date actuelle.
		return null;
	}
	
	//R�cup�rer les produits actuellement mis vente par l'utilisateur.
	public TableModel getVendeurProducts(){
		// TODO: Appeler la m�thode de SQLHelper qui r�cup�re les produits d'un vendeur.
		//       On lui passe en param�tre le username. Elle peut retourner soit ResultSet ou TableModel.
		return null;
	}
	
	//R�cup�rer l'historique des produits vendus par l'utilisateur.
	public TableModel getVendeurHistorique(){
		// TODO: Appeler la m�thode de SQLHelper qui r�cup�re les produits vendus.
		//       On lui passe en parm�tre le username.
		return null;
	}
	
	
	
	///// Lorsqu'on cr�e le AcheteurPanel.
	
	// V�rifier si l'utilisateur est un Acheteur.
	public boolean checkIfAcheteur(){
		boolean res = true;
		// TODO: Appeler la m�thode de SQLHelper qui v�rifie si un utilisateur est Acheteur.
		//       On lui donne en param�tre le username. Elle retourne true/false.
		return res;
	}
	
	// R�cup�rer la liste des cat�gories disponibles pour le ComboBox (menu d�roulant).
	public String[] getCategories(){
		// TODO: Appeler une m�thode de SQLHelper qui r�cup�re les noms des cat�gories existantes.
		//       Aucun param�tre. L'id�al serait que cette m�thode retourne un Array de String
		//       r�pr�sentant chacune le nom d'une cat�gorie. Mais �a peut �galement �tre un ResultSet
		//       et on fera le String[] ici.
		return null;
	}
	
	// R�cup�rer le tableau de tous les produits en vente.
	public TableModel getAllProducts(){
		// TODO: Appeler la m�thode de SQLHelper qui r�cup�re tous les produits actuellement en vente.
		//       Aucun param�tre. Elle peut retourner soit ResultSet ou TableModel.
		return null;
	}
	
	//R�cup�rer le tableau des offres faites par l'utilisateur.
	public TableModel getAcheteurOffers(){
		// TODO: Appeler la m�thode de SQLHelper qui r�cup�re les offres faites par l'utilisateur.
		//       On passe en param�tre le username. Elle retourne soit ResultSet ou TableModel.
		return null;
	}
	
	// R�cup�rer l'historique d'achat de l'utilisateur.
	public TableModel getAcheteurHistorique(){
		// TODO: Appeler la m�thode de SQLHelper qui r�cup�re l'historique des achats de l'utilisateur.
		//       On passe en param�tre le username. Elle retourne soit ResultSet ou TableModel.
		return null;
	}


	
	///// Losqu'on s�l�ctionne un produit dans VendeurPanel.
	
	// R�cup�rer les offres faites sur un produit.
	public TableModel getOffersOnProduct(String productName){
		// TODO: Appeler la m�thode dans SQLHelper qui r�cup�re les offres sur le produit X.
		//       On passe en param�tre productName. Elle retourne soit ResultSet ou TableModel.
		return null;
	}
	
	
	//// Losqu'on fait une recherche dans AcheteurPanel.
	
	// R�cup�rer les produits disponibles selon la s�l�ction.
	public TableModel searchProducts(String categorie, double price){
		// TODO: Appeler la m�thode qui effectue la recherche dans SQLHelper.
		//       On lui passe en param�tre categorie et price.
		
		// Si un des crit�res n'est pas sp�cifi� on modifira les param�tres ici.
		// Si price n'est pas sp�cifi� on passera en param�tre un nombre tr�s grand.
		// La cat�gorie par d�faut sera "Toutes". 
		// Il faut alors adapter la requ�te SQL en cons�quence.
		return null;
	}

}

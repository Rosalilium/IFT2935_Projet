package sql;

import com.mysql.jdbc.Driver;
import java.sql.*;



public class SQLHelper {

	// Connection info
	private final static String URL = "jdbc:mysql://www-ens.iro.umontreal.ca/nguyendv_ACHATLIGNE";
	private final static String USER = "nguyendv_web";
	private final static String PW = "ndvp092N";

	// Query
	private final static String TEST = "SELECT nom_produit FROM Produit";

	private Connection connection;

	/**
	 * Void constructor
	 */
	public SQLHelper() {
		connect();
	}

	public boolean connect() {
		try {
			connection = DriverManager.getConnection(URL, USER, PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isConnected();
	}

	public boolean isConnected() {
		return connection != null;
	}

	/**
	 * Get query results from database
	 */
	public ResultSet query(String query) {
		if (isConnected()) {

			ResultSet resultSet = null;

			try {
				Statement statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				return resultSet;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ResultSet queryTest() {
		return query(TEST);
	}

	/**
	 * Pour login
	 * Vérifie si la combinaison de username et password est bien dans la base de données
	 */
	public boolean isValidLogin(String username, String password) {

		ResultSet results = query("SELECT username, password FROM Usager WHERE username = '" + username + "' AND password = '" + password + "'");

		return hasResults(results);
	}

	/**
	 * Pour onglet acheteur
	 * Verifie si l'usager est enregistre comme acheteur
	 */
	public boolean isAcheteur(String username) {

		ResultSet results = query("SELECT username FROM Acheteur WHERE username = '" + username + "'");

		return hasResults(results);

	}

	/**
	 * Pour onglet Annonceur
	 * Verifie si l'usager est enregistre comme annonceur
	 */
	public boolean isAnnonceur(String username) {

		ResultSet results = query("SELECT username FROM Annonceur WHERE username = '" + username + "'");

		return hasResults(results);

	}

	/**
	 * Pour onglet Expert
	 * Verifie si l'usager est enregistre comme annonceur
	 */
	public boolean isExpert(String username) {

		ResultSet results = query("SELECT username FROM Expert WHERE username = '" + username + "'");

		return hasResults(results);

	}

	// Helping method to test if results have been returned
	private boolean hasResults (ResultSet results) {

		if( results != null) {
			try {
				if (results.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}


}

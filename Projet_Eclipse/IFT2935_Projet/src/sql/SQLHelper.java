package sql;

import com.mysql.jdbc.Driver;
import java.sql.*;



public class SQLHelper {

	// Connection info
	private final static String URL = "jdbc:mysql://www-ens.iro.umontreal.ca/nguyendv_ACHATLIGNE";
	private final static String USER = "nguyendv_web";
	private final static String PW = "ndvp092N";

	// Predefined query
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
}

package sql;

import oracle.jdbc.driver.OracleDriver;
import java.sql.*;

// Mettre dans ce package tout ce qui est en lien avec le SQL. (connection au serveur,
// requêtes, etc.)
// Le nom de la classe peut être modifier c'était juste pour avoir quelque chose
// dans le package.
// On peut faire autant de classes que nécessaire dans ce package, mais l'idéal ce serait
// que seul les méthodes de cette classe ci puisse être appelées par le GUI.
// Bref, une seule classe dans le package "gui" communique avec une seule classe 
// dans le package "sql".

public class SQLHelper {
	
	final static String URL = "jdbc:oracle:thin:@oracle.iro.umontreal.ca:1521:orcl";
	final static String USER = "lacombam";
	final static String PW = "bamp108L";
	
	Connection connection;
	
	public void connect() {
		
	}
	
	ResultSet query(String query) {
		
		return null;
	}

	/*public static void main(String[] args) {
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL, USER, PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (connection != null) {
			System.out.println("Connection successful!");
			
			Statement statement = null;
			
			String query = "SELECT table_name FROM user_tables";
			
			try {
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1));
					System.out.println();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
		
			
			}
		} else {
			System.out.println("Erreur");
			System.exit(-1);
		}
	}*/
}

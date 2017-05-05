package sql;


import java.sql.ResultSet;
import java.sql.SQLException;

/**\
 * Temporary driver to test SQLhelper - TO BE DELETED
 */
public class HelperDriverTest {

    static String username = "Nanaxo";
    static String password = "hhjj";
    static String product = "Toyota Camry";

    public static void main(String[] args) {

        SQLHelper helper = new SQLHelper();
        ResultSet results = helper.getExpertHistory(username);

        ResultTableModel table = new ResultTableModel(results);

        for (int i = 0 ; i < table.getRowCount(); i++) {
            for (int j = 0 ; j < table.getColumnCount(); j++) {
                System.out.println(table.getValueAt(i,j));
                System.out.println();
            }
        }

        System.out.println("Login : " + helper.isValidLogin(username, password));
        System.out.println("Acheteur : " + helper.isBuyer(username));
        System.out.println("Annonceur : " + helper.isSeller(username));
        System.out.println("Expert : " + helper.isExpert(username));

		/*if( results != null) {
		    try {
                while(results.next()) {
                    System.out.println(results.getString(1));
                    System.out.println();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }


        // Demo sample code
		/*Connection connection = null;

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
		}*/
    }
}


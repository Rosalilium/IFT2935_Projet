package sql;


import java.sql.ResultSet;
import java.sql.SQLException;

/**\
 * Temporary driver to test SQLhelper
 */
public class HelperDriverTest {

    static String username = "Nanaxo";
    static String password = "hhjj";

    public static void main(String[] args) {

        SQLHelper helper = new SQLHelper();
        ResultSet results = helper.queryTest();

        System.out.println("Login : " + helper.isValidLogin(username, password));
        System.out.println("Acheteur : " + helper.isAcheteur(username));
        System.out.println("Annonceur : " + helper.isAnnonceur(username));
        System.out.println("Expert : " + helper.isExpert(username));

		if( results != null) {
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


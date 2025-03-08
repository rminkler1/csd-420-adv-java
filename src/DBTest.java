import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {
    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/databasedb?";
            Connection connection = DriverManager.getConnection(url + "user=student1&password=pass");

            Statement statement = connection.createStatement();

//            statement.executeUpdate("DELETE FROM address33 WHERE ID=20");

//            statement.executeUpdate("INSERT INTO address33 VALUES(20,'Smith','John','1234 Nowhere Dr','Bellevue','NE','68123')");
//            ResultSet results = statement.executeQuery("SELECT * FROM address33");


            ResultSet result = statement.executeQuery("SELECT * FROM address33 WHERE ID=20");
            int i = result.getMetaData().getColumnCount();

            while (result.next()) {
                for (int x = 1; x <= i; ++x) {

                    System.out.println(result.getString(x));
                }
            }
//            System.out.println(results);
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

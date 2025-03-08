import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Batch {

    public static void main(String[] args) {

        Connection connection;
        Statement statement;

        try {

            // Declare the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Location
            String url = "jdbc:mysql://localhost:3306/databasedb?";

            // Establish the Connection to the Database
            connection = DriverManager.getConnection(url + "user=student1&password=pass");

            // Create a Statement
            statement = connection.createStatement();

            // Drop the table if it exists
            statement.executeUpdate("DROP TABLE IF EXISTS batch");

            // Build a new table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `databasedb`.`batch` " +
                    "(ID INT NOT NULL AUTO_INCREMENT, Name VARCHAR(45) NULL, PRIMARY KEY (ID))");

            // Sequential Statements
            statement.executeUpdate("INSERT INTO batch (Name) VALUES('Joe')");
            statement.executeUpdate("INSERT INTO batch (Name) VALUES('Mary')");

            // Add insert statements to the batch.
            statement.addBatch("INSERT INTO batch (Name) VALUES('Jane')");
            statement.addBatch("INSERT INTO batch (Name) VALUES('Jack')");
            statement.addBatch("INSERT INTO batch (Name) VALUES('Bob')");
            statement.addBatch("INSERT INTO batch (Name) VALUES('Fred')");

            // Execute the batch
            statement.executeBatch();

            statement.clearBatch();
            // Close the statement and database connections
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

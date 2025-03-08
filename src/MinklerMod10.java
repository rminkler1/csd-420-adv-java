/*
 * Robert Minkler
 * CSD 420 Module 10
 * Feb 21, 2025
 *
 * Connect to the databasedb database and fans table.
 * Display and update records.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;


public class MinklerMod10 extends Application {


    // Define Record Display UI Elements
    private final Spinner<Integer> idSpinner = new Spinner<>(1, 10000, 1);
    private final TextField firstName = new TextField();
    private final TextField lastName = new TextField();
    private final TextField favoriteTeam = new TextField();

    // Create Status Bar Text Field
    private final Text statusBarText = new Text("Connecting...");

    // Database connector
    private DBConnector connection;


    public static void main(String[] args) {
        launch(args);
    }

    /// Build Application UI
    public void start(Stage stage) {

        // CONSTANTS JavaFX
        final double WINDOW_WIDTH = 450;
        final double WINDOW_HEIGHT = 250;
        final double TEXT_ENTRY_WIDTH = 300;
        final double SPACING = 10;

        // Create buttons for UI
        Button displayButton = new Button("Display");
        Button updateRecordsButton = new Button("Update Record");

        // Layout Record Display and Labels
        HBox idPane = new HBox(new Text("ID"), idSpinner);
        idSpinner.setPrefWidth(TEXT_ENTRY_WIDTH);
        idSpinner.setEditable(true);    // allow manual text entry
        idPane.setSpacing(SPACING);
        idPane.setAlignment(Pos.CENTER_RIGHT);

        HBox fnPane = new HBox(new Text("First Name"), firstName);
        firstName.setPrefWidth(TEXT_ENTRY_WIDTH);
        fnPane.setSpacing(SPACING);
        fnPane.setAlignment(Pos.CENTER_RIGHT);

        HBox lnPane = new HBox(new Text("Last Name"), lastName);
        lastName.setPrefWidth(TEXT_ENTRY_WIDTH);
        lnPane.setSpacing(SPACING);
        lnPane.setAlignment(Pos.CENTER_RIGHT);

        HBox teamPane = new HBox(new Text("Favorite Team"), favoriteTeam);
        favoriteTeam.setPrefWidth(TEXT_ENTRY_WIDTH);
        teamPane.setSpacing(SPACING);
        teamPane.setAlignment(Pos.CENTER_RIGHT);

        VBox recordDisplay = new VBox();
        recordDisplay.setSpacing(SPACING);
        recordDisplay.getChildren().addAll(idPane, fnPane, lnPane, teamPane);

        // Layout the buttons
        HBox controlButtons = new HBox();
        controlButtons.getChildren().addAll(displayButton, updateRecordsButton);
        controlButtons.setAlignment(Pos.CENTER);
        controlButtons.setSpacing(SPACING);

        // Layout the app using a BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(controlButtons);
        borderPane.setCenter(recordDisplay);
        borderPane.setBottom(statusBarText);
        borderPane.setPadding(new Insets(SPACING));
        BorderPane.setMargin(controlButtons, new Insets(0, 0, SPACING, 0));

        // Show the UI
        Scene scene = new Scene(borderPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Robert Minkler Module 10");
        stage.setScene(scene);
        stage.show();

        // Define Button Actions
        displayButton.setOnAction(_ -> connection.getRecord(idSpinner.getValue()));
        updateRecordsButton.setOnAction(_ -> connection.updateRecord());

        // Keep TextFields under 25 char long
        int maxLen = 25;
        favoriteTeam.setOnKeyTyped(_ -> connection.charLimit(favoriteTeam, maxLen));
        firstName.setOnKeyTyped(_ -> connection.charLimit(firstName, maxLen));
        lastName.setOnKeyTyped(_ -> connection.charLimit(lastName, maxLen));

        // Establish Database Connection
        connection = new DBConnector();
    }

    // Handle Database Connectivity
    public class DBConnector {

        // Store database connection
        private Connection connection;

        /// Create a DBConnector to connect to the database
        public DBConnector() {

            // Connection Variables
            String url = "jdbc:mysql://localhost:3306/databasedb";
            String user = "student1";
            String pass = "pass";


            try {
                // connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, pass);

                // update the status bar on success
                statusBarText.setText("Connected");

            } catch (SQLException e) {
                e.printStackTrace();

                // Show error in the status bar if unable to connect.
                statusBarText.setText("Database connection error.");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            /*
             * Close the database connection when closing the app using a shutdownHook
             * https://stackoverflow.com/questions/48110807/how-to-close-the-database-connection-at-the-end-of-the-program
             */
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {

                    // Close the connection
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {

                        // Output if connection is closed or not.
                        System.out.println(connection.isClosed() ?
                                "Database connection closed successfully" :
                                "Database connection failed to close."
                        );
                    } catch (SQLException _) {
                    }
                }
            }));

        }

        /// Get record with ID
        private void getRecord(int id) {
            try {
                // Get data using a prepared statement
                PreparedStatement getRecord = connection.prepareStatement("SELECT * FROM fans where ID = ?");
                getRecord.setInt(1, id);

                // Execute the query
                ResultSet results = getRecord.executeQuery();

                // Get the result and update the UI
                results.next();
                idSpinner.getValueFactory().setValue(results.getInt(1));
                firstName.setText(results.getString(2));
                lastName.setText(results.getString(3));
                favoriteTeam.setText(results.getString(4));

                // close statement
                getRecord.close();

                // Update the status bar
                statusBarText.setText("Record at index " + idSpinner.getValue() + " retrieved successfully.");

            } catch (SQLException _) {
                // Clear the UI if no record was found
                firstName.setText("No Record Found");
                lastName.setText("");
                favoriteTeam.setText("");

                // Update the status bar with error message
                statusBarText.setText("No record found at index " + idSpinner.getValue() + ".");
            }
        }

        /**
         * trim a text field that is too long
         * <a href="https://stackoverflow.com/questions/8499698/trim-a-string-based-on-the-string-length">trim a string reference</a>
         */
        private void charLimit(TextField textField, int len) {
            if (textField.getLength() > len) {
                String old = textField.getText();
                textField.setText(old.substring(0, Math.min(old.length(), len)));
                textField.end();    // Place the cursor at the end of the field
            }
        }

        // Update record or create a new record at the current index if one does not exist.
        private void updateRecord() {

            // Ensure fields are not over 25 characters long. If longer truncate.
            int MAX_LENGTH = 25;
            charLimit(firstName, MAX_LENGTH);
            charLimit(lastName, MAX_LENGTH);
            charLimit(favoriteTeam, MAX_LENGTH);

            try {

                // Update the database using replace because it can also insert records if they do not exist.
                PreparedStatement setRecord = connection.prepareStatement(
                        "REPLACE INTO fans (firstname, lastname, favoriteteam, ID) VALUES(?, ?, ?, ?)"
                );

                // set records for the prepared statement
                setRecord.setString(1, firstName.getText());
                setRecord.setString(2, lastName.getText());
                setRecord.setString(3, favoriteTeam.getText());
                setRecord.setInt(4, idSpinner.getValue());

                // Execute update
                setRecord.executeUpdate();

                // close statement
                setRecord.close();

                // Update the status bar if successful
                statusBarText.setText("Record " + idSpinner.getValue() + " updated.");

            } catch (SQLException _) {
                // Show error message in the status bar
                statusBarText.setText("Record " + idSpinner.getValue() + " failed to update.");
            }
        }
    }
}

/*
    Write a program that views and updates fan information stored in database titled "databasedb",
        user ID titled “student1” with a password “pass”.

    The table name is to be “fans” with the fields of ID (integer, PRIMARY KEY), firstname (varchar(25)),
        lastname (varchar(25)), and favoriteteam (varchar(25)).

    Your interface is to have 2 buttons to display and update records.
        The display button will display the record with the provided ID in the display (ID user provides).
        The update button will update the record in the database with the changes made in the display.
    Your application is not to create or delete the table.
    To work on this using your home database, you can make the table and populate it.
    When the application is tested, the table will already be created and populated.
    Write test code that ensures all methods and the interface functions correctly.
*/
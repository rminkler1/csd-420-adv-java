// Hide package declaration for submission
//package com.robertminkler.csd420javafx;

/*
 * Robert Minkler
 * CSD 420 Module 8
 * Feb 13, 2025
 *
 * Output random characters to a textArea using multiple threads.
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.security.InvalidParameterException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;


public class RobertMThreeThreads3 extends Application {

    // How many characters of each type to add to the textArea MAX = 1 Million.
    static final int count = 100000;

    // Extend the scope of textArea and stringBuffer to the class
    static TextArea textArea;

    // Updating a StringBuffer then updating the UI occasionally is much faster than updating the textArea constantly.
    // Holds the text for the textArea until I update the textArea.
    static StringBuffer stringBuffer;

    // Define the scope of ASCII characters for each group
    static final char alphaStart = 'a';
    static final char alphaEnd = 'z';
    static final char numStart = '0';
    static final char numEnd = '9';
    static final char specStart = '!';
    static final char specEnd = '@';


    public void start(Stage stage) {

        // Restrict the count to 1 Million or less
        if (count > 1000000) throw new InvalidParameterException("The count value is too large");

        // Configure TextArea: will contain the output from the program
        textArea = new TextArea();
        textArea.setWrapText(true);
        Pane centerPane = new StackPane(textArea);

        // Setup statusBar to show the character count in the textArea
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(2, 0, 3, 10));
        Text charCount = new Text("0");
        statusBar.getChildren().addAll(charCount, new Text(" characters output to the TextArea"));

        // Bind properties to update the status bar automatically
        charCount.textProperty().bind(textArea.textProperty().length().asString());

        // Set up the buttons at the top of the UI
        HBox buttons = new HBox();
        buttons.setPadding(new Insets(10));
        buttons.setSpacing(10);

        // BUTTON All three threads rerun
        Button allButton = new Button("Fill with all three random characters using three threads");
        allButton.setOnAction(_ -> runAllChar());

        // BUTTON Run only alpha thread to confirm it works
        Button alphaButton = new Button("Fill with Alphabetic Characters");
        alphaButton.setOnAction(_ -> runSelectedRandChar(alphaStart, alphaEnd));

        // BUTTON Run only numeric thread to confirm it works
        Button numButton = new Button("Fill with Numeric Characters");
        numButton.setOnAction(_ -> runSelectedRandChar(numStart, numEnd));

        // BUTTON Run only special character thread to confirm it works
        Button specialButton = new Button("Fill with Special Characters");
        specialButton.setOnAction(_ -> runSelectedRandChar(specStart, specEnd));

        // Add all buttons to UI
        buttons.getChildren().addAll(allButton, alphaButton, numButton, specialButton);

        // Layout panes and show UI
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(centerPane);
        borderPane.setBottom(statusBar);
        borderPane.setTop(buttons);
        buttons.setAlignment(Pos.CENTER);

        Scene scene = new Scene(borderPane, 900, 600);
        stage.setTitle("Three Threads Robert Minkler");
        stage.setScene(scene);
        stage.show();

        // Run all three random character threads to fill the text area
        runAllChar();
    }

    /// Runs all three threads to update the textArea with alpha, num, and special characters at random.
    private static void runAllChar() {

        // Establish an empty stringBuffer before beginning
        stringBuffer = new StringBuffer(count * 3 + 1);

        // Create Runnable objects that will print random characters
        GetRandChar getCharaToz = new GetRandChar(alphaStart, alphaEnd, count);
        GetRandChar getSpecialChar = new GetRandChar(specStart, specEnd, count);
        GetRandChar getNumChar = new GetRandChar(numStart, numEnd, count);

        // Execute the three Runnable classes in their own threads using an ExecutorService.
        // ExecutorService is autocloseable as of Java 19.
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            executor.submit(getCharaToz);
            executor.submit(getSpecialChar);
            executor.submit(getNumChar);
        }

        // Update the TextArea in the UI
        updateTextArea();
    }

    /// Creates a thread to output random characters between start and end to the textArea
    private static void runSelectedRandChar(char start, char end) {

        // Establish an empty stringBuffer before beginning
        stringBuffer = new StringBuffer(count + 1);

        // Create and start a new thread running an instance of GetRandChar with supplied values.
        Thread getCharThread = new Thread(new GetRandChar(start, end, count));
        getCharThread.start();

        // Wait until finished before updating the textArea
        try {
            getCharThread.join();
        } catch (InterruptedException _) {
        }

        // Update the textArea using the values stored in the stringBuffer
        updateTextArea();
    }

    /// Updates the textArea with the data in the stringBuffer
    private synchronized static void updateTextArea() {
        textArea.setText(stringBuffer.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }


    /// Runnable class to print random characters between start and end.
    private static class GetRandChar implements Runnable {
        char start;
        char end;
        int count;

        /**
         * Initialize a PrintRandChar object.
         * Requires the start character, the end character, and the number of characters to print to the console.
         * Characters are randomly selected based on their ASCII code, between start and end inclusive.
         *
         * @param start the first possible ASCII character
         * @param end   the last possible ASCII character
         * @param count the number of times to print a random character to the console
         */
        public GetRandChar(char start, char end, int count) {
            this.start = start;
            this.end = ++end;
            this.count = count;
        }

        /**
         * This is the code that is executed when a new thread is started.
         * It loops through the specified number of times based on the submitted count value.
         * Then it outputs the characters it is using along with a random character from that series of characters.
         */
        public void run() {

            char randomChar;    // move scope outside do while

            // Pick n random characters
            for (int i = 0; i < count; i++) {

                // Because 0-9 in ASCII sit in the middle of the special characters,
                // when selecting special characters, get a new char if a number was selected.
                do {
                    randomChar = (char) ThreadLocalRandom.current().nextInt(start, end);
                } while (start == specStart && randomChar >= numStart && randomChar <= numEnd);

                // append the new char to the stringBuffer
                stringBuffer.append(randomChar);
            }
        }
    }
}

/*
    Create a class titled <your first name here> ThreeThreads.
        In this class, you are to use three threads to output three types of characters to a text area for display.
            In the first thread, you are to output random letter characters such as a, b, c, d …
            In the second thread, you are to output random number digits such as 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
            In the third thread, you are to output random characters such as !, @, #, $, %, &, *
        Display a minimum of 10,000 of each of the three sets.
    Write test code that ensures all methods function correctly.
*/
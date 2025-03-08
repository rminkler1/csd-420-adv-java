import java.io.*;
import java.util.Arrays;

public class Copy {

    public static void main(String[] args) {
        // check for two arguments
        if (args.length != 2) {
            System.out.println("Usage: java Copy inputfile.txt outputfile.txt");
            System.exit(1);
        }

        // create input file and check if it exists
        File input = new File(args[0]);

        if (!input.exists()) {
            System.out.println("The input file " + args[0] + " is not found.");
            System.exit(2);
        }

        // create output file and check if it exists
        File output = new File(args[1]);

        if (output.exists()) {
            System.out.println(args[1] + " already exists");
            System.exit(3);
        }

        try (
                // create buffered streams
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(input));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output))
        ) {
            // copy data over
            int data;
            int bytesCopied = 0;


            while ((data = inputStream.read()) != -1) {
                outputStream.write((byte) data);
                bytesCopied++;
            }
            // print bytes copied over
            System.out.println("Copied " + bytesCopied + " Bytes");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}

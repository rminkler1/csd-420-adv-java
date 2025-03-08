import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mod1Example {
    public static void main(String[] args) throws Exception {

        writeFile();

        Person readPerson = readFile();

        // Print the read person
        System.out.println(readPerson.getFName() + " " + readPerson.getLName() + "\nID: " + readPerson.getID() +
                "\nBirthdate: " + readPerson.getBirthDate());
    }

    private static void writeFile() throws ParseException, IOException {

        // Create Person
        Person examplePerson = new Person("John", "Doe", 123456789,
                new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2000"));

        // Create output stream
        ObjectOutputStream output = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("student.dat")));

        // Write Object to the file then close the file
        output.writeObject(examplePerson);
        output.close();
    }

    private static Person readFile() throws IOException, ClassNotFoundException {

        // Create input stream
        ObjectInputStream input = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("student.dat")));

        // Read Person from the file then close the file
        Person readPerson = (Person) input.readObject();
        input.close();

        return readPerson;
    }
}

class Person implements Serializable {
    private String fName;
    private String lName;
    private final int id;
    private final Date birthdate;

    public Person(String fName, String lName, int id, Date bDay) {
        this.fName = fName;
        this.lName = lName;
        this.id = id;
        birthdate = bDay;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public int getID() {
        return id;
    }

    public Date getBirthDate() {
        return birthdate;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}

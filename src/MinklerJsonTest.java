import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinklerJsonTest {

    public static void main(String[] args) {
        serialization();
        deserialization();
        simpleSerial();
        Quote getKanyeQuote = simpleDeserial();
        System.out.println(getKanyeQuote);
        gsonFromMap();
        gsonToMap();
    }


    public static void gsonFromMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Key", "value");
        map.put("Key2", "value2");
        Gson gson = new Gson();
        System.out.println(gson.toJson(map));
    }

    public static void gsonToMap() {
        Gson gson = new Gson();

        // Create a com.google.gson.reflect.TypeToken object to declare types for the map object.
        TypeToken<HashMap<String, String>> mapType = new TypeToken<>() {
        };

        String json = "{'key': 'value', 'key2': 'value2'}";

        // Deserialization using the typetoken
        Map<String, String> stringMap = gson.fromJson(json, mapType);
    }


    public static void serialization() {
        // Serialization

        // Create Student Object
        Student student = new Student(1234, "Robert", "Minkler", true);

        // Add Courses
        student.addCourse(new Courses("Introduction to Java", "CSD", 320));
        student.addCourse(new Courses("Introduction to Programming with Python", "CSD", 205));

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
//        Gson gson = new Gson();  Default builder

        String studentGson = gson.toJson(student);
        System.out.println(studentGson);
    }

    public static void simpleSerial() {
        String kanye = "For me, money is not my definition of success. Inspiring people is a definition of success.";

        // Create an object
        Quote kanyeQuote = new Quote(kanye);

        // Init GSON
        Gson gson = new Gson();

        // Convert the quote into JSON using toJson()
        String jsonString = gson.toJson(kanyeQuote);

        // Display result
        System.out.println(jsonString);
    }

    public static Quote simpleDeserial() {
        // JSON data as a string
        String jsonData = "{'quote':'For me, money is not my definition of success. Inspiring people is a definition of success.'}";

        // Init GSON
        Gson gson = new Gson();

        // Convert the JSON string into a Quote object and return it.
        return gson.fromJson(jsonData, Quote.class);
    }

    public static void deserialization() {
        // Deserialization
        String jsonStudent = "{\"studentId\":1234,\"firstName\":\"Robert\",\"lastName\":\"Minkler\",\"degreeSeeking\":true,\"courses\":[{\"name\":\"Introduction to Java\",\"dept\":\"CSD\",\"number\":320},{\"name\":\"Introduction to Programming with Python\",\"dept\":\"CSD\",\"number\":205}]}\n";

        Gson gson = new Gson();

        Student student = gson.fromJson(jsonStudent, Student.class);
        System.out.println(student);
    }


}

class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private boolean degreeSeeking;
    private final List<Courses> courses = new ArrayList<>();


    Student(int studentId, String firstName, String lastName, boolean degreeSeeking) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.degreeSeeking = degreeSeeking;
    }

    public void addCourse(Courses course) {
        courses.add(course);
    }

    public void setID(int studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDegreeSeeking(boolean degSeeking) {
        degreeSeeking = degSeeking;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean getDegreeSeeking() {
        return degreeSeeking;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\n" + firstName + " " + lastName + "\nDegree Seeking: " +
                degreeSeeking + "\nCourses: " + getCourses();
    }

}

class Courses {
    private String name;
    private String dept;
    private int number;

    Courses(String name, String dept, int number) {
        this.name = name;
        this.dept = dept;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return dept + number + " " + name;
    }
}

///  Simple Java Object with a constructor
class Quote {
    private String quote;

    Quote() {
    }

    Quote(String quotation) {
        quote = quotation;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quote='" + quote + '\'' +
                '}';
    }
}


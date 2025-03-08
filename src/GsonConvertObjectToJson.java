/*
 * Robert Minkler
 * GSON Example found during research
 * Module 11 GSON Example
 *
 * Example from https://mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
 * This example outputs json to a file and the console using GSON
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonConvertObjectToJson {

    public static void main(String[] args) {

        // default
        //Gson gson = new Gson();

        // enable pretty print
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // create a staff object for testing
        Staff staff = createStaffObject();

        // Converts Java object to String
        String json = gson.toJson(staff);
        System.out.println(json);

        // Converts Java object to File
        try (Writer writer = new FileWriter("staff.json")) {
            gson.toJson(staff, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Staff createStaffObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(42);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});

        Map<String, BigDecimal> salary = new HashMap<>();
        salary.put("2010", new BigDecimal(10000));
        salary.put("2012", new BigDecimal(12000));
        salary.put("2018", new BigDecimal(14000));
        staff.setSalary(salary);

        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));
        staff.setActive(true);

        return staff;

    }
}

class Staff {

    private String name;
    private int age;
    private String[] position;              // array
    private List<String> skills;            // list
    private Map<String, BigDecimal> salary; // map
    private boolean active;                 // boolean

    Staff() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Map<String, BigDecimal> getSalary() {
        return salary;
    }

    public void setSalary(Map<String, BigDecimal> salary) {
        this.salary = salary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
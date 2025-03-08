import java.io.*;

public class WriteObjects {
    public static void main(String[] args) {
        TestObject test1 = new TestObject(4);
        test1.setNum(5);

        try (
                ObjectOutputStream output = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream("object_test.dat")))
        ) {
            output.writeObject(test1);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (
                ObjectInputStream input = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("object_test.dat")))
        ) {
            TestObject testInput = (TestObject) input.readObject();
            System.out.println(testInput.getNum());
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }
}

class TestObject implements Serializable {
    private int num;

    public TestObject(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int newNum) {
        num = newNum;
    }
}

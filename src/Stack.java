/*
 * Robert Minkler
 * Stack Implementation
 * January 2025
 */

import java.util.EmptyStackException;

public class Stack<T> {

    private T[] list;
    private int size;

    Stack() {

        // Initialize values
        list = (T[]) new Object[20];
        size = 0;
    }

    public T peek() {

        if (!isEmpty()) {
            // returns last item on the stack
            return list[size - 1];

            // The stack is empty Throw exception
        } else {
            throw new EmptyStackException();
        }
    }

    public T pop() {
        if (!isEmpty()) {

            // Shrink array if it is too large
            shrink();

            // Remove last item from the stack
            return list[--size];

        } else {
            throw new EmptyStackException();
        }

    }

    public void push(T object) {

        // Expand array if full
        if (size == list.length)
            expand();

        // Push object on the stack
        list[size++] = object;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private void expand() {

        // Track the current array while building the new array
        T[] tempList = list;

        // Build a new array double the length
        list = (T[]) new Object[tempList.length * 2];

        // Transfer items over to the new array
        System.arraycopy(tempList, 0, list, 0, tempList.length);
    }

    private void shrink() {

        // Only Shrink if it makes sense to shrink
        if (size < list.length / 3 && size > 10) {

            // Track the current array while building the new array
            T[] tempList = list;

            // Build a new smaller array
            list = (T[]) new Object[size + 10];

            // Transfer items over to the new array
            System.arraycopy(tempList, 0, list, 0, size);
        }
    }

    public static void main(String[] args) {

        Stack<Integer> intStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        stringStack.push("hello");
        stringStack.push("WORLD");

        System.out.println(stringStack.pop() + stringStack.peek());

        // populate int stack
        for (int i = 0; i < 100; i++) {
            intStack.push(i);
        }

        // Remove items from int stack
        while (!intStack.isEmpty()) {
            System.out.println(intStack.pop());
        }
    }
}
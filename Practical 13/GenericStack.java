import java.util.EmptyStackException;

class ArrayStack<T> {
    private Object[] elements;
    private int size = 0;

    public ArrayStack(int capacity) {
        elements = new Object[capacity];
    }

    public void push(T item) {
        if (size == elements.length) {
            Object[] newArr = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArr, 0, elements.length);
            elements = newArr;
        }
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T item = (T) elements[--size];
        elements[size] = null;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

public class GenericStack {
    public static void main(String[] args) {
        ArrayStack<String> stringStack = new ArrayStack<>(2);
        stringStack.push("First");
        stringStack.push("Second");
        System.out.println("Popped from stringStack: " + stringStack.pop());

        ArrayStack<Integer> intStack = new ArrayStack<>(2);
        intStack.push(100);
        intStack.push(200);
        System.out.println("Popped from intStack: " + intStack.pop());
    }
}
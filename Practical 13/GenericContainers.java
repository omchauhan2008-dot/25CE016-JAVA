class Box<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericContainers {
    public static <T> T getFirstElement(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Java");
        System.out.println("Box content: " + stringBox.get());

        Pair<Integer, String> pair = new Pair<>(101, "Alice");
        System.out.println("Pair: " + pair.getFirst() + " -> " + pair.getSecond());

        Integer[] numbers = {10, 20, 30};
        String[] names = {"Apple", "Banana", "Cherry"};

        System.out.println("First number: " + getFirstElement(numbers));
        System.out.println("First name: " + getFirstElement(names));
    }
}
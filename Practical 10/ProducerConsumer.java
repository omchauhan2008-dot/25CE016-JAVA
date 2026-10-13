
class SharedBuffer {
    private int buffer;
    private boolean available = false;

    public synchronized void produce(int number) {
        try {
            while (available) {
                wait();
            }

            buffer = number;
            available = true;

            System.out.println("Produced: " + number);

            notify();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized int consume() {
        int number = -1;

        try {
            while (!available) {
                wait();
            }

            number = buffer;
            available = false;

            System.out.println("Consumed: " + number);

            notify();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return number;
    }
}

class Producer extends Thread {
    private SharedBuffer buffer;
    private int count;

    public Producer(SharedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        for (int i = 1; i <= count; i++) {
            buffer.produce(i);
        }
    }
}

class Consumer extends Thread {
    private SharedBuffer buffer;
    private int count;

    public Consumer(SharedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        for (int i = 1; i <= count; i++) {
            buffer.consume();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Producer producer = new Producer(buffer, 10);
        Consumer consumer = new Consumer(buffer, 10);

        producer.start();
        consumer.start();
    }
}

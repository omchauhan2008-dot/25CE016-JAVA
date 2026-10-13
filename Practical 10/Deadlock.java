class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Deadlock {
    public static void main(String[] args) {
        Resource r1 = new Resource("Resource-1");
        Resource r2 = new Resource("Resource-2");

        Runnable deadlockTask1 = () -> {
            synchronized (r1) {
                System.out.println(Thread.currentThread().getName() + " locked " + r1.getName());
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (r2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + r2.getName());
                }
            }
        };

        Runnable deadlockTask2 = () -> {
            synchronized (r2) {
                System.out.println(Thread.currentThread().getName() + " locked " + r2.getName());
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (r1) {
                    System.out.println(Thread.currentThread().getName() + " locked " + r1.getName());
                }
            }
        };

        Runnable fixedTask = () -> {
            Resource first = r1.getName().compareTo(r2.getName()) < 0 ? r1 : r2;
            Resource second = first == r1 ? r2 : r1;

            synchronized (first) {
                System.out.println(Thread.currentThread().getName() + " locked " + first.getName());
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (second) {
                    System.out.println(Thread.currentThread().getName() + " locked " + second.getName());
                }
            }
        };

        System.out.println("--- Running Safe Concurrent Execution ---");
        Thread t1 = new Thread(fixedTask, "Thread-1");
        Thread t2 = new Thread(fixedTask, "Thread-2");

        t1.start();
        t2.start();
    }
}
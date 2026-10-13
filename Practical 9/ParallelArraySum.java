class ArraySumWorker extends Thread {
    private int[] arr;
    private int start, end;
    private long partialSum = 0;

    public ArraySumWorker(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += arr[i];
        }
    }

    public long getPartialSum() {
        return partialSum;
    }
}

public class ParallelArraySum {
    public static void main(String[] args) throws InterruptedException {
        int size = 1_000_000;
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = 1;
        }

        ArraySumWorker w1 = new ArraySumWorker(data, 0, 500_000);
        ArraySumWorker w2 = new ArraySumWorker(data, 500_000, 1_000_000);

        w1.start();
        w2.start();

        w1.join();
        w2.join();

        long totalSum = w1.getPartialSum() + w2.getPartialSum();
        System.out.println("Total Computed Sum: " + totalSum);
    }
}
public class BoundedStats {
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0.0;
        }
        double sum = 0;
        for (N val : values) {
            sum += val.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Integer[] intVals = {10, 20, 30, 40};
        Double[] doubleVals = {1.5, 2.5, 3.5};
        Long[] longVals = {100L, 200L, 300L};

        System.out.println("Integer Average: " + average(intVals));
        System.out.println("Double Average: " + average(doubleVals));
        System.out.println("Long Average: " + average(longVals));
    }
}
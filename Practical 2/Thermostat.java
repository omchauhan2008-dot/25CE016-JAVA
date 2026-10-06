public class Thermostat {
    private String location;
    private int temperature;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    public Thermostat(String location, int startTemp) {
        this.location = location;
        if (startTemp >= MIN && startTemp <= MAX) {
            this.temperature = startTemp;
        } else {
            this.temperature = 22;
        }
        activeCount++;
    }

    public Thermostat(String location) {
        this(location, 22);
    }

    public void raise() {
        if (this.temperature < MAX) {
            this.temperature++;
        } else {
            System.out.println("Already at maximum (30)");
        }
    }

    public void lower() {
        if (this.temperature > MIN) {
            this.temperature--;
        } else {
            System.out.println("Already at minimum (16)");
        }
    }

    public int getTemperature() {
        return this.temperature;
    }

    public static int getActiveCount() {
        return activeCount;
    }

    public static void main(String[] args) {
        Thermostat t1 = new Thermostat("Living Room", 28);
        Thermostat t2 = new Thermostat("Bedroom");

        System.out.println("Testing Thermostat 1 (" + t1.location + ")");

        System.out.println("Raising temperature 10 times:");
        for (int i = 0; i < 10; i++) {
            t1.raise();
            System.out.println("Current temp: " + t1.getTemperature());
        }

        System.out.println("\nLowering temperature 20 times:");
        for (int i = 0; i < 20; i++) {
            t1.lower();
            System.out.println("Current temp: " + t1.getTemperature());
        }

        System.out.println("\nActive thermostats count: " + Thermostat.getActiveCount());
    }
}
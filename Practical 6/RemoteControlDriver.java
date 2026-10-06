interface Switchable {
    void on();
    void off();

    default void toggle(boolean state) {
        if (state) {
            on();
        } else {
            off();
        }
    }
}

@FunctionalInterface
interface DeviceChecker {
    boolean canSwitchOn(Switchable device, int hour);
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

public class RemoteControlDriver {
    public static void main(String[] args) {
        Switchable[] devices = { new Fan(), new Light() };

        for (Switchable device : devices) {
            device.toggle(true);
        }

        int currentHour = 14;

        DeviceChecker anonymousChecker = new DeviceChecker() {
            public boolean canSwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        DeviceChecker lambdaChecker = (device, hour) -> hour >= 6 && hour <= 22;

        System.out.println("Anonymous check: " + anonymousChecker.canSwitchOn(devices[0], currentHour));
        System.out.println("Lambda check: " + lambdaChecker.canSwitchOn(devices[1], currentHour));
    }
}
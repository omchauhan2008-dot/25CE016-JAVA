import java.util.Scanner;

record Vehicle(String number, String type) {}

public class TollBooth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalToll = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        while (true) {
            System.out.print("Enter vehicle number (or type 'done' to finish): ");
            String number = scanner.next();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = scanner.next().toLowerCase();

            Vehicle vehicle = new Vehicle(number, type);

            
            int toll = switch (vehicle.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };

            totalToll = totalToll + toll;

            if (vehicle.type().equals("bike")) {
                bikeCount++;
            } else if (vehicle.type().equals("car")) {
                carCount++;
            } else if (vehicle.type().equals("truck")) {
                truckCount++;
            }
        }

        System.out.println("Total toll: " + totalToll);

        String mostFrequent = "bike";
        int maxCount = bikeCount;

        if (carCount > maxCount) {
            mostFrequent = "car";
            maxCount = carCount;
        }
        if (truckCount > maxCount) {
            mostFrequent = "truck";
        }

        System.out.println("Most frequent: " + mostFrequent);
        
        scanner.close();
    }
}

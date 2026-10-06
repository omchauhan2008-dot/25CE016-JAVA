public class ParkingLot {
    private int twoWheelers = 0;
    private int fourWheelers = 0;
    private final int twoCap;
    private final int fourCap;
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    public void park(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Parked 2-Wheeler");
            } else {
                System.out.println("Full");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Parked 4-Wheeler");
            } else {
                System.out.println("Full");
            }
        }
    }

    public void leave(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers > 0) {
                twoWheelers--;
                System.out.println("2-Wheeler left");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers > 0) {
                fourWheelers--;
                System.out.println("4-Wheeler left");
            }
        }
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 1);

        lot.park("two");
        lot.park("two");
        lot.park("two");

        lot.park("four");
        lot.park("four");

        lot.leave("two");
        lot.park("two");

        System.out.println("Two-wheelers parked: " + lot.twoWheelers);
        System.out.println("Four-wheelers parked: " + lot.fourWheelers);
        System.out.println("Revenue: " + ParkingLot.revenue);
    }
}
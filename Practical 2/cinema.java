public class cinema {

    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public cinema(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public cinema(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        } else {
            return false;
        }
    }

    public void cancel(int n) {
        seatsAvailable += n;
        if (seatsAvailable > capacity) {
            seatsAvailable = capacity;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public String getTitle() {
        return title;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        cinema c = new cinema("Example", 10);

        boolean r1 = c.book(3);
        System.out.println(r1);
        System.out.println("Seats: " + c.getSeatsAvailable());

        boolean r2 = c.book(8);
        System.out.println(r2);
        System.out.println("Seats: " + c.getSeatsAvailable());

        c.cancel(2);
        System.out.println("Seats after cancel: " + c.getSeatsAvailable());
        
        boolean r3 = c.book(8);
        System.out.println(r3);
        System.out.println("Seats: " + c.getSeatsAvailable());

        System.out.println(cinema.getTotalBooked());
    }
}
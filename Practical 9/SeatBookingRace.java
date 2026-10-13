class TicketSystem {
    private int seatsLeft = 5;

    public synchronized boolean bookSeat(String passenger) {
        if (seatsLeft > 0) {
            System.out.println(passenger + " booked seat. Seats remaining: " + (--seatsLeft));
            return true;
        } else {
            System.out.println(passenger + " failed to book. Sold out!");
            return false;
        }
    }
}

public class SeatBookingRace {
    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();

        for (int i = 1; i <= 10; i++) {
            String name = "Passenger-" + i;
            new Thread(() -> system.bookSeat(name)).start();
        }
    }
}
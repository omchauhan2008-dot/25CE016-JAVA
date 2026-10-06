abstract class Media {
    private String title;

    public Media(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract double calculateLateFee(int daysLate);
}

class Book extends Media {
    public Book(String title) {
        super(title);
    }

    public double calculateLateFee(int daysLate) {
        return daysLate * 2.0;
    }
}

class Dvd extends Media {
    public Dvd(String title) {
        super(title);
    }

    public double calculateLateFee(int daysLate) {
        return daysLate * 5.0;
    }
}

class Magazine extends Media {
    public Magazine(String title) {
        super(title);
    }

    public double calculateLateFee(int daysLate) {
        return daysLate * 1.5;
    }
}

public class MediaDriver {
    public static void main(String[] args) {
        Media[] batch = {
            new Book("Java Programming"),
            new Dvd("Inception"),
            new Magazine("Tech Today")
        };

        int daysLate = 4;
        double totalFees = 0.0;

        for (Media item : batch) {
            double fee = item.calculateLateFee(daysLate);
            totalFees += fee;
            System.out.println(item.getTitle() + " Late Fee: " + fee);
        }

        System.out.println("Total Late Fees: " + totalFees);
    }
}
import java.util.Scanner;

@FunctionalInterface
interface DiscountRule {
    double apply(double price);
}

class DiscountEngine {
    public static void applyDiscount(double[] prices, DiscountRule rule) {
        for (double price : prices) {
            double discounted = rule.apply(price);
            System.out.println("Original: " + price + " -> Final: " + discounted);
        }
    }
}

public class DiscountDriver {
    public static void main(String[] args) {
        double[] prices = { 100.0, 250.0, 500.0, 1200.0 };

        Scanner sc = new Scanner(System.in);
        System.out.println("Select Discount Rule:");
        System.out.println("1. Flat 10% Off");
        System.out.println("2. Flat 50 Off");
        int choice = sc.nextInt();

        DiscountRule selectedRule;
        if (choice == 1) {
            selectedRule = price -> price * 0.90;
        } else {
            selectedRule = price -> Math.max(0, price - 50);
        }

        DiscountEngine.applyDiscount(prices, selectedRule);
        sc.close();
    }
}
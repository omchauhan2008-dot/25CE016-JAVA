import java.util.Scanner;


enum Coin {
    ONE, TWO, FIVE, TEN
}

public class VendingMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targetPrice = 15;
        int total = 0;

        System.out.println("Item price is 15. Please insert coins (ONE, TWO, FIVE, TEN):");

        
        while (total < targetPrice) {
            System.out.print("Insert coin: ");
            String input = scanner.next().toUpperCase(); 
            
            
            Coin coin = Coin.valueOf(input);

            
            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            total = total + value;
            System.out.println("Total inserted so far: " + total);
        }

        int change = total - targetPrice;
        System.out.println("Paid. Change: " + change);
        
        scanner.close();
    }
}
import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

public class guardedcalculator {
    public static int readNumber(Scanner sc) throws InvalidNumberException {
        String input = sc.next();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("Invalid number entered. Please enter a valid integer.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter first number: ");
                int a = readNumber(sc);

                System.out.print("Enter second number: ");
                int b = readNumber(sc);

                System.out.print("Enter operator (+, -, *, /): ");
                String op = sc.next();

                if (op.equals("/") && b == 0) {
                    throw new DivideByZeroException("Cannot divide by zero.");
                }

                int result;
                switch (op) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator. Use +, -, *, or /.");
                }

                System.out.println("Result: " + result);
                break;

            } catch (InvalidNumberException e) {
                System.out.println("Invalid number: " + e.getMessage());
            } catch (DivideByZeroException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid operator: " + e.getMessage());
            } finally {
                System.out.println("Attempt logged.\n");
            }
        }

        sc.close();
    }
}
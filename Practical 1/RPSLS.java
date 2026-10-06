import java.util.Random;
import java.util.Scanner;

public class RPSLS {
    enum Move {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK
    }

    public static int winner(Move a, Move b) {
        if (a == b) return 0;

        boolean aBeatsB = switch (a) {
            case SCISSORS -> b == Move.PAPER || b == Move.LIZARD;
            case PAPER -> b == Move.ROCK || b == Move.SPOCK;
            case ROCK -> b == Move.LIZARD || b == Move.SCISSORS;
            case LIZARD -> b == Move.SPOCK || b == Move.PAPER;
            case SPOCK -> b == Move.SCISSORS || b == Move.ROCK;
        };

        return aBeatsB ? 1 : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Move[] moves = Move.values();

        int playerScore = 0;
        int computerScore = 0;

        for (int round = 1; round <= 5; round++) {
            System.out.print("Round " + round + " - Enter move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
            String input = scanner.nextLine().toUpperCase();

            Move playerMove = null;
            for (Move m : moves) {
                if (m.name().equals(input)) {
                    playerMove = m;
                    break;
                }
            }

            if (playerMove == null) {
                System.out.println("Invalid input. Please enter a valid move.\n");
                round--;
                continue;
            }

            Move computerMove = moves[random.nextInt(moves.length)];
            int result = winner(playerMove, computerMove);

            System.out.println("Player: " + playerMove + " | Computer: " + computerMove);

            if (result == 1) {
                System.out.println("Round Winner: Player");
                playerScore++;
            } else if (result == -1) {
                System.out.println("Round Winner: Computer");
                computerScore++;
            } else {
                System.out.println("Round Winner: Tie");
            }
            System.out.println();
        }

        System.out.println("------------------------------------");
        if (playerScore > computerScore) {
            System.out.println("You win " + playerScore + "" + computerScore);
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins " + computerScore + "-" + playerScore);
        } else {
            System.out.println("Game tied " + playerScore + "-" + computerScore);
        }

        scanner.close();
    }
}
public class CardDriver {
    public static void main(String[] args) {
        Card[] cards = new Card[5];
        int count = 0;

        Card[] inputCards = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("Ace", "Spades"),
            new Card("10", "Diamonds")
        };

        for (Card currentCard : inputCards) {
            boolean duplicate = false;
            for (int i = 0; i < count; i++) {
                if (cards[i].equals(currentCard)) {
                    System.out.println("Duplicate found: " + currentCard);
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                cards[count++] = currentCard;
            }
        }
    }
}
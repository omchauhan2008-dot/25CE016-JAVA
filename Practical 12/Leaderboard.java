import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 850);
        scores.put("Bob", 920);
        scores.put("Charlie", 920);
        scores.put("David", 780);
        scores.put("Eve", 950);

        List<Map.Entry<String, Integer>> leaderboard = new ArrayList<>(scores.entrySet());
        leaderboard.sort((e1, e2) -> {
            int scoreCompare = e2.getValue().compareTo(e1.getValue());
            if (scoreCompare != 0) {
                return scoreCompare;
            }
            return e1.getKey().compareTo(e2.getKey());
        });

        int topN = 3;
        System.out.println("Top " + topN + " Players:");
        for (int i = 0; i < Math.min(topN, leaderboard.size()); i++) {
            Map.Entry<String, Integer> entry = leaderboard.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + " - " + entry.getValue());
        }
    }
}
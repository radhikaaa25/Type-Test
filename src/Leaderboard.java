// Leaderboard.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private static List<Score> scores = new ArrayList<>();

    public static void addScore(String name, int wpm) {
        scores.add(new Score(name, wpm));
        // Sort the scores in descending order
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                return Integer.compare(s2.getWpm(), s1.getWpm());
            }
        });
    }

    public static List<Score> getScores() {
        return scores;
    }

    public static class Score {
        private String name;
        private int wpm;

        public Score(String name, int wpm) {
            this.name = name;
            this.wpm = wpm;
        }

        public String getName() {
            return name;
        }

        public int getWpm() {
            return wpm;
        }
    }
}

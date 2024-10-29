import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private static final String DB_URL = "jdbc:sqlite:typetest.sqlite";

    public static void addScore(String username, int wpm, int errors) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            int userId = Database.getUserId(username);

            String sql = "INSERT INTO Scores(userId, wpm, errors) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, wpm);
                pstmt.setInt(3, errors);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Score> getScores() {
        List<Score> scores = new ArrayList<>();
        String sql = """
                     SELECT Users.username, Scores.wpm, Scores.errors
                     FROM Scores
                     JOIN Users ON Scores.userId = Users.userId
                     ORDER BY Scores.wpm DESC
                     LIMIT 10;
                     """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String username = rs.getString("username");
                int wpm = rs.getInt("wpm");
                int errors = rs.getInt("errors");
                scores.add(new Score(username, wpm, errors));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    // Score inner class to hold the leaderboard entries
    public static class Score {
        private String name;
        private int wpm;
        private int errors;

        public Score(String name, int wpm, int errors) {
            this.name = name;
            this.wpm = wpm;
            this.errors = errors;
        }

        public String getName() {
            return name;
        }

        public int getWpm() {
            return wpm;
        }

        public int getErrors() {
            return errors;
        }
    }
}

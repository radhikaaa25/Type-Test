import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:typetest.sqlite";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Users (userId INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL UNIQUE, password TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Scores (scoreId INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, wpm INTEGER, errors INTEGER, testDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY(userId) REFERENCES Users(userId))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Register a new user after checking if the username exists
    public static boolean registerUser(String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("Username already taken.");
            return false;
        }

        String sql = "INSERT INTO Users(username, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if a username already exists
    private static boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate user by matching username and password
    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get the user ID by username
    public static int getUserId(String username) throws SQLException {
        String sql = "SELECT userId FROM Users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt("userId") : -1;
        }
    }

    // Add score data for a user
    public static void addScore(int userId, int wpm, int errors) {
        String sql = "INSERT INTO Scores(userId, wpm, errors) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, wpm);
            pstmt.setInt(3, errors);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

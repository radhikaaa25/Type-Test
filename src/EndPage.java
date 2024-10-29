import javax.swing.*;
import java.awt.*;

public class EndPage extends JFrame {
    public EndPage(String playerName, int wpm, int errors, int totalWords) {
        setTitle("TypeTest - Results");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Calculate accuracy
        int correctWords = totalWords - errors;
        double accuracy = (double) correctWords / totalWords * 100;

        // Add score to leaderboard
        try {
            int userId = Database.getUserId(playerName);
            if (userId != -1) {
                Database.addScore(userId, wpm, errors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Result labels with modern styling
        JLabel titleLabel = new JLabel("Your Results, " + playerName + "!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0xFF4081));

        JLabel wpmLabel = new JLabel("WPM: " + wpm);
        JLabel errorsLabel = new JLabel("Errors: " + errors);
        JLabel accuracyLabel = new JLabel(String.format("Accuracy: %.2f%%", accuracy));

        wpmLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        errorsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        accuracyLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Progress bar for WPM and accuracy
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(1, 2, 20, 20));
        resultsPanel.add(createCirclePanel("WPM", wpm));
        resultsPanel.add(createCirclePanel("Accuracy", (int) accuracy));

        // Action buttons for leaderboard or return to main menu
        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(e -> {
            new LeaderboardPage().setVisible(true);
            dispose();
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titleLabel);
        panel.add(wpmLabel);
        panel.add(errorsLabel);
        panel.add(accuracyLabel);
        panel.add(resultsPanel);
        panel.add(leaderboardButton);
        panel.add(backButton);

        add(panel);
    }

    private JPanel createCirclePanel(String label, int value) {
        JPanel circlePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0xFFC107));
                g.fillOval(10, 10, 80, 80);
                g.setColor(new Color(0x4CAF50));
                g.drawString(label + ": " + value, 30, 50);
            }
        };
        circlePanel.setPreferredSize(new Dimension(100, 100));
        return circlePanel;
    }
}








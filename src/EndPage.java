import javax.swing.*;
import java.awt.*;

public class EndPage extends JFrame {
    public EndPage(String playerName, int wpm, int errors, int totalWords) {
        setTitle("TypeTest - Results");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add score to the leaderboard
        Leaderboard.addScore(playerName, wpm);

        // Calculate accuracy
        int correctWords = totalWords - errors;
        double accuracy = (double) correctWords / totalWords * 100;

        // Create UI components
        JLabel resultLabel = new JLabel("Your WPM: " + wpm + ", Errors: " + errors, JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel accuracyLabel = new JLabel(String.format("Accuracy: %.2f%%", accuracy), JLabel.CENTER);
        accuracyLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Drawing accuracy as circles
        JPanel circlePanel = new CirclePanel(accuracy);

        JButton leaderboardButton = new JButton("View Leaderboard");
        leaderboardButton.addActionListener(e -> {
            new LeaderboardPage().setVisible(true);
            dispose();  // Close current window
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();  // Close current window
        });

        // Layout for the results
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(resultLabel);
        panel.add(accuracyLabel);
        panel.add(circlePanel);
        panel.add(leaderboardButton);
        panel.add(backButton);

        add(panel);
    }

    // Inner class to draw circles for accuracy
    private class CirclePanel extends JPanel {
        private final double accuracy;

        public CirclePanel(double accuracy) {
            this.accuracy = accuracy;
            setPreferredSize(new Dimension(100, 100));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0xFFC107)); // Yellow for accuracy
            int diameter = 80;
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;

            // Draw background circle
            g.setColor(new Color(0xE0E0E0)); // Gray for background
            g.fillOval(x, y, diameter, diameter);

            // Draw accuracy circle
            g.setColor(new Color(0x4CAF50)); // Green for accuracy
            int accuracyDiameter = (int) (diameter * (accuracy / 100));
            g.fillOval(x + (diameter - accuracyDiameter) / 2, y + (diameter - accuracyDiameter) / 2, accuracyDiameter, accuracyDiameter);
        }
    }
}

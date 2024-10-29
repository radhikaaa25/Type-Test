import javax.swing.*;
import java.awt.*;

public class EndPage extends JFrame {
    public EndPage(String playerName, int wpm, int errors) {
        setTitle("TypeTest - Results");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add score to leaderboard
        try {
            int userId = Database.getUserId(playerName);
            if (userId != -1) {
                Database.addScore(userId, wpm, errors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Header label
        JLabel titleLabel = new JLabel("Your Results, " + playerName + "!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0xFF4081));

        // Circular displays for WPM and Errors
        JPanel circlePanel = new JPanel();
        circlePanel.setLayout(new GridLayout(1, 2, 20, 0));
        circlePanel.add(createCircleDisplay("WPM", wpm, new Color(0x4CAF50)));
        circlePanel.add(createCircleDisplay("Errors", errors, new Color(0xF44336)));

        // Buttons for navigation
        JButton leaderboardButton = new JButton("View Leaderboard");
        leaderboardButton.setBackground(new Color(0x2196F3));
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.setFocusPainted(false);
        leaderboardButton.addActionListener(e -> {
            new LeaderboardPage().setVisible(true);
            dispose();
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(0xFF7043));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();
        });

        // Layout and styling
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0xECEFF1));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(circlePanel);
        panel.add(Box.createVerticalStrut(20));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(backButton);
        buttonPanel.setBackground(new Color(0xECEFF1));

        // Add components to main frame
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createCircleDisplay(String label, int value, Color color) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int size = Math.min(getWidth(), getHeight()) - 10;

                g.setColor(new Color(0xEEEEEE));
                g.fillOval(10, 10, size, size);

                g.setColor(color);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawString(label, 20, 35);

                g.setFont(new Font("Arial", Font.PLAIN, 28));
                g.drawString(Integer.toString(value), 40, 70);
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EndPage("Player", 75, 3).setVisible(true));
    }
}

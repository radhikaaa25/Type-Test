import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {
    private String userName;

    public LandingPage(String userName) {
        this.userName = userName;
        setTitle("TypeTest");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create an ImagePanel with the keyboard background image
        ImagePanel panel = new ImagePanel("C:\\Users\\smile\\Downloads\\demo\\Type-Test\\src\\keybg.jpg");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adding a title label
        JLabel titleLabel = new JLabel("WELCOME TO TypeTest ;)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Gentona", Font.BOLD, 29));
        titleLabel.setForeground(new Color(0xDCBDBD)); // Yellow color for visibility
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Create buttons in opaque rectangles
        panel.add(createButtonPanel("Practice", e -> new PracticePage().setVisible(true)));
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacing
        panel.add(createButtonPanel("Test", e -> new TestPage().setVisible(true)));
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacing
        panel.add(createButtonPanel("Tips", e -> new TipsPage().setVisible(true)));
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacing
        panel.add(createButtonPanel("Leaderboard", e -> new LeaderboardPage().setVisible(true)));

        // Add a footer label
        JLabel footerLabel = new JLabel("Get ready to improve your typing skills!", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        footerLabel.setForeground(new Color(0xBFBA98)); // Yellow color for visibility
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing
        panel.add(footerLabel);

        // Add the main panel to the frame
        add(panel);
        setVisible(true);
    }

    private JPanel createButtonPanel(String text, ActionListener action) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xCCB51E1E, true)); // Dark red color for buttons
        buttonPanel.setOpaque(true);
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(0xB67E34), 2)); // Light red border
        buttonPanel.setLayout(new BorderLayout());

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0xCC9F0F0F, true)); // Match panel color
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(action);

        buttonPanel.add(button, BorderLayout.CENTER);
        buttonPanel.setPreferredSize(new Dimension(400, 50)); // Button size
        buttonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return buttonPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LandingPage("Anonymous"));
    }
}

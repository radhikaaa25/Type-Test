import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {
    private String userName;

    public LandingPage(String userName) {
        this.userName = userName;
        setTitle("TypeTest");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create ImagePanel with a GIF background
        ImagePanel panel = new ImagePanel("C:\\Users\\smile\\Downloads\\demo\\Type-Test\\src\\key3.gif");
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel logoLabel = new JLabel(new ImageIcon("C:\\Users\\smile\\Downloads\\demo\\Type-Test\\src\\loho.png.png")); // Placeholder for logo image
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(logoLabel, gbc);

        // Welcome title
        JLabel titleLabel = new JLabel("Welcome to TypeTest !!!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0x63B68B));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(titleLabel, gbc);

        // Main options (Practice, Test, Tips, Leaderboard)
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 2;
        panel.add(createButtonPanel("Practice", e -> new PracticePage().setVisible(true)), gbc);
        gbc.gridy++;
        panel.add(createButtonPanel("Test", e -> new TestPage(userName).setVisible(true)), gbc);
        gbc.gridy++;
        panel.add(createButtonPanel("Tips", e -> new TipsPage().setVisible(true)), gbc);
        gbc.gridy++;
        panel.add(createButtonPanel("Leaderboard", e -> new LeaderboardPage().setVisible(true)), gbc);

        // Footer text
        JLabel footerLabel = new JLabel("Get ready to boost your typing skills!", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        footerLabel.setForeground(new Color(0xD5EAE8));
        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(footerLabel, gbc);

        // Login and Signup buttons
        JPanel authPanel = new JPanel();
        authPanel.setOpaque(false); // Make it transparent to blend with the background

        // Login Button
        JButton loginButton = createAuthButton("Login", e -> new LoginPage().setVisible(true));
        authPanel.add(loginButton);

        // Signup Button
        JButton signupButton = createAuthButton("Signup", e -> new SignupPage().setVisible(true));
        authPanel.add(signupButton);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(authPanel, gbc);

        add(panel);
        setVisible(true);
    }

    private JPanel createButtonPanel(String text, ActionListener action) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0x3CB35C));
        buttonPanel.setOpaque(true);
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(0xFF7043), 2));
        buttonPanel.setLayout(new BorderLayout());

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0x880E4F));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(action);

        buttonPanel.add(button, BorderLayout.CENTER);
        buttonPanel.setPreferredSize(new Dimension(350, 50));
        buttonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return buttonPanel;
    }

    private JButton createAuthButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0x062C2C)); // Different color for login/signup
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        return button;
    }

    // Inner class for background image (GIF)
    private static class ImagePanel extends JPanel {
        private final Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LandingPage("Anonymous"));
    }
}

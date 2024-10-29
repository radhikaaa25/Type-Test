import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("TypeTest - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color and layout
        getContentPane().setBackground(new Color(34, 40, 49));
        setLayout(new BorderLayout());

        // Create a panel for the form inputs and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(new Color(57, 62, 70));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Username and password fields with rounded corners
        JTextField usernameField = new JTextField(20);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        usernameField.setBackground(new Color(238, 238, 238));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        passwordField.setBackground(new Color(238, 238, 238));

        // Buttons with hover effects
        JButton loginButton = createStyledButton("Login", new Color(34, 166, 179));
        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (Database.authenticateUser(username, password)) {
                new LandingPage(username).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton signupButton = createStyledButton("Sign Up", new Color(255, 87, 34));
        signupButton.addActionListener(e -> new SignupPage().setVisible(true));

        // Add components to form panel
        formPanel.add(new JLabel("Username:", JLabel.RIGHT));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:", JLabel.RIGHT));
        formPanel.add(passwordField);
        formPanel.add(loginButton);
        formPanel.add(signupButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}

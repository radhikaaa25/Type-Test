import javax.swing.*;
import java.awt.*;

public class SignupPage extends JFrame {
    public SignupPage() {
        setTitle("TypeTest - Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(34, 40, 49));
        setLayout(new BorderLayout());


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(new Color(57, 62, 70));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label=new JLabel();
        label.setText("Username:");
        label.setForeground(new Color(255, 255, 255));
        label.setBackground(new Color(181, 76, 76));

        JLabel label2=new JLabel();
        label2.setText("Password:");
        label2.setForeground(new Color(255, 255, 255));
        label2.setBackground(new Color(181, 76, 76));

        JTextField usernameField = new JTextField(20);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        usernameField.setBackground(new Color(238, 238, 238));

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        passwordField.setBackground(new Color(238, 238, 238));

        JButton registerButton = createStyledButton("Register", new Color(34, 166, 179));
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (Database.registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginPage().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username is already taken.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        formPanel.add(label);
        formPanel.add(usernameField);
        formPanel.add(label2);
        formPanel.add(passwordField);
        formPanel.add(registerButton);

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
        SwingUtilities.invokeLater(() -> new SignupPage().setVisible(true));
    }
}

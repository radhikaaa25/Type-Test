// TipsPage.java
import javax.swing.*;
import java.awt.*;

public class TipsPage extends JFrame {
    public TipsPage() {
        setTitle("TypeTest - Typing Tips");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel tipsHeader = new JLabel("Typing Tips");
        tipsHeader.setFont(new Font("Arial", Font.BOLD, 18));
        tipsHeader.setHorizontalAlignment(SwingConstants.CENTER);
        tipsHeader.setForeground(new Color(0x0288D1));

        String[] tips = {
                "1. Maintain a good posture while typing.",
                "2. Use all your fingers, not just two!",
                "3. Keep your eyes on the screen, not on the keyboard.",
                "4. Practice regularly to improve speed and accuracy.",
                "5. Start slow; accuracy is more important than speed initially.",
                "6. Use online typing games to make practice fun!",
                "7. Familiarize yourself with the keyboard layout.",
                "8. Take breaks to avoid finger strain."
        };

        JPanel tipsPanel = new JPanel(new GridLayout(tips.length, 1, 5, 5));
        tipsPanel.setBackground(new Color(0xE1F5FE));

        for (String tip : tips) {
            JLabel tipLabel = new JLabel(tip);
            tipLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            tipLabel.setForeground(new Color(0x424242));
            tipsPanel.add(tipLabel);
        }

        JButton backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(0xFF7043));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();
        });

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(0xE1F5FE));
        mainPanel.add(tipsHeader, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(tipsPanel), BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        add(mainPanel);
    }
}

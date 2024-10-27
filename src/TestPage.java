import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TestPage extends JFrame {
    private Timer timer;
    private int timeElapsed = 0;
    private String userName;
    private String testText;
    private JTextArea typingArea;
    private JLabel timerLabel;

    public TestPage() {
        // Ask for player name at the start
        userName = JOptionPane.showInputDialog("Enter your name:");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Anonymous"; // Default name if input is empty
        }
        this.testText = Utils.getRandomText();  // Assume Utils.getRandomText() provides random text

        setTitle("TypeTest - Test");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timerLabel = new JLabel("Time: 0 seconds");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(new Color(0x388E3C));

        JLabel textLabel = new JLabel("<html><p style='width:400px;'>" + testText + "</p></html>");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        textLabel.setForeground(new Color(0x000000));

        typingArea = new JTextArea();
        typingArea.setFont(new Font("Arial", Font.PLAIN, 14));
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);

        // Start the timer when the typing area is clicked
        typingArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                startTimer();
            }
        });

        typingArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { checkCompletion(); }
            public void removeUpdate(DocumentEvent e) { checkCompletion(); }
            public void insertUpdate(DocumentEvent e) { checkCompletion(); }
        });

        JButton backButton = createBackButton();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(0xFFF8E1));
        panel.add(timerLabel, BorderLayout.NORTH);
        panel.add(textLabel, BorderLayout.CENTER);
        panel.add(new JScrollPane(typingArea), BorderLayout.SOUTH);
        panel.add(backButton, BorderLayout.WEST);

        add(panel);
    }

    private void startTimer() {
        if (timer == null || !timer.isRunning()) {
            timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timeElapsed++;
                    timerLabel.setText("Time: " + timeElapsed + " seconds");
                }
            });
            timer.start();
        }
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void checkCompletion() {
        String typedText = typingArea.getText().trim();
        if (typedText.equals(testText)) {
            stopTimer();
            int wpm = Utils.calculateWPM(typedText, timeElapsed);
            int errors = Utils.calculateErrors(testText, typedText);
            int totalWords = testText.split("\\s+").length;

            // Show the end page with results
            new EndPage(userName, wpm, errors, totalWords).setVisible(true);
            dispose();
        }
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(0xFF7043));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new LandingPage(userName).setVisible(true);
            dispose();
        });
        return backButton;
    }
}

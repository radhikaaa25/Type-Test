import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPage extends JFrame {
    private Timer timer;
    private int timeElapsed = 0;
    private boolean isTypingStarted = false;
    private String userName;
    private String testText;
    private JTextArea typingArea;
    private JLabel timerLabel;
    private JLabel wpmLabel;
    private JProgressBar progressBar;

    public TestPage(String userName) {
        this.userName = userName;
        this.testText = Utils.getRandomText(); // Random text for testing

        setTitle("TypeTest - Test");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel to hold all components with a background color
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(0x194C4B));

        // Timer label
        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(new Color(0x4CAF50)); // Green color

        // WPM label
        wpmLabel = new JLabel("WPM: 0");
        wpmLabel.setFont(new Font("Arial", Font.BOLD, 16));
        wpmLabel.setForeground(new Color(0x71C7D1)); // Yellow color
// Progress bar
        progressBar = new JProgressBar(0, testText.length());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0x163437)); // Green color for progress
        // Stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setOpaque(false);
        statsPanel.add(timerLabel);
        statsPanel.add(wpmLabel);
        statsPanel.add(progressBar);

        JLabel textLabel = new JLabel("<html><p style='width:400px; color:white; font-size:14px;'>" + testText + "</p></html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Typing area
        typingArea = new JTextArea(5, 20);
        typingArea.setFont(new Font("Consolas", Font.BOLD, 16));
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setBackground(new Color(0x7F3D4D6E, true));
        typingArea.setForeground(Color.WHITE);
        typingArea.setBorder(BorderFactory.createLineBorder(new Color(0x5CB8B8), 2));



        // Start timer when typing begins
        typingArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (!isTypingStarted) {
                    startTimer();
                    isTypingStarted = true;
                }
            }
        });

        // Real-time typing listener for progress and WPM calculation
        typingArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateProgressAndWPM(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateProgressAndWPM(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateProgressAndWPM(); }
        });

        // Submit with Enter key
        typingArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    submitTypingTest();
                }
            }
        });

        // Back button
        JButton backButton = createBackButton();

        // Arrange components in the main panel
        mainPanel.add(statsPanel, BorderLayout.SOUTH);
        mainPanel.add(textLabel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(typingArea), BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.WEST);

        // Add the main panel to the frame
        add(mainPanel);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timerLabel.setText("Time: " + timeElapsed + "s");
                updateWPM();
            }
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void updateProgressAndWPM() {
        int typedLength = typingArea.getText().length();
        progressBar.setValue(typedLength);
        updateWPM();
    }

    private void updateWPM() {
        int wpm = Utils.calculateWPM(typingArea.getText(), timeElapsed);
        wpmLabel.setText("WPM: " + wpm);
    }

    private void submitTypingTest() {
        stopTimer();
        int wpm = Utils.calculateWPM(typingArea.getText(), timeElapsed);
        int totalWords = Utils.countWords(testText);
        int errors=Utils.calculateErrors(testText,typingArea.getText());
        new EndPage(userName, wpm, errors, totalWords).setVisible(true);
        dispose();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestPage("User").setVisible(true));
    }
}

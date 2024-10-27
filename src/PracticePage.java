// PracticePage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PracticePage extends JFrame {
    private JTextArea practiceArea;
    private JTextArea inputArea;
    private JLabel wpmLabel;
    private JLabel errorLabel;
    private long startTime;

    public PracticePage() {
        setTitle("TypeTest - Practice");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel instructionsLabel = new JLabel("Practice Typing Below", JLabel.CENTER);
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        instructionsLabel.setForeground(new Color(0x0288D1));

        practiceArea = new JTextArea();
        practiceArea.setFont(new Font("Arial", Font.PLAIN, 14));
        practiceArea.setLineWrap(true);
        practiceArea.setWrapStyleWord(true);
        practiceArea.setEditable(false);

        // Sample practice text
        String practiceText = Utils.getRandomText();
        practiceArea.setText(practiceText);

        inputArea = new JTextArea();
        inputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    calculateResults();
                } else if (inputArea.getText().length() == 0) {
                    startTime = System.currentTimeMillis();  // Start timer on first input
                }
            }
        });

        wpmLabel = new JLabel("WPM: 0", JLabel.CENTER);
        errorLabel = new JLabel("Errors: 0", JLabel.CENTER);

        // Back to Menu button added directly
        JButton backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(0xFF7043));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xFFF8E1));
        panel.add(instructionsLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(practiceArea), BorderLayout.CENTER);
        panel.add(new JScrollPane(inputArea), BorderLayout.SOUTH);

        JPanel resultPanel = new JPanel(new GridLayout(2, 1));
        resultPanel.add(wpmLabel);
        resultPanel.add(errorLabel);

        panel.add(resultPanel, BorderLayout.EAST); // Add result panel to the right
        panel.add(backButton, BorderLayout.WEST); // Add button to the left

        add(panel);
    }

    private void calculateResults() {
        String typedText = inputArea.getText();
        String originalText = practiceArea.getText();

        long endTime = System.currentTimeMillis();
        int timeInSeconds = (int) ((endTime - startTime) / 1000);

        int wpm = Utils.calculateWPM(originalText, timeInSeconds);
        int errors = Utils.calculateErrors(originalText, typedText);

        wpmLabel.setText("WPM: " + wpm);
        errorLabel.setText("Errors: " + errors);
    }
}

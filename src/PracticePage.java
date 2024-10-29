import javax.swing.*;
import java.awt.*;

public class PracticePage extends JFrame {
    private JTextArea practiceArea;
    private JTextArea inputArea;
    private JLabel wpmLabel;
    private JLabel errorLabel;
    private long startTime;

    public PracticePage() {
        setTitle("TypeTest - Practice");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image setup
        JLabel background =new JLabel();
        background.setBackground(Color.BLACK);
        setContentPane(background);
        background.setLayout(new BorderLayout(10, 10));

        // Instructions Label
        JLabel instructionsLabel = new JLabel("Practice Typing Below", JLabel.CENTER);
        instructionsLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        instructionsLabel.setForeground(Color.black);

        // Text area for practice text with custom style
        practiceArea = new JTextArea();
        practiceArea.setFont(new Font("Serif", Font.PLAIN, 19));
        practiceArea.setLineWrap(true);
        practiceArea.setWrapStyleWord(true);
        practiceArea.setEditable(false);
        practiceArea.setForeground(Color.WHITE);
        practiceArea.setBackground(new Color(0, 0, 0, 180)); // Semi-transparent background

        String practiceText = Utils.getRandomText();
        practiceArea.setText(practiceText);

        // Text area for input with styling
        inputArea = new JTextArea();
        inputArea.setFont(new Font("Consolas", Font.BOLD, 19));
        inputArea.setLineWrap(true);
        inputArea.setRows(15);
        inputArea.setWrapStyleWord(true);
        inputArea.setBackground(new Color(225, 169, 139, 204));
        inputArea.setForeground(Color.BLACK);

        // Make the input area appear longer
        inputArea.setPreferredSize(new Dimension(600, 200)); // Wider and taller size
        inputArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    calculateResults();
                } else if (inputArea.getText().length() == 0) {
                    startTime = System.currentTimeMillis();  // Start timer on first input
                }
            }
        });

        // WPM and Errors labels
        wpmLabel = new JLabel("WPM: 0", JLabel.CENTER);
        errorLabel = new JLabel("Errors: 0", JLabel.CENTER);
        wpmLabel.setFont(new Font("Arial", Font.BOLD, 18));
        errorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        wpmLabel.setForeground(new Color(0x00C853)); // Green for WPM
        errorLabel.setForeground(new Color(0xFF1744)); // Red for Errors

        // Result Panel for WPM and Errors
        JPanel resultPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        resultPanel.setBackground(new Color(0, 0, 0, 100));
        resultPanel.add(wpmLabel);
        resultPanel.add(errorLabel);

        // Scroll panels for text areas
        JScrollPane practiceScrollPane = new JScrollPane(practiceArea);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        practiceScrollPane.setBorder(BorderFactory.createTitledBorder("Practice Text"));
        inputScrollPane.setBorder(BorderFactory.createTitledBorder("Type Here"));

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(0xFF7043));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();
        });

        // Panel for layout organization
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setOpaque(false); // Make transparent to show background
        mainPanel.add(instructionsLabel, BorderLayout.NORTH);
        mainPanel.add(practiceScrollPane, BorderLayout.CENTER);

        // Adding extra padding around the input panel for enhanced appearance
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.SOUTH); // Add inputPanel with padding to mainPanel

        // Add result panel and back button to the main panel
        JPanel sidePanel = new JPanel(new BorderLayout(10, 10));
        sidePanel.setOpaque(false);
        sidePanel.add(resultPanel, BorderLayout.CENTER);
        sidePanel.add(backButton, BorderLayout.SOUTH);

        // Add to the main layout
        background.add(mainPanel, BorderLayout.CENTER);
        background.add(sidePanel, BorderLayout.EAST);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PracticePage().setVisible(true));
    }
}

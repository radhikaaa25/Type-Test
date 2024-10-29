import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class LeaderboardPage extends JFrame {
    public LeaderboardPage() {
        setTitle("TypeTest - Leaderboard");
        setSize(500, 600); // Adjusted for better spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create an ImagePanel with a background image
        ImagePanel panel = new ImagePanel("C:\\Users\\smile\\Downloads\\demo\\Type-Test\\src\\key.gif"); // Update with your image path
        panel.setLayout(new BorderLayout());

        // Set up leaderboard table
        String[] columnNames = {"Rank", "Player", "WPM"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isOpaque() {
                return false; // Make the table transparent
            }
        };

        // Set table styling
        table.setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(30);
        table.setBackground(new Color(255, 255, 255, 150)); // Semi-transparent white background
        table.setForeground(new Color(0x880E4F)); // Dark text color
        table.setShowVerticalLines(false);

        // Center-align table data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Customize header font, color, and alignment
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16)); // Bolder font for header
        header.setForeground(new Color(0x51022E)); // White text color
        header.setBackground(new Color(0x880E4F)); // Dark background color
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // Fetch and display scores
        updateLeaderboard(tableModel);

        // Add scroll pane with size adjustments
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(350, 200)); // Set smaller dimensions for the table
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Transparent viewport
        panel.add(scrollPane, BorderLayout.CENTER); // Add table to the center

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(0xCC7E235B, true));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();
        });

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0x524B4B));
        buttonPanel.add(backButton); // Add button to the panel

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        // Add the main panel to the frame
        add(panel);
        getContentPane().setBackground(new Color(0x524B4B)); // Set content background color
    }

    private void updateLeaderboard(DefaultTableModel tableModel) {
        List<Leaderboard.Score> scores = Leaderboard.getScores();
        tableModel.setRowCount(0); // Clear existing rows
        for (int i = 0; i < scores.size(); i++) {
            Leaderboard.Score score = scores.get(i);
            tableModel.addRow(new Object[]{i + 1, score.getName(), score.getWpm()});
        }
    }

    // Custom JPanel to set the background image
    class ImagePanel extends JPanel {
        private Image backgroundImage;

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
        SwingUtilities.invokeLater(LeaderboardPage::new);
    }
}

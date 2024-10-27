// LeaderboardPage.java
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LeaderboardPage extends JFrame {
    public LeaderboardPage() {
        setTitle("TypeTest - Leaderboard");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up leaderboard table
        String[] columnNames = {"Rank", "Player", "WPM"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Set table styling
        table.setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(30);
        table.setBackground(new Color(0xFCE4EC));
        table.setForeground(new Color(0x880E4F));
        table.setShowVerticalLines(false);

        // Center-align table data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Fetch and display scores
        updateLeaderboard(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Add table to center

        // Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(0xFF7043));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new LandingPage("").setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();  // Panel for the button
        buttonPanel.setBackground(new Color(0xFCE4EC));
        buttonPanel.add(backButton);  // Add button to the panel

        add(buttonPanel, BorderLayout.SOUTH);  // Add button panel to the bottom

        getContentPane().setBackground(new Color(0xFCE4EC));
    }

    private void updateLeaderboard(DefaultTableModel tableModel) {
        List<Leaderboard.Score> scores = Leaderboard.getScores();
        tableModel.setRowCount(0);  // Clear existing rows
        for (int i = 0; i < scores.size(); i++) {
            Leaderboard.Score score = scores.get(i);
            tableModel.addRow(new Object[]{i + 1, score.getName(), score.getWpm()});
        }
    }
}

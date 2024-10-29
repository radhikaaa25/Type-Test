
import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        try {
            // Set Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            String userName = JOptionPane.showInputDialog(null, "Enter your name:");
            new LandingPage(userName).setVisible(true);
        });
    }
}

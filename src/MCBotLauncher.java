import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MCBotLauncher {

    private static Process mcBotProcess;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MCBotLauncher::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("control", new Color(25, 25, 25));
                    UIManager.put("info", new Color(25, 25, 25));
                    UIManager.put("nimbusBase", new Color(18, 30, 49));
                    UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
                    UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
                    UIManager.put("nimbusFocus", new Color(115, 164, 209));
                    UIManager.put("nimbusGreen", new Color(176, 179, 50));
                    UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
                    UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
                    UIManager.put("nimbusOrange", new Color(191, 98, 4));
                    UIManager.put("nimbusRed", new Color(169, 46, 34));
                    UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
                    UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
                    UIManager.put("text", new Color(230, 230, 230));
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to the default look and feel.
        }

        JFrame frame = new JFrame("MCBot Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null); // Center the frame

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel ipPortLabel = new JLabel("IP:PORT:");
        ipPortLabel.setForeground(Color.WHITE);
        JTextField ipPortField = new JTextField();

        JLabel protocolLabel = new JLabel("Protocol:");
        protocolLabel.setForeground(Color.WHITE);
        JComboBox<String> protocolBox = new JComboBox<>(new String[]{
                "754 (1.16.5)", "755 (1.17)", "756 (1.17.1)", "757 (1.18)", 
                "758 (1.18.1)", "759 (1.18.2)", "760 (1.19)", "761 (1.19.1)", 
                "762 (1.19.2)", "763 (1.19.3)", "764 (1.19.4)", "765 (1.20)"
        });

        JLabel methodLabel = new JLabel("Method:");
        methodLabel.setForeground(Color.WHITE);
        JComboBox<String> methodBox = new JComboBox<>(new String[]{
                "bigpacket", "botjoiner", "doublejoin", "emptypacket", 
                "gayspam", "handshake", "invaliddata", "invalidspoof", 
                "invalidnames", "spoof", "join", "legacyping", "legitnamejoin", 
                "localhost", "pingjoin", "longhost", "longnames", "nullping", 
                "ping", "query", "randompacket", "bighandshake", 
                "unexpectedpacket", "memory", "test"
        });

        JLabel secondsLabel = new JLabel("Seconds:");
        secondsLabel.setForeground(Color.WHITE);
        JTextField secondsField = new JTextField();

        JLabel cpsLabel = new JLabel("CPS:");
        cpsLabel.setForeground(Color.WHITE);
        JTextField cpsField = new JTextField();

        JButton runButton = new JButton("Run");
        JButton stopButton = new JButton("Stop");

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(ipPortLabel, gbc);

        gbc.gridx = 1;
        frame.add(ipPortField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(protocolLabel, gbc);

        gbc.gridx = 1;
        frame.add(protocolBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(methodLabel, gbc);

        gbc.gridx = 1;
        frame.add(methodBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(secondsLabel, gbc);

        gbc.gridx = 1;
        frame.add(secondsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(cpsLabel, gbc);

        gbc.gridx = 1;
        frame.add(cpsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        frame.add(runButton, gbc);

        gbc.gridy = 6;
        frame.add(stopButton, gbc);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipPort = ipPortField.getText();
                String protocol = protocolBox.getSelectedItem().toString().split(" ")[0];
                String method = methodBox.getSelectedItem().toString();
                String seconds = secondsField.getText();
                String cps = cpsField.getText();

                try {
                    runMCBot(ipPort, protocol, method, seconds, cps);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error running MCBot: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMCBot();
            }
        });

        frame.setVisible(true);
    }

    private static void runMCBot(String ipPort, String protocol, String method, String seconds, String cps) throws Exception {
        // Extract MCBOT.jar and proxies.txt to a temporary directory
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "mcbot");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        File mcbotJar = new File(tempDir, "MCBOT.jar");
        extractResource("MCBOT.jar", mcbotJar);

        File proxiesTxt = new File(tempDir, "proxies.txt");
        extractResource("proxies.txt", proxiesTxt);

        // Build the command
        String command = String.format("java -jar %s %s %s %s %s %s", mcbotJar.getAbsolutePath(), ipPort, protocol, method, seconds, cps);
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.directory(tempDir); // Set the working directory
        processBuilder.inheritIO(); // Show the output in the console
        mcBotProcess = processBuilder.start();
    }

    private static void stopMCBot() {
        if (mcBotProcess != null) {
            mcBotProcess.destroy();
            mcBotProcess = null;
            JOptionPane.showMessageDialog(null, "MCBot stopped successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void extractResource(String resourceName, File destination) throws IOException {
        try (InputStream inputStream = MCBotLauncher.class.getResourceAsStream("/" + resourceName);
             FileOutputStream outputStream = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}

package renju;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RuleWindow extends JFrame {
    public RuleWindow() {
        setTitle("Renju Game Rules");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setEditable(false);
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        try (BufferedReader reader = new BufferedReader(new FileReader("renju_rules.txt"))) {
            StringBuilder rulesContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                rulesContent.append(line).append("\n");
            }
            rulesTextArea.setText(rulesContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
            rulesTextArea.setText("Error loading rules: " + e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(rulesTextArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RuleWindow ruleWindow = new RuleWindow();
            ruleWindow.setVisible(true);
        });
    }
}

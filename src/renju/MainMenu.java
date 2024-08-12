package renju;	
import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MainMenu {
	private JFrame frame;
	
	public MainMenu() {
        frame = new JFrame("Renju Game - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);

        JLabel greetingLabel = new JLabel("Welcome to Renju Game!");
        greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        greetingLabel.setBounds(50, 20, 200, 30);

        JButton startGameButton = new JButton("Start Game");
        JButton viewMoveHistoryButton = new JButton("View Move History");
        JButton rulesButton = new JButton("View Rules");

        startGameButton.setBounds(50, 70, 200, 30);
        viewMoveHistoryButton.setBounds(50, 120, 200, 30);
        rulesButton.setBounds(50, 170, 200, 30);

        startGameButton.addActionListener(e -> startGame());
        viewMoveHistoryButton.addActionListener(e -> viewMoveHistory());
        rulesButton.addActionListener(e -> openRuleWindow());

        frame.add(greetingLabel);
        frame.add(startGameButton);
        frame.add(viewMoveHistoryButton);
        frame.add(rulesButton);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void startGame() {
    	frame.dispose();
        new RenjuGUI(Stone.BLACK); // Start the game directly with a default color (e.g., black)
    }

    private void viewMoveHistory() {
        try {
            File moveHistoryFile = new File("renju_moves.txt");

            if (!moveHistoryFile.exists()) {
                JOptionPane.showMessageDialog(null, "Move history file not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Desktop.getDesktop().open(moveHistoryFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error opening move history file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void openRuleWindow() {
        RuleWindow ruleWindow = new RuleWindow();
        ruleWindow.setVisible(true);
    }
    
    public RuleWindow RuleWindowTest() {
        RuleWindow ruleWindow = new RuleWindow();
        ruleWindow.setVisible(true);
        return ruleWindow;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}

package renju;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RenjuGUI extends JFrame {
    public RenjuGame game;
    private JLabel turnLabel;
    
    public RenjuGUI(Stone playerColor) {
        game = new RenjuGame();

        setTitle("Renju Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLayout(null);

        RenjuBoard renjuBoard = new RenjuBoard();
        renjuBoard.addMouseListener(new BoardMouseListener());
        renjuBoard.setBounds(10, 10, 450, 450);
        add(renjuBoard);
        
        JButton passButton = new JButton("Pass");
        passButton.addActionListener(e -> handlePass());
        passButton.setBounds(200, 470, 100, 30);
        add(passButton);
        
        turnLabel = new JLabel();
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setBounds(100, 470, 100, 30);
        updateTurnLabel(); // Update the label initially
        add(turnLabel);

        setVisible(true);
        game.isPlayer1Turn = (playerColor == Stone.BLACK);
    }
    

    public class RenjuBoard extends JPanel {
        protected int CELL_SIZE = 30; // Size of each cell on the board

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBoard(g);
            drawStones(g);
        }

        private void drawBoard(Graphics g) {
            g.setColor(new Color(139, 69, 19)); // Brown color for board
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.BLACK);
            for (int i = 0; i < game.BOARD_SIZE; i++) {
                g.drawLine(0, i * CELL_SIZE, game.BOARD_SIZE * CELL_SIZE, i * CELL_SIZE);
                g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, game.BOARD_SIZE * CELL_SIZE);
            }
        }

        private void drawStones(Graphics g) {
            for (int i = 0; i < game.BOARD_SIZE; i++) {
                for (int j = 0; j < game.BOARD_SIZE; j++) {
                    if (game.board[i][j] == Stone.BLACK) {
                        g.setColor(Color.BLACK);
                        g.fillOval(j * CELL_SIZE + 2, i * CELL_SIZE + 2, CELL_SIZE - 4, CELL_SIZE - 4);
                    } else if (game.board[i][j] == Stone.WHITE) {
                        g.setColor(Color.WHITE);
                        g.fillOval(j * CELL_SIZE + 2, i * CELL_SIZE + 2, CELL_SIZE - 4, CELL_SIZE - 4);
                    }
                }
            }
        }
    }
    
    private void handlePass() {
        if (!game.isGameOver()) {
            game.isPlayer1Turn = !game.isPlayer1Turn;
            updateTurnLabel();
        }
    }
    private void updateTurnLabel() {
        if (game.isPlayer1Turn) {
            turnLabel.setText("Blacks Turn");
        } else {
            turnLabel.setText("Whites Turn");
        }
    }
    
    
    private class BoardMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int mouseX = e.getX() / 30; // Assuming CELL_SIZE = 30
            int mouseY = e.getY() / 30;

            if (!game.isGameOver() && game.placeStone(mouseY, mouseX, game.isPlayer1Turn ? Stone.BLACK : Stone.WHITE)) {
                repaint(); // Update the board
                updateTurnLabel();
                if (game.isGameOver()) {
                    int option = JOptionPane.showConfirmDialog(null, "Game Over! Winner: " + game.winner +
                            "\nDo you want to save the move history into a file?", "Save Moves", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        game.saveMoveHistoryToFile(); // Save moves if the user chooses to
                    }
                    dispose();
                    new MainMenu();
                }
            }
        }
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new RenjuGUI(Stone.WHITE));
    	SwingUtilities.invokeLater(MainMenu::new);
    }
}

package renju;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RenjuGame {
    public int BOARD_SIZE = 15;
    public Stone[][] board;
    public boolean isPlayer1Turn;
    public boolean gameOver;
    public Stone winner;
    private final int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {1, -1}}; // Horizontal, Vertical, Diagonal, Anti-diagonal
    private ArrayList<int[]> moveHistory; // Collection to store moves
    private String fileName = "renju_moves.txt";
    
    public RenjuGame() {
        board = new Stone[BOARD_SIZE][BOARD_SIZE];
        // Initialize the board with EMPTY stones
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Stone.EMPTY;
            }
        }
        isPlayer1Turn = true;
        gameOver = false;
        winner = Stone.EMPTY;
        moveHistory = new ArrayList<>(); // Initialize move history
    }

    public boolean placeStone(int row, int col, Stone player) {
        if (gameOver || row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE || board[row][col] != Stone.EMPTY) {
            return false; // Invalid move
        }

        if (isPlayer1Turn) {
            // Check for double three for starting player (Black)
            board[row][col] = player;
            boolean isDoubleThree = checkForDoubleThree(row, col, player);
            boolean isDoubleFour = checkForDoubleFour(row, col, player);
            board[row][col] = Stone.EMPTY; // Reset the move
            if (isDoubleThree || isDoubleFour) {
                return false; // Prevent double three and double four for starting player
            }
        }

        board[row][col] = player;

        moveHistory.add(new int[]{row, col}); // Store the move

        if (checkWin(row, col, player)) {
            gameOver = true;
            winner = player;
        }

        isPlayer1Turn = !isPlayer1Turn;
        return true; // Move successful
    }
    
    
    private int countConsecutiveStones(int row, int col, int deltaX, int deltaY, Stone stone) {
        int count = 0;
        while (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == stone) {
            count++;
            row += deltaX;
            col += deltaY;
        }
        return count;
    }

     private boolean checkWin(int row, int col, Stone player) {
        for (int[] dir : directions) {
            int deltaX = dir[0];
            int deltaY = dir[1];

            int count = countConsecutiveStones(row, col, deltaX, deltaY, player);
            count += countConsecutiveStones(row, col, -deltaX, -deltaY, player) - 1;

            if (count == 5) {
                return true; // There's a winning configuration
            }
            if (player == Stone.BLACK && count >= 6) {
                gameOver = true;
                winner = Stone.WHITE;
                return false;
            }
        }

        return false;
    }
    

    private boolean checkForDoubleThree(int row, int col, Stone player) {
        // Simulate placing a stone and check for double three
        board[row][col] = player;

        // Count patterns of three stones in different directions
        int patternCount = 0;
        for (int[] dir : directions) {
            int deltaX = dir[0];
            int deltaY = dir[1];

            int count = countConsecutiveStones(row, col, deltaX, deltaY, player);
            count += countConsecutiveStones(row, col, -deltaX, -deltaY, player) - 1;

            if (count == 3) {
                patternCount++;
            }
        }
        board[row][col] = Stone.EMPTY; // Reset the move

        // Check if double three condition is met
        return patternCount >= 2;
    }
    
    private boolean checkForDoubleFour(int row, int col, Stone player) {
        // Simulate placing a stone and check for double four
        board[row][col] = player;

        // Count patterns of four stones in different directions
        int patternCount = 0;
        for (int[] dir : directions) {
            int deltaX = dir[0];
            int deltaY = dir[1];

            int count = countConsecutiveStones(row, col, deltaX, deltaY, player);
            count += countConsecutiveStones(row, col, -deltaX, -deltaY, player) - 1;

            if (count == 4) {
                patternCount++;
            }
        }

        board[row][col] = Stone.EMPTY; // Reset the move

        // Check if double four condition is met
        return patternCount >= 2;
    }

    
    public void saveMoveHistoryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < moveHistory.size(); i++) {
                int[] move = moveHistory.get(i);
                writer.write("(" + move[0] + "," + move[1] + ")");
                writer.newLine();
            }
            writer.flush();
            System.out.println("Moves saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<int[]> getMoveHistory() {
        return moveHistory; // Retrieve move history
    }
    

    public boolean isGameOver() {
        return gameOver;
    }
}

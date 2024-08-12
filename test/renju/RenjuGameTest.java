package renju;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class RenjuGameTest {
    @Test
    public void testMovesSavedIntoFile() {
        RenjuGame game = new RenjuGame();

        // Perform moves
        game.placeStone(0, 0, Stone.BLACK);
        game.placeStone(1, 1, Stone.WHITE);
        game.placeStone(2, 2, Stone.BLACK);

        game.saveMoveHistoryToFile(); // Save moves to file

        // Read the file and check if moves are correctly saved
        try (BufferedReader reader = new BufferedReader(new FileReader("renju_moves.txt"))) {
            String line;
            int moveCount = 0;
            while ((line = reader.readLine()) != null) {
                moveCount++;
            }
            assertEquals(3, moveCount); // Check if three moves were saved (adjust according to your move count)
        } catch (IOException e) {
            fail("Exception occurred while reading file: " + e.getMessage());
        }
    }


    @Test
    public void testDoubleThree() {
        RenjuGame game = new RenjuGame();

        // Place stones to create a double three scenario
        game.placeStone(7, 8, Stone.BLACK);
        game.placeStone(1, 1, Stone.WHITE);
        game.placeStone(7, 9, Stone.BLACK);
        game.placeStone(0, 1, Stone.WHITE);
        game.placeStone(8, 7, Stone.BLACK);
        game.placeStone(1, 2, Stone.WHITE);
        game.placeStone(9, 7, Stone.BLACK);
        game.placeStone(0, 0, Stone.WHITE);

        // Check if the double three condition is met
        assertFalse(game.placeStone(7, 7, Stone.BLACK)); // Try placing a stone to create a double three
    }

    @Test
    public void testDoubleFour() {
        RenjuGame game = new RenjuGame();

        // Place stones to create a double four scenario
        game.placeStone(7, 8, Stone.BLACK);
        game.placeStone(1, 1, Stone.WHITE);
        game.placeStone(7, 9, Stone.BLACK);
        game.placeStone(0, 1, Stone.WHITE);
        game.placeStone(8, 7, Stone.BLACK);
        game.placeStone(1, 2, Stone.WHITE);
        game.placeStone(9, 7, Stone.BLACK);
        game.placeStone(0, 0, Stone.WHITE);	
        game.placeStone(10, 7, Stone.BLACK);
        game.placeStone(12, 14, Stone.WHITE);
        game.placeStone(7, 10, Stone.BLACK);
        game.placeStone(1, 0, Stone.WHITE);
        
        // Check if the double four condition is met
        assertFalse(game.placeStone(7, 7, Stone.BLACK)); // Try placing a stone to create a double four
    }
}


package renju;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.junit.Test;

import renju.RenjuGUI.RenjuBoard;

public class RenjuGUITest {

	@Test
	public void testRenjuGUIInitialization() {
		RenjuGUI renjuGUI = new RenjuGUI(Stone.BLACK);

		assertNotNull(renjuGUI);
	}

	@Test
    public void testRenjuBoardPresence() {
        RenjuGUI renjuGUI = new RenjuGUI(Stone.BLACK);
        assertNotNull(renjuGUI.getContentPane().getComponent(0)); // Check if the first component is the Renju board
    }

    @Test
    public void testPassButtonPresence() {
        RenjuGUI renjuGUI = new RenjuGUI(Stone.BLACK);
        assertNotNull(renjuGUI.getContentPane().getComponent(1)); // Check if the second component is the Pass button
    }
    
    @Test
    public void testInitialPlayerTurn() {
        RenjuGUI renjuGUI = new RenjuGUI(Stone.BLACK);
        RenjuGame game = renjuGUI.game;
        assertEquals(Stone.BLACK, game.isPlayer1Turn ? Stone.BLACK : Stone.WHITE); // Assuming BLACK is the initial player
    }
    
    @Test
    public void testGameOverStateAfterPass() {
        RenjuGUI renjuGUI = new RenjuGUI(Stone.BLACK);
        RenjuGame game = renjuGUI.game;
        JButton passButton = (JButton) renjuGUI.getContentPane().getComponent(1); // Assuming Pass button is the second component
        passButton.doClick(); // Simulate clicking the Pass button

        assertFalse(game.isGameOver());
    }

    
}

package renju;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

public class MainMenuTest {
	
	@Test
	public void testMainMenuInitialization() {
        MainMenu mainMenu = new MainMenu();
        assertNotNull(mainMenu);
    }
	
	@Test
    public void testRulesWindowNotNull() {
		MainMenu mainMenu = new MainMenu();
        RuleWindow rulesWindow = mainMenu.RuleWindowTest();
        assertNotNull(rulesWindow);
    }

}

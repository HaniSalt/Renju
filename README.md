# Renju Game Project

This is a simple implementation of the [Renju](https://en.wikipedia.org/wiki/Renju) board game (a variation of Gomoku) using Java Swing for the graphical user interface.

## Description

The project provides a playable Renju game on a 15x15 board where two players (Black and White) take turns placing their stones. The goal is to get five stones in a row (horizontally, vertically, or diagonally). 
This implementation includes specific Renju rules, such as restrictions for the Black player (no double threes, no double fours, no overlines) and the ability to pass a turn.

## Features

* **Graphical User Interface:** A Swing-based GUI for game interaction.
* **15x15 Board:** Standard Renju board size.
* **Two Players:** Black and White take turns placing stones (local multiplayer).
* **Turn Indication:** Displays whose turn it is (Black or White).
* **Win Condition:** Detects when a player achieves five stones in a row.
* **Renju Rules:**
    * Black cannot place a stone that simultaneously creates two open lines of three stones ("double three").
    * Black cannot place a stone that simultaneously creates two open lines of four stones ("double four").
    * If Black forms a line of six or more stones ("overline"), Black loses (White wins).
* **Pass Turn:** Allows a player to pass their turn.
* **Move History:**
    * Records the coordinates of each move made during a game.
    * Prompts the user to save the move history to a file (`renju_moves.txt`) at the end of the game.
    * Option to view the saved move history file from the main menu.
* **Game Rules Display:** Option to view the game rules loaded from an external file (`renju_rules.txt`).
* **Main Menu:** Simple menu to Start Game, View Move History, or View Rules.

## Files

* `mainmenu.java`: Implements the main menu window, providing options to start the game, view history, or view rules. Acts as the entry point for the application.
  ![image](https://github.com/user-attachments/assets/24f8bc93-d0d9-4eda-889b-52a2d59f2873)

* `renjugui.java`: Creates the main game window, including the board display, turn label, and pass button. Handles user clicks on the board to place stones.
* `renjugame.java`: Contains the core game logic, including board state management, placing stones, checking for valid moves (including Renju restrictions), detecting win conditions, tracking turns, and managing move history saving/retrieval.
* `RuleWindow.java`: A simple window class that displays the contents of the `renju_rules.txt` file.
* `stone.java`: An enum defining the possible states for each position on the board (`EMPTY`, `BLACK`, `WHITE`).
  ![image](https://github.com/user-attachments/assets/62215459-0e22-4790-9130-7e64ba38fe32)

* `renju_rules.txt` (External): A text file containing the rules of Renju. **This file must be present in the same directory as the compiled classes for the "View Rules" feature to work.**
* `renju_moves.txt` (Generated): A text file where the move history is saved if the user chooses to do so at the end of a game.

## Prerequisites

* Java Development Kit (JDK) to compile the source code.
* Java Runtime Environment (JRE) to run the game.
* The `renju_rules.txt` file must be present in the root directory where you run the game.

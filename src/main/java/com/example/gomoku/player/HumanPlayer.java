package com.example.gomoku.player;

import java.util.Scanner;

/**
 * Represents a human player in the Gomoku game.
 * <p>
 * This class implements the {@link Player} interface. It allows a human
 * to enter moves via the command line.
 */
public class HumanPlayer implements Player {
    private final char symbol;
    private final Scanner scanner;
    /**
     * Creates a new human player with a symbol and input scanner.
     *
     * @param symbol  The character representing this player on the board.
     * @param scanner Scanner object used to read input from the user.
     */
    public HumanPlayer(char symbol, Scanner scanner) {
        this.symbol = symbol;
        this.scanner = scanner;
    }

    /**
     * Returns the symbol representing this player.
     *
     * @return the player symbol (e.g., 'X' or 'O').
     */
    @Override
    public char getSymbol() {
        return symbol;
    }

    /**
     * Prompts the user to enter a move.
     * <p>
     * The move should be entered as "row col" (space-separated integers).
     * This method loops until valid input is provided.
     *
     * @return an integer array of size 2 containing the row and column of the move.
     */
    @Override
    public int[] getMove() {
        while (true) {
            System.out.print("Enter move as 'row col': ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid input, try again.");
                continue;
            }
            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                return new int[]{row, col};
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers, try again.");
            }
        }
    }
}

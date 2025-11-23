package com.example.gomoku.player;

import java.util.Random;

/**
 * Represents an AI player that makes random moves in the Gomoku game.
 * <p>
 * This player randomly selects a row and column for its move.
 */
public class RandomAIPlayer implements Player {
    private final char symbol;
    private final Random rand = new Random();

    /**
     * Constructs a RandomAIPlayer with the given symbol.
     *
     * @param symbol the character representing this player on the board
     */
    public RandomAIPlayer(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representing this player on the board.
     *
     * @return the character symbol of the player
     */
    @Override
    public char getSymbol() {
        return symbol;
    }

    /**
     * Generates a random move for this AI player.
     * <p>
     * Note: Currently, it chooses numbers between 0 and 9.
     * You can adjust this later to match the board size dynamically.
     *
     * @return an array [row, col] representing the AI's move
     */
    @Override
    public int[] getMove() {
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);
        return new int[]{row, col};
    }
}

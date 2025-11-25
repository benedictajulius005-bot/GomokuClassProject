package com.example.gomoku.player;

import java.util.Random;

/**
 * Represents an AI player that makes random moves
 * based on the actual board size.
 */
public class RandomAIPlayer implements Player {

    private final char symbol;
    private final int rows;
    private final int cols;
    private final Random rand = new Random();

    /**
     * Constructs an AI player that knows the board size.
     *
     * @param symbol the player's symbol (e.g., 'O')
     * @param rows total number of rows on the board
     * @param cols total number of columns on the board
     */
    public RandomAIPlayer(char symbol, int rows, int cols) {
        this.symbol = symbol;
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    /**
     * Generates a random valid move within the board limits.
     *
     * @return an array {row, col}
     */
    @Override
    public int[] getMove() {
        int row = rand.nextInt(rows);
        int col = rand.nextInt(cols);
        return new int[]{row, col};
    }
}

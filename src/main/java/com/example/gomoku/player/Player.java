package com.example.gomoku.player;

/**
 * Represents a player in the Gomoku game.
 * <p>
 * This interface defines the methods that any type of player
 * (human or AI) must implement.
 */
public interface Player {

    /**
     * Returns the symbol representing this player on the board.
     * For example, 'X' or 'O'.
     *
     * @return the character symbol of the player
     */
    char getSymbol();

    /**
     * Returns the next move of this player.
     * <p>
     * For a human player, this might read input from the console.
     * For an AI player, it might calculate a random or strategic move.
     *
     * @return an array of two integers: [row, col] representing the move
     */
    int[] getMove();
}

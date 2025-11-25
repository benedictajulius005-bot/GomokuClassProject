package com.example.gomoku.model;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the game board for Gomoku.
 * <p>
 * Handles board creation, move placement, checking for winners,
 * and serialization for saving/loading.
 */
public class Board {

    /** Number of rows in the board. */
    private final int rows;

    /** Number of columns in the board. */
    private final int cols;

    /** 2D array representing the board grid. Empty cells are '.'. */
    private final char[][] grid;

    /** Counter for the total moves made so far. */
    private int movesMade = 0;

    /**
     * Constructs a new board with the specified dimensions.
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        for (char[] row : grid) Arrays.fill(row, '.');
    }

    /**
     * Places a player's symbol on the board at the specified position.
     *
     * @param r the row index
     * @param c the column index
     * @param playerSymbol the symbol representing the player
     * @return {@code true} if the move was successful, {@code false} if invalid
     */
    public boolean place(int r, int c, char playerSymbol) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return false;
        if (grid[r][c] != '.') return false;
        grid[r][c] = playerSymbol;
        movesMade++;
        return true;
    }

    /**
     * Checks if there is a winner on the board.
     *
     * @return an Optional containing the winning player's symbol, or empty if no winner
     */
    public Optional<Character> winner() {
        int[] dr = {0, 1, 1, -1};
        int[] dc = {1, 0, 1, 1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char s = grid[r][c];
                if (s == '.') continue;

                for (int d = 0; d < 4; d++) {
                    int cnt = 1;
                    int rr = r + dr[d], cc = c + dc[d];

                    while (inBounds(rr, cc) && grid[rr][cc] == s) {
                        cnt++;
                        rr += dr[d];
                        cc += dc[d];
                    }

                    if (cnt >= 5) return Optional.of(s);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Checks whether a given position is within the board bounds.
     *
     * @param r row index
     * @param c column index
     * @return {@code true} if the position is inside the board, {@code false} otherwise
     */
    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    /**
     * Checks whether the board is completely filled.
     *
     * @return {@code true} if no empty cells remain, {@code false} otherwise
     */
    public boolean isFull() {
        return movesMade >= rows * cols;
    }

    /**
     * Returns a deep copy of the current board grid.
     *
     * @return a 2D character array representing the board
     */
    public char[][] getGridCopy() {
        char[][] copy = new char[rows][cols];
        for (int i = 0; i < rows; i++) System.arraycopy(grid[i], 0, copy[i], 0, cols);
        return copy;
    }

    /**
     * Converts the board to a string suitable for saving to a file or database.
     *
     * @return serialized board string
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append(grid[i]);
            if (i < rows - 1) sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Restores a Board object from a serialized string.
     *
     * @param s the serialized board string
     * @return a Board object representing the same state
     */
    public static Board deserialize(String s) {
        String[] lines = s.split("\n");
        int r = lines.length;
        int c = lines[0].length();
        Board b = new Board(r, c);
        for (int i = 0; i < r; i++) {
            b.grid[i] = lines[i].toCharArray();
        }
        return b;
    }

    /**
     * Returns a human-readable string representation of the board.
     *
     * @return the board as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) sb.append(row).append('\n');
        return sb.toString();
    }
}

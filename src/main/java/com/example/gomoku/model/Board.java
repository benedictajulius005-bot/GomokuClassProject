package com.example.gomoku.model;

import java.util.Arrays;
import java.util.Optional;

public class Board {
    private final int rows;
    private final int cols;
    private final char[][] grid;
    private int movesMade = 0;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        for (char[] row : grid) Arrays.fill(row, '.');
    }

    public boolean place(int r, int c, char playerSymbol) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return false;
        if (grid[r][c] != '.') return false;
        grid[r][c] = playerSymbol;
        movesMade++;
        return true;
    }

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
                        cnt++; rr += dr[d]; cc += dc[d];
                    }
                    if (cnt >= 5) return Optional.of(s);
                }
            }
        }
        return Optional.empty();
    }

    private boolean inBounds(int r, int c) { return r >= 0 && r < rows && c >= 0 && c < cols; }

    public boolean isFull() { return movesMade >= rows * cols; }

    public char[][] getGridCopy() {
        char[][] copy = new char[rows][cols];
        for (int i = 0; i < rows; i++) System.arraycopy(grid[i], 0, copy[i], 0, cols);
        return copy;
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append(grid[i]);
            if (i < rows - 1) sb.append('\n');
        }
        return sb.toString();
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) sb.append(row).append('\n');
        return sb.toString();
    }
}

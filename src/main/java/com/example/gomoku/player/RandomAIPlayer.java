package com.example.gomoku.player;

import java.util.Random;

public class RandomAIPlayer implements Player {
    private final char symbol;
    private final Random rand = new Random();

    public RandomAIPlayer(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public int[] getMove() {
        int row = rand.nextInt(10); // you can adjust board size dynamically later
        int col = rand.nextInt(10);
        return new int[]{row, col};
    }
}

package com.example.gomoku.player;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final char symbol;      // ✅ declare final field
    private final Scanner scanner;  // ✅ scanner to read input

    public HumanPlayer(char symbol, Scanner scanner) {
        this.symbol = symbol;       // ✅ initialize the symbol
        this.scanner = scanner;
    }

    @Override
    public char getSymbol() {
        return symbol;              // ✅ now it's safe to use
    }

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

package com.example.gomoku.game;

import com.example.gomoku.model.Board;
import com.example.gomoku.player.Player;

import java.util.Optional;

public class Game {
    private final Board board;

    public Game(int rows, int cols) {
        this.board = new Board(rows, cols);
    }

    public void start(Player p1, Player p2) {
        Player current = p1;
        while (true) {
            System.out.println(board);
            System.out.println("Player " + current.getSymbol() + "'s turn");

            int[] move = current.getMove();
            int row = move[0];
            int col = move[1];

            if (!board.place(row, col, current.getSymbol())) {
                System.out.println("Invalid move, try again.");
                continue;
            }

            Optional<Character> win = board.winner();
            if (win.isPresent()) {
                System.out.println(board);
                System.out.println("Player " + win.get() + " wins!");
                break;
            }

            if (board.isFull()) {
                System.out.println(board);
                System.out.println("It's a draw!");
                break;
            }

            current = (current == p1) ? p2 : p1;
        }
    }

    public void saveCurrentBoard(String name) {
        // You can add DB saving logic later
        System.out.println("Saving board to DB as '" + name + "'");
    }
}

package com.example.gomoku.game;

import com.example.gomoku.model.Board;
import com.example.gomoku.player.Player;
import com.example.gomoku.util.DbManager;

import java.util.Optional;

/**
 * Represents a full Gomoku game session between two players.
 * <p>
 * The {@code Game} class manages:
 * <ul>
 *     <li>Game initialization</li>
 *     <li>Turn-by-turn gameplay</li>
 *     <li>Move validation</li>
 *     <li>Winner and draw detection</li>
 *     <li>Saving the current board state</li>
 * </ul>
 */
public class Game {

    /** The playing board for this game session. */
    private final Board board;

    /**
     * Creates a new game with a board of the given size.
     *
     * @param rows the number of rows on the board
     * @param cols the number of columns on the board
     */
    public Game(int rows, int cols) {
        this.board = new Board(rows, cols);
    }

    /**
     * Starts the Gomoku match between two players.
     * <p>
     * The game alternates turns between the two players until:
     * <ul>
     *     <li>a player wins, or</li>
     *     <li>the board becomes full (resulting in a draw)</li>
     * </ul>
     *
     * @param p1 the first player
     * @param p2 the second player
     */
    public void start(Player p1, Player p2) {
        Player current = p1;

        while (true) {
            System.out.println(board);
            System.out.println("Player " + current.getSymbol() + "'s turn");

            int[] move = current.getMove();
            int row = move[0];
            int col = move[1];

            // Attempt to place the symbol
            if (!board.place(row, col, current.getSymbol())) {
                System.out.println("Invalid move, skipping turn.");
                current = (current == p1) ? p2 : p1;  // switch player
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

            // Swap turns
            current = (current == p1) ? p2 : p1;
        }
    }

    /**
     * Saves the current board state into the database under the provided name.
     * <p>
     * If a board with the same name already exists, the save is rejected to
     * prevent accidental overwriting.
     *
     * @param name the name under which the board should be saved
     */
    public void saveCurrentBoard(String name) {
        DbManager db = DbManager.getInstance();

        if (db.boardExists(name)) {
            System.out.println("A board with this name already exists. Please choose another name.");
        } else {
            db.saveBoard(name, board.serialize());
            System.out.println("Board saved as '" + name + "'.");
        }
    }
}

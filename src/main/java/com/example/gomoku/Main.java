package com.example.gomoku;

import com.example.gomoku.game.Game;
import com.example.gomoku.player.HumanPlayer;
import com.example.gomoku.player.RandomAIPlayer;
import com.example.gomoku.util.DbManager;
import com.example.gomoku.util.LoggerConfig;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The main entry point for the Gomoku game.
 * <p>
 * This class runs a command-line interface (CLI) version of the game
 * where a human player can play against a simple AI that chooses random moves.
 * It also provides options to save the board state to a local SQL database.
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Main method to start the Gomoku CLI.
     * <p>
     * It sets up logging, initializes the database, prompts the user for board size,
     * starts the game with a human and an AI player, and allows saving the finished board.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Setup logging
        LoggerConfig.setup();
        LOGGER.info("Starting Gomoku CLI");

        // Ensure database table exists
        DbManager.getInstance().createTableIfNotExists();

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Gomoku (5 in a row)!");
        System.out.print("Enter rows (N): ");
        int n = Integer.parseInt(sc.nextLine());
        System.out.print("Enter cols (M): ");
        int m = Integer.parseInt(sc.nextLine());

        // Initialize game and players
        Game game = new Game(n, m);
        HumanPlayer human = new HumanPlayer('X', sc);
        RandomAIPlayer ai = new RandomAIPlayer('O');

        // Start the game loop
        game.start(human, ai);

        // Optionally save the finished board
        System.out.println("Game finished. Save board? (y/n)");
        String ans = sc.nextLine().trim();
        if (ans.equalsIgnoreCase("y")) {
            while (true) {
                System.out.print("Enter a name for your saved board: ");
                String boardName = sc.nextLine().trim();
                if (boardName.isEmpty()) {
                    System.out.println("Name cannot be empty.");
                    continue;
                }

                if (DbManager.getInstance().boardExists(boardName)) {
                    System.out.println("That name already exists. Try another one.");
                } else {
                    game.saveCurrentBoard(boardName);
                    break;
                }
            }
        }

        System.out.println("Bye!");
        LOGGER.info("Gomoku CLI finished");
    }
}

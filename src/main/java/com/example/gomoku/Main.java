package com.example.gomoku;

import com.example.gomoku.game.Game;
import com.example.gomoku.player.HumanPlayer;
import com.example.gomoku.player.RandomAIPlayer;
import com.example.gomoku.util.DbManager;
import com.example.gomoku.util.LoggerConfig;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LoggerConfig.setup();
        LOGGER.info("Starting Gomoku CLI");

        DbManager.getInstance();

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Gomoku (5 in a row)!");
        System.out.print("Enter rows (N): ");
        int n = Integer.parseInt(sc.nextLine());
        System.out.print("Enter cols (M): ");
        int m = Integer.parseInt(sc.nextLine());

        Game game = new Game(n, m);
        HumanPlayer human = new HumanPlayer('X', sc);
        RandomAIPlayer ai = new RandomAIPlayer('O');

        game.start(human, ai);

        System.out.println("Game finished. Save board? (y/n)");
        String ans = sc.nextLine().trim();
        if (ans.equalsIgnoreCase("y")) {
            game.saveCurrentBoard("last_game");
            System.out.println("Board saved to DB under name 'last_game'.");
        }

        System.out.println("Bye!");
        LOGGER.info("Gomoku CLI finished");
    }
}

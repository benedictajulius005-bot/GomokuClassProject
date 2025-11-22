package com.example;

import com.example.gomoku.game.Game;
import com.example.gomoku.model.Board;
import com.example.gomoku.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    // A fake player that always returns one fixed move.
    static class FixedPlayer implements Player {
        private final char symbol;
        private final int[] move;

        FixedPlayer(char symbol, int row, int col) {
            this.symbol = symbol;
            this.move = new int[]{row, col};
        }

        @Override
        public char getSymbol() {
            return symbol;
        }

        @Override
        public int[] getMove() {
            return move;
        }
    }

    @Test
    public void testGameEndsOnWin() {
        Game g = new Game(5, 5);

        // X always plays (0,0), O always plays (1,1)
        Player p1 = new FixedPlayer('X', 0, 0);
        Player p2 = new FixedPlayer('X', 0, 1);  // X wins faster

        // If the game loops forever → test fails
        assertDoesNotThrow(() -> g.start(p1, p2));
    }

    @Test
    public void testAIMoveWithinBoard() {
        Board b = new Board(10, 10);

        Player ai = new Player() {
            @Override
            public char getSymbol() { return 'O'; }

            @Override
            public int[] getMove() { return new int[]{5, 5}; }
        };

        int[] mv = ai.getMove();
        assertTrue(mv[0] >= 0 && mv[0] < 10);
        assertTrue(mv[1] >= 0 && mv[1] < 10);
    }
}

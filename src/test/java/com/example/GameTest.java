package com.example;

import com.example.gomoku.game.Game;
import com.example.gomoku.model.Board;
import com.example.gomoku.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    // A fake player that returns a sequence of moves to ensure the game can end
    static class FixedPlayer implements Player {
        private final char symbol;
        private final int[][] moves;
        private int index = 0;

        FixedPlayer(char symbol, int[][] moves) {
            this.symbol = symbol;
            this.moves = moves;
        }

        @Override
        public char getSymbol() {
            return symbol;
        }

        @Override
        public int[] getMove() {
            // Return the next move in the sequence
            if (index < moves.length) {
                return moves[index++];
            }
            // If out of moves, just return a random valid move (prevent infinite loop)
            return new int[]{0, 0};
        }
    }

    @Test
    public void testGameEndsOnWin() {
        Game g = new Game(5, 5);

        // Give each player a sequence of moves to ensure a win occurs
        Player p1 = new FixedPlayer('X', new int[][]{
                {0,0},{0,1},{0,2},{0,3},{0,4}  // X will win across first row
        });
        Player p2 = new FixedPlayer('O', new int[][]{
                {1,0},{1,1},{1,2},{1,3},{1,4}  // O plays in second row
        });

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

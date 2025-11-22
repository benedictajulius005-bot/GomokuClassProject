package com.example;

import com.example.gomoku.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testHorizontalWin() {
        Board b = new Board(6, 6);
        for (int c = 0; c < 5; c++) b.place(2, c, 'X');

        assertTrue(b.winner().isPresent());
        assertEquals('X', b.winner().get());
    }

    @Test
    public void testVerticalWin() {
        Board b = new Board(6, 6);
        for (int r = 0; r < 5; r++) b.place(r, 3, 'O');

        assertTrue(b.winner().isPresent());
        assertEquals('O', b.winner().get());
    }

    @Test
    public void testDiagonalWinLeftToRight() {
        Board b = new Board(6, 6);
        b.place(0, 0, 'X');
        b.place(1, 1, 'X');
        b.place(2, 2, 'X');
        b.place(3, 3, 'X');
        b.place(4, 4, 'X');

        assertTrue(b.winner().isPresent());
        assertEquals('X', b.winner().get());
    }

    @Test
    public void testDiagonalWinRightToLeft() {
        Board b = new Board(6, 6);
        b.place(0, 4, 'X');
        b.place(1, 3, 'X');
        b.place(2, 2, 'X');
        b.place(3, 1, 'X');
        b.place(4, 0, 'X');

        assertTrue(b.winner().isPresent());
        assertEquals('X', b.winner().get());
    }

    @Test
    public void testPlaceOutOfBounds() {
        Board b = new Board(5, 5);

        assertFalse(b.place(-1, 2, 'X'));
        assertFalse(b.place(10, 2, 'X'));
        assertFalse(b.place(2, 10, 'X'));
    }

    @Test
    public void testPlaceOnOccupiedCell() {
        Board b = new Board(5, 5);

        assertTrue(b.place(2, 2, 'X'));
        assertFalse(b.place(2, 2, 'O'));  // cannot place again
    }

    @Test
    public void testBoardIsFull() {
        Board b = new Board(2, 2);

        b.place(0,0,'X');
        b.place(0,1,'O');
        b.place(1,0,'X');
        b.place(1,1,'O');

        assertTrue(b.isFull());
    }

    @Test
    public void testSerializeAndDeserialize() {
        Board b = new Board(3, 3);
        b.place(0, 0, 'X');
        b.place(1, 1, 'O');

        String serialized = b.serialize();
        Board loaded = Board.deserialize(serialized);

        assertEquals(serialized, loaded.serialize());
    }
}

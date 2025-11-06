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
    public void testNoWin() {
        Board b = new Board(5, 5);
        assertTrue(b.winner().isEmpty());
    }
}

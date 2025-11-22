package com.example.gomoku.model;

/**
 * Represents a move on the board with a row and column.
 * <p>
 * This is a simple immutable data holder (record) for storing a move's coordinates.
 */
public record Move(int row, int col) {}

package com.example.gomoku.dao;

/**
 * Data Access Object interface for saving and loading
 * a Gomoku game board from a persistent storage (SQL database).
 * <p>
 * Implementations of this interface are responsible for
 * handling all database-related operations for storing
 * and retrieving a serialized game board.
 */
public interface BoardDao {

    /**
     * Saves a serialized representation of the game board under the given name.
     *
     * @param name the unique identifier or board name used as the key for storage
     * @param serializedBoard the board converted into a String (JSON, text, etc.)
     *                       that will be stored in the database
     */
    void save(String name, String serializedBoard);

    /**
     * Loads a previously saved serialized board from the database.
     *
     * @param name the unique identifier or board name that was used when saving
     * @return the serialized board as a String, or {@code null} if no board exists under that name
     */
    String load(String name);
}

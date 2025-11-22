package com.example.gomoku.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Utility class for setting up logging for the Gomoku game.
 * <p>
 * It configures the root logger to write logs to a file "gomoku.log"
 * and sets the log level to INFO.
 */
public class LoggerConfig {

    /**
     * Configures the root logger to output log messages to a file with INFO level.
     * <p>
     * The logs are appended to "gomoku.log" and formatted using SimpleFormatter.
     * If an IOException occurs while creating the file handler, the stack trace is printed.
     */
    public static void setup() {
        Logger root = Logger.getLogger("");
        try {
            FileHandler fh = new FileHandler("gomoku.log", true);
            fh.setFormatter(new SimpleFormatter());
            root.addHandler(fh);
            root.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

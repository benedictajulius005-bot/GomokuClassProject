package com.example.gomoku.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
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

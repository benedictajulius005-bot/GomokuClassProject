package com.example.gomoku.dao;

public interface BoardDao {
    void save(String name, String serializedBoard);
    String load(String name);
}

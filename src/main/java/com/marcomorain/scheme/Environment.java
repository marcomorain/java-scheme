package com.marcomorain.scheme;

public interface Environment {
    public Cell load(String symbol);
    public void save(String symbol, Cell value);
}

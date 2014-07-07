package com.marcomorain.scheme;

public class Symbol extends Cell {
    private final String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }
    
    @Override
    public Cell eval(Environment environment) {
        return environment.load(symbol);
    }
    
    @Override
    public String toString() {
        return symbol;
    }
}

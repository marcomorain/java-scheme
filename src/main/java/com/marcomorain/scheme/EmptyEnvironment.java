package com.marcomorain.scheme;

class EmptyEnvironment implements Environment {

    @Override
    public Cell load(String symbol) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void save(String symbol, Cell value) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
}

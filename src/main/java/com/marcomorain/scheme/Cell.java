package com.marcomorain.scheme;

public class Cell {
    public static final Cell TRUE  = new Cell();
    public static final Cell FALSE = new Cell();
    public static final Cell EMPTY_LIST = new Cell();
    
    protected Cell() {
    }
    
    public Cell eval(Environment environment) {
        return this;
    }
    
    public Cell call(Environment environment, Cell arguments) {
        throw no("call");
    }
    
    public Cell car() {
        throw no("car");
    }
    
    public Cell cdr() {
        throw no("cdr");
    }
    
    public double number() {
        throw no("number");
    }
    
    private UnsupportedOperationException no(String what) {
        return new UnsupportedOperationException(String.format("Cannot %sa %s", what, getClass().getSimpleName()));
    }
}

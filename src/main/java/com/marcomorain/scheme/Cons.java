package com.marcomorain.scheme;

public class Cons extends Cell {
    public final Cell car;
    public final Cell cdr;
    
    public Cons(Cell car, Cell cdr) {
        this.car = car;
        this.cdr = cdr;
    }
}

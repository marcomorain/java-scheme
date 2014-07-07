package com.marcomorain.scheme;

public class Cons extends Cell {
    public final Cell car;
    public final Cell cdr;
    
    public Cons(Cell car, Cell cdr) {
        this.car = car;
        this.cdr = cdr;
    }
    
    @Override
    public Cell eval(Environment environment) {
        return car.eval(environment).call(environment, cdr);
    }

    @Override
    public Cell car() {
        return car;
    }
    
    @Override
    public Cell cdr() {
        return cdr;
    }
}

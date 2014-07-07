package com.marcomorain.scheme;

public class Cons extends Cell {

    public final Cell car;
    public final Cell cdr;

    public Cons(Cell car, Cell cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    @Override
    public Cell eval(Environment environment) throws EvaluationException {
        // TODO: This should eval the arguments.
        return car.eval(environment).call(environment, cdr);
    }

    @Override
    public Cell car() throws EvaluationException {
        return car;
    }

    @Override
    public Cell cdr() throws EvaluationException {
        return cdr;
    }
}

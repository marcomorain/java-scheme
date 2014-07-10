package com.marcomorain.scheme;

public class Cell {

    public static final Cell TRUE = new Cell();
    public static final Cell FALSE = new Cell();
    public static final Cell EMPTY_LIST = new Cell();

    protected Cell() {
    }

    public Cell eval(Environment environment) throws EvaluationException {
        return this;
    }

    public Cell call(Environment environment, Cell arguments) throws EvaluationException {
        throw no("call");
    }

    public Cell car() throws EvaluationException {
        throw no("car");
    }

    public Cell cdr() throws EvaluationException {
        throw no("cdr");
    }

    public double number() throws EvaluationException {
        throw no("number");
    }

    private EvaluationException no(String what) {
        return new EvaluationException(String.format("Cannot %s a %s", what, getClass().getSimpleName()));
    }
}

package com.marcomorain.scheme;

public interface Environment {

    public Cell load(String symbol) throws EvaluationException;

    public void save(String symbol, Cell value) throws EvaluationException;
}

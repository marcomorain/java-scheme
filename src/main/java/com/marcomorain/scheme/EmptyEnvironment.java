package com.marcomorain.scheme;

class EmptyEnvironment implements Environment {

    @Override
    public Cell load(String symbol) throws EvaluationException {
        throw new EvaluationException();
    }

    @Override
    public void save(String symbol, Cell value) throws EvaluationException {
        throw new EvaluationException();
    }

}

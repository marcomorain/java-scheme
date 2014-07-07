package com.marcomorain.scheme;

public class Evaluator {

    private final Environment environment;

    // TODO: this should not throw
    public Evaluator() throws EvaluationException {
        this.environment = new RuntimeEnvironment(new EmptyEnvironment());
        Functions.Register(environment);
    }

    public Cell eval(Cell cell) throws EvaluationException {
        return cell.eval(environment);
    }
}

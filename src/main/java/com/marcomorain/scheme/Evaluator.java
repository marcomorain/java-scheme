package com.marcomorain.scheme;

public class Evaluator {
    private final Environment environment;
    
    public Evaluator() {
        this.environment = new RuntimeEnvironment(new EmptyEnvironment());
        Functions.Register(environment);
    }
    
    public Cell eval(Cell cell) {
        return cell.eval(environment);
    }
}

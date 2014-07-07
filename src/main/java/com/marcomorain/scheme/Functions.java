package com.marcomorain.scheme;

public class Functions {

    public static void Register(Environment environment) throws EvaluationException {
        environment.save("+", new Function() {

            @Override
            public Cell call(Environment environment, Cell arguments) throws EvaluationException {
                double result = 0;
                for (Cell cell = arguments; cell != EMPTY_LIST; cell = cell.cdr()) {
                    result += cell.car().eval(environment).number();
                }
                return new Number(result);
            }
        });
    }
}

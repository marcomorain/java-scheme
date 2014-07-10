package com.marcomorain.scheme;

import java.io.IOException;
import java.io.StringReader;

public class App {

    public static void main(String[] args) throws ParseException, EvaluationException, IOException {
        Evaluator evaluator = new Evaluator();
        for (String arg : args) {
            System.out.format("Input: %s%n", arg);
            eval(evaluator, arg);

        }
    }

    static void eval(Evaluator e, String s) throws IOException {
        Parser parser = new Parser(new Tokeniser(new StringReader(s)));
        try {
            Cell cell = e.eval(parser.read());
            System.out.println(cell);
        } catch (EvaluationException | ParseException | IOException ee) {
            System.err.printf("Parse error: %s%n", ee);
        }
    }
}

package com.marcomorain.scheme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class App {

    public static void main(String[] args) throws ParseException, EvaluationException, IOException {
        Evaluator evaluator = new Evaluator();
        for (String arg : args) {

            eval(evaluator, arg);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                eval(evaluator, line);
            }
        }
    }

    static void eval(Evaluator e, String s) throws IOException {
        Parser parser = new Parser(new Tokeniser(new StringReader(s)));
        try {
            System.out.format("> %s%n", s);
            Cell cell = e.eval(parser.read());
            System.out.println(cell);
        } catch (EvaluationException | ParseException | IOException ee) {
            System.err.printf("Parse error: %s%n", ee);
        }
    }
}

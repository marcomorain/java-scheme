package com.marcomorain.scheme;

import java.io.IOException;
import java.io.StringReader;

public class App {

    public static void main(String[] args) throws ParseException, EvaluationException, IOException {
        Tokeniser tokeniser = new Tokeniser(new StringReader("(+ 1 2 3)"));
        Parser parser = new Parser(tokeniser);

        Evaluator evaluator = new Evaluator();
        for (;;) {
            try {
                Cell cell = evaluator.eval(parser.read());
                System.out.println(cell);
            } catch (EvaluationException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                System.err.printf("Parse error: %s%n", e);
                return;
            }
        }
    }
}

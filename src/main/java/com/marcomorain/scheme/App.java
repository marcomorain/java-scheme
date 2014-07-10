package com.marcomorain.scheme;

import java.io.IOException;
import java.io.StringReader;

public class App {

    public static void main(String[] args) throws IOException {
        for (String arg : args) {
        }

        Tokeniser tokeniser = new Tokeniser(new StringReader( args[0]));
        tokeniser.parseToken();
    }
}

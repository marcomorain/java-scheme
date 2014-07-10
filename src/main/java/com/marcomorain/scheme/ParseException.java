package com.marcomorain.scheme;

public class ParseException extends Exception {

    private final int line;
    private final int column;

    public ParseException(String message, int line, int column) {
        super(String.format("Parse Error %s at %d:%d", message, line, column));
        this.line = line;
        this.column = column;
    }
}

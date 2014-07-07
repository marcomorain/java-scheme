package com.marcomorain.scheme;

import java.io.IOException;
import java.io.Reader;

public class LineTracker {

    private final Reader reader;
    private int line;
    private int column;

    public LineTracker(Reader reader) {
        this.reader = reader;
        this.line = 1;
        this.column = 0;
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }

    public int peek() throws IOException {
        reader.mark(1);
        final int result = reader.read();
        reader.reset();
        return result;
    }

    public int next() throws IOException {
        final int c = reader.read();
        if (c == '\n') {
            this.line++;
            this.column = 0;
        } else {
            this.column++;
        }
        return c;
    }

}

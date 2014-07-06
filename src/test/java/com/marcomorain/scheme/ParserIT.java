package com.marcomorain.scheme;

import java.io.Reader;
import java.io.StringReader;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Test;

public class ParserIT {

    @Test
    public void testRead() throws Exception {
        Reader input = new StringReader("(+ 1 2)");
        Tokeniser tokeniser = new Tokeniser(input);
        Parser parser = new Parser(tokeniser);
        Cell cell = parser.read();
        assertThat(cell, instanceOf(Cons.class));
    }

}

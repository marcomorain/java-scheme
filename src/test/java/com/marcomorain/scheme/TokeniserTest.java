package com.marcomorain.scheme;

import com.marcomorain.scheme.Tokeniser.Token;
import java.io.StringReader;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class TokeniserTest {

    @Test
    public void testParseToken() throws Exception {
        Tokeniser instance = new Tokeniser(new StringReader("(1 22 378 abcd)"));
        assertThat(instance.parseToken(), equalTo(Token.LEFT_PAREN));
        assertThat(instance.parseToken(), equalTo(Token.NUMBER));
        assertThat(instance.parseToken(), equalTo(Token.NUMBER));
        assertThat(instance.parseToken(), equalTo(Token.NUMBER));
        assertThat(instance.parseToken(), equalTo(Token.IDENTIFIER));
        assertThat(instance.parseToken(), equalTo(Token.RIGHT_PAREN));
    }

    @Test
    public void itCanIterate() throws Exception {

        Tokeniser instance = new Tokeniser(new StringReader("(1 22 378 abcd)"));
        int count = 0;
        for (Token token : instance) {
            count++;
        }
        assertThat(count, is(6));
    }

}

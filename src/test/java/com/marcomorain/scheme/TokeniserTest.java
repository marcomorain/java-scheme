package com.marcomorain.scheme;

import java.io.IOException;
import java.io.StringReader;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TokeniserTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testSum() throws Exception {
        Tokeniser instance = new Tokeniser(new StringReader("(+ 1 2)"));
        assertThat(instance.parseToken().type, equalTo(Token.Type.LEFT_PAREN));
        assertThat(instance.parseToken().type, equalTo(Token.Type.IDENTIFIER));
        assertThat(instance.parseToken().type, equalTo(Token.Type.NUMBER));
        assertThat(instance.parseToken().type, equalTo(Token.Type.NUMBER));
        assertThat(instance.parseToken().type, equalTo(Token.Type.RIGHT_PAREN));
    }

    @Test
    public void testParseToken() throws Exception {
        Tokeniser instance = new Tokeniser(new StringReader("(1 22 378 abcd)"));
        assertThat(instance.parseToken().type, equalTo(Token.Type.LEFT_PAREN));
        assertThat(instance.parseToken().type, equalTo(Token.Type.NUMBER));
        assertThat(instance.parseToken().type, equalTo(Token.Type.NUMBER));
        assertThat(instance.parseToken().type, equalTo(Token.Type.NUMBER));
        assertThat(instance.parseToken().type, equalTo(Token.Type.IDENTIFIER));
        assertThat(instance.parseToken().type, equalTo(Token.Type.RIGHT_PAREN));
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

    // TODO: Test parsing in other implementations: "#t#f"

    @Test
    public void itCanParseBooleans() throws Exception {

        Tokeniser instance = new Tokeniser(new StringReader("#t #f"));
        assertThat(instance.parseToken().type, equalTo(Token.Type.TRUE));
        assertThat(instance.parseToken().type, equalTo(Token.Type.FALSE));
        assertThat(instance.parseToken().type, equalTo(Token.Type.END_OF_INPUT));
    }

    @Test
    public void itCanParseStrings() throws Exception {
        Tokeniser instance = new Tokeniser(new StringReader("\"one\""));
        Token token = instance.parseToken();
        assertThat(token.type, equalTo(Token.Type.STRING));
        assertThat(token.identifier, equalTo("one"));
    }

    @Test
    public void itCorrectlyIdentifiesUnterminatedStrings() throws Exception {
        exception.expect(IOException.class);
        exception.expectMessage("food");
        Tokeniser instance = new Tokeniser(new StringReader("\"unterminated"));
    }
}

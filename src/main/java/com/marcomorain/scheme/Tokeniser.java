package com.marcomorain.scheme;

import com.marcomorain.scheme.Tokeniser.Token;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Tokeniser implements Iterable<Token> {

    public static enum Token {

        LEFT_PAREN,
        RIGHT_PAREN,
        NUMBER,
        IDENTIFIER,
        END_OF_INPUT
    }

    private final Reader input;

    public Tokeniser(Reader input) {
        this.input = input;
    }

    public static boolean isSpecialSubsequent(int c) {
        switch (c) {
            case '+':
            case '-':
            case '.':
            case '@':
                return true;
        }

        return false;
    }

    public static boolean isPeculiarIdentifier(int c) {
        // @todo: ... can be accepted here.
        return c == '+' || c == '-';
    }

    public static boolean isSubsequent(int c) {
        return isInitial(c) || Character.isDigit(c) || isSpecialSubsequent(c);
    }

    public static boolean isDelimeter(int c) {
        switch (c) {
            case 0: // @todo: having null here is a bit of a hack - not in the spec.
            case ' ':
            case '\n':
            case '\t':
            case '"':
            case '(':
            case ')':
            case ';':
                return true;
        }
        return false;
    }

    public static boolean isSpecialInitial(int c) {
        switch (c) {
            case '!':
            case '$':
            case '%':
            case '&':
            case '*':
            case '/':
            case ':':
            case '<':
            case '=':
            case '>':
            case '?':
            case '^':
            case '_':
            case '~':
                return true;
        }

        return false;
    }

    public static boolean isInitial(int c) {
        return Character.isAlphabetic(c) || isSpecialInitial(c);
    }

    private int peek() throws IOException {
        input.mark(1);
        final int result = input.read();
        input.reset();
        return result;
    }

    private Token parseNumber(final int c) throws IOException {

        double result = Character.getNumericValue(c);

        for (;;) {
            input.mark(1);
            final int next = input.read();

            if (!Character.isDigit(next)) {
                input.reset();
                break;
            }
            result *= 10;
            result += Character.getNumericValue(next);
        }
        System.out.format("Read number %f%n", result);
        return Token.NUMBER;
    }

    private void skipWhiteSpace() throws IOException {
        while (Character.isWhitespace(peek())) {
            System.out.format("Skip whitespace%n");
            input.read();
        }
    }

    private Token parseIdentifier() throws IOException {

        StringBuilder builder = new StringBuilder();

        int c = input.read();

        if (isInitial(c)) {
            builder.appendCodePoint(c);

            for (;;) {
                c = peek();

                if (isDelimeter(c)) {
                    break;
                }
                if (!isSubsequent(c)) {
                    throw new IOException("malformed identifier");
                }
                input.read();
                builder.appendCodePoint(c);
            }
        } else if (isPeculiarIdentifier(c)) {
            builder.appendCodePoint(c);
            input.read();
        } else {
            throw new IOException("malformed identifier");
        }

        System.out.format("Read identifier: '%s'", builder.toString());
        return Token.IDENTIFIER;
    }

    public Token parseToken() throws IOException {
        skipWhiteSpace();
        final int c = peek();
        switch (c) {
            case -1:
                return Token.END_OF_INPUT;
            case '(':
                input.read();
                return Token.LEFT_PAREN;
            case ')':
                input.read();
                return Token.RIGHT_PAREN;
            default:
                if (Character.isDigit(c)) {
                    input.read();
                    return parseNumber(c);
                } else {
                    return parseIdentifier();
                }
        }
    }

    @Override
    public Iterator<Token> iterator() {
        return new TokenEnumeration();
    }

    private final class TokenEnumeration extends NoRemovalIterator<Token> {

        private Token next = null;

        TokenEnumeration() {
            try {
                next = parseToken();
            } catch (IOException e) {
                throw new IllegalStateException();
            }
        }

        @Override
        public boolean hasNext() {
            return next != Token.END_OF_INPUT;
        }

        @Override
        public Token next() {
            try {
                Token result = next;
                next = parseToken();
                return result;
            } catch (IOException ioe) {
                throw new NoSuchElementException();
            }
        }
    }
}

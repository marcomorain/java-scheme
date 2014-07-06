package com.marcomorain.scheme;

// TODO: non-public
public class Token {

    public enum Type {
        LEFT_PAREN,
        RIGHT_PAREN,
        QUOTE,
        DOT,
        BACKTICK,
        NUMBER,
        COMMA_AT,
        COMMA,
        IDENTIFIER,
        TRUE,
        FALSE,
        END_OF_INPUT
    }

    public final Type type;
    public double number;
    public String identifier;

    public Token(Type type) {
        this.type = type;
    }

    public static Token number(double n) {
        Token result = new Token(Token.Type.NUMBER);
        result.number = n;
        return result;
    }

    public static Token identifier(String i) {
        Token result = new Token(Token.Type.IDENTIFIER);
        result.identifier = i;
        return result;
    }
}

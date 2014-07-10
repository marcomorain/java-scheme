package com.marcomorain.scheme;

import java.io.IOException;

public class Parser {

    private final Tokeniser tokeniser;

    public Parser(Tokeniser tokeniser) {
        this.tokeniser = tokeniser;
    }

    public Cell read() throws ParseException, IOException {
        return datum(tokeniser.parseToken());
    }

    private Cell datum(Token token) throws ParseException, IOException {

        if (token.type == Token.Type.END_OF_INPUT) {
            throw new ParseException(String.format("End of input"), tokeniser.line(), tokeniser.column());
        }

        Cell simple = simpleDatum(token);

        if (simple != null) {
            return simple;
        }

        Cell compound = compoundDatum(token);

        if (compound != null) {
            return compound;
        }

        throw new ParseException(String.format("Unexpected token %s", token), tokeniser.line(), tokeniser.column());
    }

    private Cell simpleDatum(Token token) {
        switch (token.type) {
            case FALSE:
                return Cell.FALSE;
            case TRUE:
                return Cell.TRUE;
            case NUMBER:
                return new Number(token.number);
            //case CHARACTER:
            //case STRING:
            case IDENTIFIER:
                return new Symbol(token.identifier);
        }
        return null;
    }

    private Cell compoundDatum(Token token) throws ParseException, IOException {
        final Cell list = list(token);
        if (list != null) {
            return list;
        }
        final Cell vector = vector(token);
        if (vector != null) {
            return vector;
        }
        return null;
    }

    private Cell list(Token token) throws ParseException, IOException {
        if (token.type != Token.Type.LEFT_PAREN) {
            return null;
        }
        return listTail(tokeniser.parseToken());
    }

    private Cell listTail(Token token) throws ParseException, IOException {
        switch (token.type) {
            case RIGHT_PAREN:
                return Cell.EMPTY_LIST;
            // TODO: case DOT
            default:
                Cell cell = datum(token);
                Cell tail = listTail(tokeniser.parseToken());
                return new Cons(cell, tail);
        }
    }

    private Cell vector(Token token) {
        // TODO
        return null;
    }

}

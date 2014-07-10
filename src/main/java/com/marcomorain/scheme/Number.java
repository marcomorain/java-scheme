package com.marcomorain.scheme;


public class Number extends Cell {

    private final double number;

    public Number(double number) {
        this.number = number;
    }
    
    @Override
    public double number() {
        return number;
    }
    
    @Override
    public String toString() {
        return Double.toString(number);
    }
}

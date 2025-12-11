package org.jsp.Assessment.exception;

public class NumberException extends RuntimeException {
    public NumberException(String partNumber){
        super("Product with partNumber '" + partNumber + "' already exists");
    }
}


package com.aj.game.exceptions;

public class BoardException extends Exception {

    public BoardException() {
        super("Invalid board information. Please check your input and try again");
    }

    public BoardException(String message) {
        super(message);
    }
}

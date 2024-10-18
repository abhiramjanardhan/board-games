package com.aj.game.exceptions;

public class PawnPositionException extends Exception {

    public PawnPositionException() {
        super("Invalid position. Please check your input and try again!");
    }

    public PawnPositionException(String message) {
        super(message);
    }
}

package com.aj.game.exceptions;

public class DiceException extends Exception {

    public DiceException() {
        super("Invalid dice value. Please check your input and try again!");
    }
}

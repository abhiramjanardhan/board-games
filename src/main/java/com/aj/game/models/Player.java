package com.aj.game.models;

import com.aj.game.utilities.InputScanner;

public class Player {
    private String name;
    private int currentPosition;
    private final InputScanner scanner;

    public Player() {
        this.currentPosition = 0;
        this.scanner = new InputScanner();
        this.updateValues();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    private void updateValues() {
        scanner.printMessage("Enter the player information");

        scanner.print("Enter the name of the player = ");
        this.setName(scanner.getString());

        scanner.printMessage("Player details are updated!");
        scanner.printNewLine();
    }

    public void printCurrentPosition() {
        scanner.printMessage("Current position: " + this.getCurrentPosition());
    }
}

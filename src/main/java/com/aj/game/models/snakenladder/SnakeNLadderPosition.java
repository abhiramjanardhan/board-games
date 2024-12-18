package com.aj.game.models.snakenladder;

import com.aj.game.models.Position;

public class SnakeNLadderPosition extends Position {
    private int currentPosition;

    public SnakeNLadderPosition() {
        this.currentPosition = 0;
    }

    public String getCurrentPosition(String... parameters) {
        return String.valueOf(currentPosition);
    }

    public void setCurrentPosition(String... positions) {
        this.currentPosition = Integer.parseInt(positions[0]);
    }

    public void printCurrentPosition(String... parameters) {
        scanner.printMessage("Current position: " + this.getCurrentPosition());
    }
}

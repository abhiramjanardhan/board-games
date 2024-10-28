package com.aj.game.models.snakenladder;

import com.aj.game.exceptions.BoardException;
import com.aj.game.models.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class SnakeNLadder extends GameBoard {
    private int numberOfLadders;
    private int numberOfSnakes;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public SnakeNLadder() {
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
    }

    public int getNumberOfLadders() {
        return numberOfLadders;
    }

    public void setNumberOfLadders(int numberOfLadders) {
        this.numberOfLadders = numberOfLadders;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public int getNumberOfSnakes() {
        return numberOfSnakes;
    }

    public void setNumberOfSnakes(int numberOfSnakes) {
        this.numberOfSnakes = numberOfSnakes;
    }

    public void printPlayerStatus() {
        scanner.printMessage("Players on board:");
        this.getPlayers().forEach(player -> {
            try {
                scanner.printMessage(player.getName());
                player.getPosition().printCurrentPosition();
                scanner.printNewLine();
            } catch (BoardException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void printBoardDetails() {
        scanner.printMessage("There are a total of " + this.getNumberOfSnakes() + " snakes on board");
        this.getSnakes().forEach(snake -> {
            scanner.printMessage("Details for snake: " + snake.getName());
            scanner.printMessage("Biting at position: " + snake.getStart());
            scanner.printMessage("Dropping at position: " + snake.getEnd());
            scanner.printNewLine();
        });

        scanner.printMessage("There are a total of " + this.getNumberOfLadders() + " ladders on board");
        this.getLadders().forEach(ladder -> {
            scanner.printMessage("Details for ladder: " + ladder.getName());
            scanner.printMessage("Climbing at position: " + ladder.getStart());
            scanner.printMessage("Reaching at position: " + ladder.getEnd());
            scanner.printNewLine();
        });
    }
}

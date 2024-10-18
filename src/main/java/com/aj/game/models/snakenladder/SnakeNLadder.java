package com.aj.game.models.snakenladder;

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
}

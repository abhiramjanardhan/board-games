package com.aj.game.models.snakenladder.config;

import java.util.List;

public class Snakes {
    private int numberOfSnakes;
    private List<SnakeNLadderDetail> snakes;

    public int getNumberOfSnakes() {
        return numberOfSnakes;
    }

    public void setNumberOfSnakes(int numberOfSnakes) {
        this.numberOfSnakes = numberOfSnakes;
    }

    public List<SnakeNLadderDetail> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<SnakeNLadderDetail> snakes) {
        this.snakes = snakes;
    }
}

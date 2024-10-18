package com.aj.game.models.snakenladder.config;

import java.util.List;

public class Ladders {
    private int numberOfLadders;
    private List<SnakeNLadderDetail> ladders;

    public int getNumberOfLadders() {
        return numberOfLadders;
    }

    public void setNumberOfLadders(int numberOfLadders) {
        this.numberOfLadders = numberOfLadders;
    }

    public List<SnakeNLadderDetail> getLadders() {
        return ladders;
    }

    public void setLadders(List<SnakeNLadderDetail> ladders) {
        this.ladders = ladders;
    }
}

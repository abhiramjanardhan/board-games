package com.aj.game.models.snakenladder.config;

public class Board {
    private int diceCount;
    private int boardStart;
    private int boardEnd;

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getBoardStart() {
        return boardStart;
    }

    public void setBoardStart(int boardStart) {
        this.boardStart = boardStart;
    }

    public int getBoardEnd() {
        return boardEnd;
    }

    public void setBoardEnd(int boardEnd) {
        this.boardEnd = boardEnd;
    }
}

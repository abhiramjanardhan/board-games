package com.aj.game.models;

import com.aj.game.constants.GameConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.models.ludo.LudoPosition;
import com.aj.game.models.snakenladder.SnakeNLadderPosition;
import com.aj.game.utilities.InputScanner;

public abstract class Position {
    protected final InputScanner scanner;

    public Position() {
        this.scanner = InputScanner.getInstance();
    }

    public abstract String getCurrentPosition(String... parameters) throws BoardException;
    public abstract void setCurrentPosition(String... positions) throws BoardException;
    public abstract void printCurrentPosition(String... parameters) throws BoardException;

    public static Position getBoardPosition(String gameBoard) throws BoardException {
        switch (gameBoard) {
            case GameConstants.SNAKE_N_LADDER:
                return new SnakeNLadderPosition();
            case GameConstants.LUDO:
                return new LudoPosition();
            default:
                throw new BoardException("Invalid board! Please check your input and try again!");
        }
    }
}

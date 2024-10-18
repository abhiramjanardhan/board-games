package com.aj.game.services;

import com.aj.game.constants.GameConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.services.snakenladder.SnakeNLadderService;

public class GameBoardServiceFactory {
    public static GameBoardService getService(String gameBoard) throws BoardException {
        switch (gameBoard) {
            case GameConstants.SNAKE_N_LADDER:
                return new SnakeNLadderService();
            default:
                throw new BoardException();
        }
    }
}

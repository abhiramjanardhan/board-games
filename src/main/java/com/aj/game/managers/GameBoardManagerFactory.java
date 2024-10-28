package com.aj.game.managers;

import com.aj.game.constants.GameConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.managers.ludo.LudoManager;
import com.aj.game.managers.snakenladder.SnakeNLadderManager;

public class GameBoardManagerFactory {
    public static GameBoardManager getManager(String gameBoard) throws BoardException {
        switch (gameBoard) {
            case GameConstants.SNAKE_N_LADDER:
                return new SnakeNLadderManager();
            case GameConstants.LUDO:
                return new LudoManager();
            default:
                throw new BoardException();
        }
    }
}

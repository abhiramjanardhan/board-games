package com.aj.game.managers;

import com.aj.game.exceptions.BoardException;
import com.aj.game.services.GameBoardService;
import com.aj.game.services.GameBoardServiceFactory;

abstract public class GameBoardManager implements GameBoardManagerTemplate {
    private final String gameBoard;
    private GameBoardManager boardManager;
    private GameBoardService boardService;

    public GameBoardManager(String gameBoard) throws BoardException {
        this.gameBoard = gameBoard;
//        this.setBoardManager(GameBoardManagerFactory.getManager(this.gameBoard));
        this.setBoardService(GameBoardServiceFactory.getService(this.gameBoard));
    }

    public GameBoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(GameBoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public GameBoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(GameBoardService boardService) {
        this.boardService = boardService;
    }
}

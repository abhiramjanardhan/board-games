package com.aj.game.managers;

import com.aj.game.exceptions.BoardException;
import com.aj.game.models.GameBoard;
import com.aj.game.services.GameBoardService;
import com.aj.game.services.GameBoardServiceFactory;
import com.aj.game.utilities.InputScanner;

abstract public class GameBoardManager implements GameBoardManagerTemplate {
    private GameBoardService boardService;
    protected final InputScanner scanner;

    public GameBoardManager(String gameBoard) throws BoardException {
        this.scanner = InputScanner.getInstance();
        this.setBoardService(GameBoardServiceFactory.getService(gameBoard));
    }

    public GameBoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(GameBoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public GameBoard configure() throws Exception {
        return this.getBoardService().configure();
    }

    @Override
    public void play(GameBoard gameBoard) throws BoardException {
        this.getBoardService().play(gameBoard);
    }
}

package com.aj.game.services;

import com.aj.game.exceptions.BoardException;
import com.aj.game.models.GameBoard;
import com.aj.game.models.Player;
import com.aj.game.utilities.InputScanner;

abstract public class GameBoardService implements GameBoardServiceTemplate {
    private GameBoardService boardService;
    private final String gameBoard;
    protected InputScanner scanner;

    public GameBoardService(String gameBoard) throws BoardException {
        this.gameBoard = gameBoard;
        this.scanner = InputScanner.getInstance();
    }

    public GameBoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(GameBoardService boardService) {
        this.boardService = boardService;
    }

    protected GameBoard configureCommonInformation(GameBoard gameBoard) throws Exception {
        scanner.printNewLine();
        scanner.print("Enter the number of players on the board: ");
        gameBoard.setNumberOfPlayers(this.scanner.getInteger());

        if (gameBoard.getNumberOfPlayers() <= 0) {
            throw new BoardException();
        }

        scanner.printNewLine();

        for (int i = 0 ; i < gameBoard.getNumberOfPlayers() ; i++) {
            gameBoard.getPlayers().add(new Player(this.gameBoard));
        }

        return gameBoard;
    }
}

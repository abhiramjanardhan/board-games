package com.aj.game.models;

import com.aj.game.exceptions.BoardException;
import com.aj.game.utilities.InputScanner;

public class Player {
    private String name;
    private String gameBoard;
    private final InputScanner scanner;
    private Position position;

    public Player(String gameBoard) throws BoardException {
        this.gameBoard = gameBoard;
        this.scanner = InputScanner.getInstance();
        this.position = Position.getBoardPosition(gameBoard);
        this.updateValues();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String gameBoard) {
        this.gameBoard = gameBoard;
    }

    public InputScanner getScanner() {
        return scanner;
    }

    private void updateValues() {
        scanner.printMessage("Enter the player information");

        scanner.print("Enter the name of the player = ");
        this.setName(scanner.getString());

        scanner.printMessage("Player details are updated!");
        scanner.printNewLine();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

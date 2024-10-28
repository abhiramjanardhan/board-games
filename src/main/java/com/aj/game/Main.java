package com.aj.game;

import com.aj.game.constants.GameConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.managers.GameBoardManager;
import com.aj.game.managers.GameBoardManagerFactory;
import com.aj.game.models.GameBoard;
import com.aj.game.utilities.InputScanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    public static void main(String[] args) {
        boolean closeGame = false;
        InputScanner scanner = InputScanner.getInstance();
        scanner.printMessage("Welcome to your Board Games!");

        do {
            try {
                scanner.printMessage("Please choose from the list below");
                scanner.printMessage("Please find below the list of games available ...");
                scanner.printMessage("1. Snake and Ladder");
                scanner.printMessage("2. Ludo");
                scanner.printMessage("0. Exit");

                scanner.print("Enter the value: ");
                int game = scanner.getInteger();

                if (game == 0) {
                    closeGame = true;
                } else {
                    String gameBoard = "";
                    switch (game) {
                        case 1:
                            gameBoard = GameConstants.SNAKE_N_LADDER;
                            break;
                        case 2:
                            gameBoard = GameConstants.LUDO;
                            break;
                        default:
                            throw new BoardException("Invalid game option.");
                    }

                    GameBoardManager boardManager = GameBoardManagerFactory.getManager(gameBoard);
                    GameBoard snakeNLadder = boardManager.configure();
                    boardManager.validate(snakeNLadder);
                    boardManager.play(snakeNLadder);
                }
            } catch (Exception e) {
                scanner.printMessage(e.getMessage());
                scanner.printMessage("Please try again ...");
            }
        } while (!closeGame);

        scanner.closeScanner();
        scanner.exit();
    }
}
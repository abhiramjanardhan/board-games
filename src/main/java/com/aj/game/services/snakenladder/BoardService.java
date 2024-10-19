package com.aj.game.services.snakenladder;

import com.aj.game.exceptions.BoardException;
import com.aj.game.models.snakenladder.config.Board;
import com.aj.game.utilities.InputScanner;
import com.aj.game.utilities.JsonReader;

public class BoardService {
    private final JsonReader jsonReader;
    private final InputScanner scanner;

    public BoardService() {
        this.scanner = InputScanner.getInstance();
        this.jsonReader = JsonReader.getInstance();
    }

    public Board configure() throws BoardException {
        Board board = new Board();

        scanner.print("Enter the start of the board: ");
        board.setBoardStart(this.scanner.getInteger());

        if (board.getBoardStart() <= 0) {
            throw new BoardException();
        }

        scanner.print("Enter the end of the board: ");
        board.setBoardEnd(this.scanner.getInteger());

        scanner.printMessage("Values updated successfully ... ");
        scanner.printNewLine();

        return board;
    }

    public Board readFromConfig() throws BoardException {
        Board board = null;

        try {
            // Assuming your JSON is located in a file named "board.json"er.readValue(inputStream, Board.class);
            board = jsonReader.readJson("snakenladder/board.json", Board.class);
        } catch (Exception e) {
            throw new BoardException(e.getMessage());
        }

        return board;
    }
}

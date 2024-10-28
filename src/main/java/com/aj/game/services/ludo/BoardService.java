package com.aj.game.services.ludo;

import com.aj.game.exceptions.BoardException;
import com.aj.game.models.GameBoard;
import com.aj.game.models.ludo.Ludo;
import com.aj.game.models.ludo.LudoPosition;
import com.aj.game.models.ludo.config.Board;
import com.aj.game.utilities.InputScanner;
import com.aj.game.utilities.JsonReader;

public class BoardService {
    private final JsonReader jsonReader;
    private final InputScanner scanner;

    public BoardService() {
        this.scanner = InputScanner.getInstance();
        this.jsonReader = JsonReader.getInstance();
    }

    public Board readFromConfig() throws BoardException {
        Board board = null;

        try {
            // Assuming your JSON is located in a file named "board.json"er.readValue(inputStream, Board.class);
            board = jsonReader.readJson("ludo/board.json", Board.class);
        } catch (Exception e) {
            throw new BoardException(e.getMessage());
        }

        return board;
    }

    public void printPlayerBoardInformation(GameBoard gameBoard, String playerName) {
        Ludo ludo = (Ludo) gameBoard;
        ludo.getPlayers().forEach(player -> {
            if (!playerName.isEmpty() && !player.getName().equalsIgnoreCase(playerName)) {
                return;
            }

            scanner.printMessage("Pawn Details for the player: " + player.getName());
            scanner.printNewLine();
            ((LudoPosition) player.getPosition()).getGridPawns().forEach((key, pawn) -> {
                scanner.printMessage("Pawn number: " + pawn.getPawnNumber());
                scanner.printMessage("Pawn color: " + pawn.getColor().toString());
                scanner.printMessage("Pawn current position: " + pawn.getPosition());
                scanner.printNewLine();
            });
        });
    }
}

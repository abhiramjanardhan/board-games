package com.aj.game.services.ludo;

import com.aj.game.constants.ludo.LudoConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.models.GameBoard;
import com.aj.game.models.Player;
import com.aj.game.models.ludo.GridColor;
import com.aj.game.models.ludo.Ludo;
import com.aj.game.models.ludo.LudoPosition;
import com.aj.game.models.ludo.Pawn;
import com.aj.game.models.ludo.config.Board;
import com.aj.game.models.ludo.config.Grid;
import com.aj.game.utilities.InputScanner;
import com.aj.game.utilities.JsonReader;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
    private final JsonReader jsonReader;
    private final InputScanner scanner;
    private final GridService gridService;
    private Map<GridColor, Grid> colorGridMap;

    public BoardService() {
        this.scanner = InputScanner.getInstance();
        this.jsonReader = JsonReader.getInstance();
        this.gridService = new GridService();
        this.colorGridMap = new HashMap<>();
    }

    public void setColorGridMap(Map<GridColor, Grid> colorGridMap) {
        this.colorGridMap = colorGridMap;
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

    public boolean determinePositionSafety(String positionType) {
        return LudoConstants.SAFE_POSITIONS.contains(positionType);
    }

    public AbstractMap.SimpleEntry<Boolean, Grid> getCurrentPositionDetails(int position, int pawnNumber, Player currentPlayer) {
        LudoPosition currentPlayerPosition = (LudoPosition) currentPlayer.getPosition();
        Grid currentGrid = this.colorGridMap.get(currentPlayerPosition.getGridPawns().get(pawnNumber).getColor());
        String positionType = this.gridService.determineGridPosition(position, currentGrid);

        return new AbstractMap.SimpleEntry<>(this.determinePositionSafety(positionType), currentGrid);
    }

    public AbstractMap.SimpleEntry<Integer, Player> getOtherPlayerOnPosition(int position, int pawnNumber, Player currentPlayer, List<Player> players) {
        AbstractMap.SimpleEntry<Integer, Player> otherPlayer = null;
        AbstractMap.SimpleEntry<Boolean, Grid> details = this.getCurrentPositionDetails(position, pawnNumber, currentPlayer);

        if (details.getKey()) {
            return null;
        }

        for (Player player: players) {
            if (!player.getName().equalsIgnoreCase(currentPlayer.getName())) {
                LudoPosition otherPlayerPosition = (LudoPosition) player.getPosition();
                Map<Integer, Pawn> otherPlayerPawns = otherPlayerPosition.getGridPawns();
                boolean found = false;

                for (Map.Entry<Integer, Pawn> pawnEntry: otherPlayerPawns.entrySet()) {
                    Integer otherPawnNumber = pawnEntry.getKey();
                    Pawn otherPawn = pawnEntry.getValue();

                    if (otherPawn.getPosition() == position && !otherPawn.isCanEnterFinalLeg()) {
                        found = true;
                        otherPlayer = new AbstractMap.SimpleEntry<>(otherPawnNumber, player);
                        break;
                    }
                }

                if (found) {
                    break;
                }
            }
        }

        return otherPlayer;
    }
}

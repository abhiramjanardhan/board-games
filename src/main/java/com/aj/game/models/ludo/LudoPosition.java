package com.aj.game.models.ludo;

import com.aj.game.exceptions.BoardException;
import com.aj.game.models.Position;

import java.util.HashMap;
import java.util.Map;

public class LudoPosition extends Position {
    private Map<Integer, Pawn> gridPawns;

    public LudoPosition() {
        this.gridPawns = new HashMap<>();
    }

    public Map<Integer, Pawn> getGridPawns() {
        return gridPawns;
    }

    public void setGridPawns(Map<Integer, Pawn> gridPawns) {
        this.gridPawns = gridPawns;
    }

    private void validatePlayer(int hashCode) throws BoardException {
        if (!this.gridPawns.containsKey(hashCode)) {
            throw new BoardException("Player is not on board");
        }
    }

    private void validatePawn(int number) throws BoardException {
        if (!this.getGridPawns().containsKey(number)) {
            throw new BoardException("Invalid Pawn. Please check your input and try again.");
        }
    }

    @Override
    public String getCurrentPosition(String... parameters) throws BoardException {
        int pawnNumber = Integer.parseInt(parameters[0]);
        this.validatePawn(pawnNumber);
        Pawn pawn = this.getGridPawns().get(pawnNumber);

        return pawn.getColor().toString() + " " + pawn.getPosition();
    }

    @Override
    public void setCurrentPosition(String... parameters) throws BoardException {
        int pawnNumber = Integer.parseInt(parameters[0]);
        this.validatePawn(pawnNumber);
        int position = Integer.parseInt(parameters[1]);

        this.getGridPawns().get(pawnNumber).setPosition(position);
    }

    @Override
    public void printCurrentPosition(String... parameters) throws BoardException {
        scanner.printMessage("Current position: " + this.getCurrentPosition());
    }
}

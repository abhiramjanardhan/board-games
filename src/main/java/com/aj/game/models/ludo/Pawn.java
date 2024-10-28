package com.aj.game.models.ludo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn {
    private int pawnNumber;
    private boolean canStart;
    private int position;
    private GridColor color;
    private GridColor homeColor;
    private boolean enteredBaseAgain;
    private boolean crossedFinishLine;
    private boolean canEnterFinalLeg;

    public Pawn() {
        this.canStart = false;
        this.crossedFinishLine = false;
        this.canEnterFinalLeg = false;
        this.enteredBaseAgain = false;
        this.position = -1;
    }

    public int getPawnNumber() {
        return pawnNumber;
    }

    public void setPawnNumber(int pawnNumber) {
        this.pawnNumber = pawnNumber;
    }

    public boolean isCanStart() {
        return canStart;
    }

    public void setCanStart(boolean canStart) {
        this.canStart = canStart;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public GridColor getColor() {
        return color;
    }

    public void setColor(GridColor color) {
        this.color = color;
    }

    public GridColor getHomeColor() {
        return homeColor;
    }

    public void setHomeColor(GridColor homeColor) {
        this.homeColor = homeColor;
    }

    public boolean isEnteredBaseAgain() {
        return enteredBaseAgain;
    }

    public void setEnteredBaseAgain(boolean enteredBaseAgain) {
        this.enteredBaseAgain = enteredBaseAgain;
    }

    public boolean isCrossedFinishLine() {
        return crossedFinishLine;
    }

    public void setCrossedFinishLine(boolean crossedFinishLine) {
        this.crossedFinishLine = crossedFinishLine;
    }

    public boolean isCanEnterFinalLeg() {
        return canEnterFinalLeg;
    }

    public void setCanEnterFinalLeg(boolean canEnterFinalLeg) {
        this.canEnterFinalLeg = canEnterFinalLeg;
    }

    public static List<GridColor> gridColors() {
        return new ArrayList<>(Arrays.asList(GridColor.values()));
    }
}

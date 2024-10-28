package com.aj.game.models.ludo;

import com.aj.game.models.Dice;
import com.aj.game.models.GameBoard;

public class Ludo  extends GameBoard {
    private boolean requireSecondDice;
    private Dice secondDice;
    private boolean requireInitialStartCondition;

    public Ludo() {
        this.setStart(-1);
        this.setEnd(Integer.MAX_VALUE);
        this.requireSecondDice = false;
        this.secondDice = null;
        this.requireInitialStartCondition = true;
    }

    public boolean isRequireSecondDice() {
        return requireSecondDice;
    }

    public void setRequireSecondDice(boolean requireSecondDice) {
        this.requireSecondDice = requireSecondDice;
    }

    public Dice getSecondDice() {
        return secondDice;
    }

    public void setSecondDice(Dice secondDice) {
        this.secondDice = secondDice;
    }

    public boolean isRequireInitialStartCondition() {
        return requireInitialStartCondition;
    }

    public void setRequireInitialStartCondition(boolean requireInitialStartCondition) {
        this.requireInitialStartCondition = requireInitialStartCondition;
    }
}

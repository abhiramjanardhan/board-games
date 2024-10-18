package com.aj.game.models;

import com.aj.game.exceptions.DiceException;
import com.aj.game.utilities.InputScanner;

import java.util.Random;

public class Dice {
    private int diceCount;
    private InputScanner scanner;

    public Dice(int diceCount) throws DiceException {
        this.diceCount = diceCount;

        if (this.diceCount <= 0) {
            throw new DiceException();
        }
    }

    public Dice() throws DiceException {
        this.diceCount = 0;
        this.scanner = new InputScanner();
        this.updateDiceCount();
    }

    private void updateDiceCount() throws DiceException {
        scanner.printMessage("Dice configuration is starting ...");
        scanner.print("Please enter the dice count = ");
        this.diceCount = scanner.getInteger();
        scanner.printMessage("Dice value is updated!");
        scanner.printNewLine();

        if (this.diceCount <= 0) {
            throw new DiceException();
        }
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(this.diceCount) + 1;
    }
}

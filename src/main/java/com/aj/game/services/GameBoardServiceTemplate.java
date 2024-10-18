package com.aj.game.services;

import com.aj.game.models.GameBoard;

public interface GameBoardServiceTemplate {
    GameBoard configure() throws Exception;
    void displayRules();
    void play(GameBoard gameBoard);
}

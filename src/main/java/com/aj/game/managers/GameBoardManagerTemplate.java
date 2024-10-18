package com.aj.game.managers;

import com.aj.game.models.GameBoard;

public interface GameBoardManagerTemplate {
    GameBoard configure() throws Exception;
    void validate(GameBoard gameBoard);
    void play(GameBoard gameBoard);
}

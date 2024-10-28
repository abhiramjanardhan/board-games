package com.aj.game.managers.ludo;

import com.aj.game.constants.GameConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.managers.GameBoardManager;
import com.aj.game.models.GameBoard;

public class LudoManager extends GameBoardManager {
    public LudoManager() throws BoardException {
        super(GameConstants.LUDO);
    }

    @Override
    public void validate(GameBoard gameBoard) {

    }
}

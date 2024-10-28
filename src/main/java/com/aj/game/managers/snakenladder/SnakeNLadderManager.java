package com.aj.game.managers.snakenladder;

import com.aj.game.constants.GameConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.managers.GameBoardManager;
import com.aj.game.models.GameBoard;
import com.aj.game.models.snakenladder.SnakeNLadder;

import java.util.ArrayList;
import java.util.List;

public class SnakeNLadderManager extends GameBoardManager {
    public SnakeNLadderManager() throws BoardException {
        super(GameConstants.SNAKE_N_LADDER);
    }

    @Override
    public void validate(GameBoard gameBoard) {
        SnakeNLadder snakeNLadder = (SnakeNLadder) gameBoard;

        scanner.printMessage("Finalizing details ...");
        List<Integer> snakeStartEndValues = new ArrayList<>();
        List<Integer> ladderStartEndValues = new ArrayList<>();

        snakeNLadder.getSnakes().forEach(snake -> {
            snakeStartEndValues.add(snake.getStart());
            snakeStartEndValues.add(snake.getEnd());
        });

        snakeNLadder.getLadders().forEach(ladder -> {
            ladderStartEndValues.add(ladder.getStart());
            ladderStartEndValues.add(ladder.getEnd());
        });

        snakeNLadder.getSnakes().forEach(snake -> {
            if (ladderStartEndValues.contains(snake.getStart()) || ladderStartEndValues.contains(snake.getEnd())) {
                try {
                    throw new BoardException("Snake position cannot land on any ladder");
                } catch (BoardException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        snakeNLadder.getLadders().forEach(ladder -> {
            if (snakeStartEndValues.contains(ladder.getStart()) || snakeStartEndValues.contains(ladder.getEnd())) {
                try {
                    throw new BoardException("Ladder position cannot land on any snake");
                } catch (BoardException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        scanner.printMessage("The entered configuration is valid ...");
        scanner.printNewLine();
    }
}

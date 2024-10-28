package com.aj.game.services.snakenladder;

import com.aj.game.constants.GameConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.mappers.snakenladder.LaddersMapper;
import com.aj.game.mappers.snakenladder.SnakesMapper;
import com.aj.game.models.Dice;
import com.aj.game.models.GameBoard;
import com.aj.game.models.Player;
import com.aj.game.models.snakenladder.Ladder;
import com.aj.game.models.snakenladder.Snake;
import com.aj.game.models.snakenladder.SnakeNLadder;
import com.aj.game.models.snakenladder.config.Board;
import com.aj.game.models.snakenladder.config.Ladders;
import com.aj.game.models.snakenladder.config.Snakes;
import com.aj.game.services.GameBoardService;

import java.util.List;

public class SnakeNLadderService extends GameBoardService {

    private final BoardService boardService;
    private final SnakeService snakeService;
    private final LadderService ladderService;

    public SnakeNLadderService() throws BoardException {
        super(GameConstants.SNAKE_N_LADDER);
        this.boardService = new BoardService();
        this.snakeService = new SnakeService();
        this.ladderService = new LadderService();
    }

    @Override
    public GameBoard configure() throws Exception {
        SnakeNLadder snakeNLadder = new SnakeNLadder();

        scanner.printNewLine();
        scanner.printMessage("Please configure the snake and ladder board!");

        scanner.printMessage("Enter the board configurations ...");

        snakeNLadder = (SnakeNLadder) this.configureCommonInformation(snakeNLadder);

        scanner.print("Do you want to use pre-defined board configuration (Y/y - Yes, N/n - No): ");
        String predefined = this.scanner.getString();

        if (predefined.equalsIgnoreCase("Y")) {
            scanner.printNewLine();
            Board board = boardService.readFromConfig();
            Snakes snakes = snakeService.readFromConfig();
            Ladders ladders = ladderService.readFromConfig();

            snakeNLadder.setDice(new Dice(board.getDiceCount()));
            snakeNLadder.setStart(board.getBoardStart());
            snakeNLadder.setEnd(board.getBoardEnd());

            List<Snake> translatedSnakes = SnakesMapper.INSTANCE.detailsToSnakesData(snakes.getSnakes());
            List<Ladder> translatedLadders = LaddersMapper.INSTANCE.detailsToLaddersData(ladders.getLadders());

            snakeNLadder.setNumberOfSnakes(snakes.getNumberOfSnakes());
            snakeNLadder.setSnakes(translatedSnakes);

            snakeNLadder.setNumberOfLadders(ladders.getNumberOfLadders());
            snakeNLadder.setLadders(translatedLadders);

        } else {
            scanner.printNewLine();
            snakeNLadder.setDice(new Dice());

            Board board = boardService.configure();
            snakeNLadder.setStart(board.getBoardStart());
            snakeNLadder.setEnd(board.getBoardEnd());

            scanner.print("Enter the number of snakes on the board = ");
            int numberOfSnakes = this.scanner.getInteger();
            scanner.printNewLine();
            snakeNLadder.setNumberOfSnakes(numberOfSnakes);

            if (snakeNLadder.getNumberOfSnakes() <= 0) {
                throw new BoardException();
            }

            for (int i = 0 ; i < snakeNLadder.getNumberOfSnakes() ; i++) {
                snakeNLadder.getSnakes().add(snakeService.configure());
            }

            scanner.print("Enter the number of ladders on the board = ");
            int numberOfLadders = this.scanner.getInteger();
            snakeNLadder.setNumberOfLadders(numberOfLadders);

            if (snakeNLadder.getNumberOfLadders() <= 0) {
                throw new BoardException();
            }

            for (int i = 0 ; i < snakeNLadder.getNumberOfLadders() ; i++) {
                snakeNLadder.getLadders().add(ladderService.configure());
            }
        }

        scanner.printMessage("Board is configured successfully ...");

        scanner.printMessage("Performing additional validation ...");
        this.validate(snakeNLadder);
        scanner.printMessage("Information is validated successfully ...");

        scanner.printNewLine();
        scanner.printMessage("The board is configured successfully with the below details:");
        scanner.printNewLine();

        snakeNLadder.printPlayerStatus();
        snakeNLadder.printBoardDetails();

        scanner.printMessage("Please enjoy the game ...");
        scanner.printNewLine();

        return snakeNLadder;
    }

    private void validate(SnakeNLadder gameBoard) {
        gameBoard.getSnakes().forEach(snake -> {
            if (snake.getStart() < gameBoard.getStart() || snake.getStart() > gameBoard.getEnd()
                    || snake.getEnd() < gameBoard.getStart() || snake.getEnd() > gameBoard.getEnd()) {
                try {
                    throw new BoardException("Snake cannot be placed outside the board. Please check your input and try again");
                } catch (BoardException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        gameBoard.getLadders().forEach(ladder -> {
            if (ladder.getStart() < gameBoard.getStart() || ladder.getStart() > gameBoard.getEnd()
                    || ladder.getEnd() < gameBoard.getStart() || ladder.getEnd() > gameBoard.getEnd()) {
                try {
                    throw new BoardException("Ladder cannot be placed outside the board. Please check your input and try again");
                } catch (BoardException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void displayRules() {
        scanner.printMessage("Welcome to Snake and Ladder ... ");
        scanner.printMessage("Let us go over the rules ...");

        scanner.printMessage("1. There can be 1 - 6 players at a time");
        scanner.printMessage("2. Each player plays his/her turn until they reach/cross the last point in the board");
        scanner.printMessage("3. Player who reaches the very end first is determined the winner of the game");
        scanner.printMessage("4. If the player arrives at the bottom of the ladder, then the player will directly move to the top of the ladder");
        scanner.printMessage("5. If the player arrives at the snake's head, then the player will directly move to the tail of the snake");
        scanner.printMessage("6. Players can land on the same position on the board");

        scanner.printMessage("Enjoy the game!");
        scanner.printNewLine();
    }

    @Override
    public void play(GameBoard gameBoard) throws BoardException {
        SnakeNLadder snakeNLadder = (SnakeNLadder) gameBoard;
        Player winner = null;
        boolean playGoesOn = true, quit = false;
        this.displayRules();

        do {
            for (Player player: snakeNLadder.getPlayers()) {
                scanner.printMessage("Current position of player: " + player.getName() + " is: " + player.getPosition().getCurrentPosition());
                scanner.printNewLine();

                String letsRoll = "";
                do {
                    scanner.print("Enter Y/y to roll the dice (E/e to exit): ");
                    letsRoll = this.scanner.getString();
                    if (letsRoll.equalsIgnoreCase("e")) {
                        quit = true;
                        break;
                    }
                } while (!letsRoll.equalsIgnoreCase("Y"));

                if (quit) {
                    playGoesOn = false;
                    scanner.printNewLine();
                    scanner.printMessage("Quitting the game ... Farewell!");
                    scanner.printNewLine();
                    break;
                }

                int diceValue = snakeNLadder.getDice().rollDice();
                scanner.printMessage("Rolling Dice ...");
                scanner.printMessage("Dice value is: " + diceValue);
                scanner.printNewLine();

                int playerCurrentPosition = Integer.parseInt(player.getPosition().getCurrentPosition());
                player.getPosition().setCurrentPosition(String.valueOf(playerCurrentPosition + diceValue));

                scanner.printMessage("Moving player " + player.getName() + " by " + diceValue + " places");
                scanner.printNewLine();

                // update the current position
                playerCurrentPosition = Integer.parseInt(player.getPosition().getCurrentPosition());

                for (Snake snake: snakeNLadder.getSnakes()) {
                    if (playerCurrentPosition == snake.getStart()) {
                        scanner.printMessage("Ouch! Player " + player.getName() + " is bitten by the snake!");
                        player.getPosition().setCurrentPosition(String.valueOf(snake.getEnd()));
                        scanner.printNewLine();
                        break;
                    }
                }

                for (Ladder ladder: snakeNLadder.getLadders()) {
                    if (playerCurrentPosition == ladder.getStart()) {
                        scanner.printMessage("Hurray! Player " + player.getName() + " climbs the ladder!");
                        player.getPosition().setCurrentPosition(String.valueOf(ladder.getEnd()));
                        scanner.printNewLine();
                        break;
                    }
                }

                // update the current position
                playerCurrentPosition = Integer.parseInt(player.getPosition().getCurrentPosition());

                scanner.printMessage("Player " + player.getName() + " is moved to position " + playerCurrentPosition);
                scanner.printNewLine();

                scanner.print("Display current player status (Y/y - Yes, N/n - No): ");
                String printStatus = scanner.getString();
                if (printStatus.equalsIgnoreCase("Y")) {
                    snakeNLadder.printPlayerStatus();
                }

                if (playerCurrentPosition >= snakeNLadder.getEnd()) {
                    winner = player;
                    playGoesOn = false;
                    break;
                }

                scanner.printNewLine();
                scanner.printMessage("Time for the next player to play!");
                scanner.printMessage("-------------------------");
                scanner.printNewLine();
            }
        } while (playGoesOn);

        if (!quit) {
            scanner.printMessage("Congratulations on completing the game!");
            scanner.printMessage("The winner is: " + winner.getName());
            scanner.printNewLine();
        }
    }
}

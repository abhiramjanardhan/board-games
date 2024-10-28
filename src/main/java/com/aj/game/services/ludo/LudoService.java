package com.aj.game.services.ludo;

import com.aj.game.constants.GameConstants;
import com.aj.game.constants.ludo.LudoConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.models.Dice;
import com.aj.game.models.GameBoard;
import com.aj.game.models.Player;
import com.aj.game.models.ludo.GridColor;
import com.aj.game.models.ludo.Ludo;
import com.aj.game.models.ludo.LudoPosition;
import com.aj.game.models.ludo.Pawn;
import com.aj.game.models.ludo.config.Board;
import com.aj.game.models.ludo.config.Grid;
import com.aj.game.models.ludo.config.GridDetail;
import com.aj.game.services.GameBoardService;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LudoService extends GameBoardService {
    private final BoardService boardService;
    private final GridService gridService;
    private final Map<GridColor, Grid> colorGridMap;
    private int totalSpacesPerGrid;
    private int finalLegLength;
    private int homeRunPosition;

    public LudoService() throws BoardException {
        super(GameConstants.LUDO);
        this.boardService = new BoardService();
        this.gridService = new GridService();
        this.colorGridMap = new HashMap<>();
    }

    @Override
    public GameBoard configure() throws Exception {
        Ludo ludo = new Ludo();

        scanner.printNewLine();
        scanner.printMessage("Please configure the snake and ladder board!");

        scanner.printMessage("Enter the board configurations ...");

        ludo = (Ludo) this.configureCommonInformation(ludo);

        scanner.printMessage("Configuring the board ...");
        scanner.printNewLine();

        Board board = boardService.readFromConfig();

        scanner.print("Do you need to add one more dice into the game? (Y/y - Yes, N/n - No): ");
        String requireSecondDice = scanner.getString();
        ludo.setDice(new Dice(board.getDiceCount()));

        if (requireSecondDice.equalsIgnoreCase("Y")) {
            ludo.setRequireSecondDice(true);
            ludo.setSecondDice(new Dice(board.getDiceCount()));
            scanner.printMessage("Second dice is added into the game!");
        } else {
            scanner.printMessage("Board is configured with only one dice!");
        }
        scanner.printNewLine();

        scanner.print("Does the player need to roll 6 to start the pawn from the base? (Y/y - Yes, N/n - No): ");
        String requireInitialStartCondition = scanner.getString();
        ludo.setRequireInitialStartCondition(requireInitialStartCondition.equalsIgnoreCase("Y"));

        if (ludo.isRequireInitialStartCondition()) {
            scanner.printMessage("The board is configured to have the pawn roll 6 to start from the base position!");
        } else {
            scanner.printMessage("The board is configured to have the pawn roll to start immediately from the base position!");
        }
        scanner.printNewLine();

        List<GridColor> availableColors = Pawn.gridColors();

        // update the additional configuration details of the grid
        Grid grid = this.gridService.readFromConfig();
        this.totalSpacesPerGrid = grid.getTotalSpacesPerGrid();
        this.finalLegLength = grid.getFinalLegLength();
        this.homeRunPosition = this.finalLegLength + 1;
        availableColors.forEach(gridColor -> this.colorGridMap.put(gridColor, grid));
        this.boardService.setColorGridMap(this.colorGridMap);

        for (int i = 0; i < ludo.getPlayers().size() ; i++) {
            Player player = ludo.getPlayers().get(i);
            LudoPosition position = (LudoPosition) player.getPosition();

            AtomicInteger index = new AtomicInteger(1);
            scanner.printMessage("List of available colors the color for the player: " + player.getName());
            availableColors.forEach(color -> scanner.printMessage(index.getAndIncrement() + ". " + color));

            boolean validColor = false;
            GridColor gridColor = null;
            do {
                try {
                    scanner.print("Choose the color: ");
                    gridColor = GridColor.fromString(scanner.getString());
                    scanner.printNewLine();
                    validColor = true;
                } catch (Exception ignored) {
                    scanner.printMessage("Invalid color. Please try again!");
                    scanner.printNewLine();
                }
            } while (!validColor);

            for (int j = 1; j <= 4; j++) {
                Pawn pawn = new Pawn();
                pawn.setPawnNumber(j);
                pawn.setColor(gridColor);
                pawn.setHomeColor(gridColor);
                ((LudoPosition) player.getPosition()).getGridPawns().put(j, pawn);
            }

            player.setPosition(position);
            ludo.getPlayers().set(i, player);

            availableColors.remove(gridColor);

            scanner.printMessage("Details successfully updated for player: " + player.getName());
            scanner.printNewLine();
        }

        this.boardService.printPlayerBoardInformation(ludo, "");

        return ludo;
    }

    @Override
    public void displayRules() {
        scanner.printMessage("Welcome to Ludo ... ");
        scanner.printMessage("Let us go over the rules ...");

        scanner.printMessage("1. There can be 1 - 4 players at a time");
        scanner.printMessage("2. Each player can select either one of the 4 home bases - RED / GREEN / BLUE / YELLOW");
        scanner.printMessage("3. Each player gets 4 pawns to move around the board");
        scanner.printMessage("4. Upon start each pawn cannot move onto the board from their home bases until the dice value is 6");
        scanner.printMessage("5. Once the dice is rolled a 6, then a pawn can be selected to move on board into its home base position");
        scanner.printMessage("6. The pawns move around the board through each grid until it reaches its home grid again");
        scanner.printMessage("7. Once the pawn comes back to the home grid, it can enter the home run stretch and try to reach the home run");
        scanner.printMessage("8. The other player can try to place his pawn on top of the other player pawn - making that pawn to go back to its home base and try again");
        scanner.printMessage("9. There are 3 safe spots on each grid and if the pawn is there then that pawn cannot be sent back to its home base and that spot can be shared by multiple pawns");
        scanner.printMessage("10. The safe spots are as follows:");
        scanner.printMessage("a. 4th position of the grid");
        scanner.printMessage("b. 7th position of the grid (which is also the entry point for the home run)");
        scanner.printMessage("c. 9th position of the grid (which is the home base of the grid color)");
        scanner.printMessage("11. The pawns move in a specific order. For ex: If the player has chosen RED as the home base then the pawn will move in the order: RED -> GREEN -> YELLOW -> BLUE -> RED");
        scanner.printMessage("12. Once the pawn reaches the entry point safe spot or crosses it, it will then progress towards the home run");
        scanner.printMessage("13. The home run stretch is 7 spaces where 7th position is the home run position - The pawn has to specifically land on the home run position to complete, otherwise the pawn has to try again");
        scanner.printMessage("14. The game continues until all 4 pawns of the player has reached the home run position");
        scanner.printMessage("15. The first player to have all 4 pawns reach the home base is the winner of the game");

        scanner.printMessage("Enjoy the game!");
        scanner.printNewLine();
    }

    @Override
    public void play(GameBoard gameBoard) {
        Ludo ludo = (Ludo) gameBoard;
        Player winner = null;
        boolean playGoesOn = true, quit = false;
        this.displayRules();

        do {
            for (Player player: ludo.getPlayers()) {
                scanner.printMessage("Player " + player.getName() + " has to roll the dice ...");

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

                int diceValue1 = ludo.getDice().rollDice();
                int diceValue2 = 0;
                if (ludo.isRequireSecondDice()) {
                    diceValue2 = ludo.getSecondDice().rollDice();
                }

                int diceValue = diceValue1 + diceValue2;
                scanner.printMessage("Rolling Dice ...");
                scanner.printMessage("Dice value is: " + diceValue);
                scanner.printNewLine();

                LudoPosition position = (LudoPosition) player.getPosition();
                scanner.printMessage("Available pawns to move ...");

                Map<Integer, Pawn> pawns = position.getGridPawns();
                scanner.printMessage("Note:");
                scanner.printMessage("1. Select only the pawn that can be moved, if not your turn will end.");
                scanner.printMessage("2. The pawn which has completed the finish line will not move.");
                scanner.printNewLine();
                pawns.forEach((integer, pawn) -> {
                    if (!pawn.isCrossedFinishLine()) {
                        scanner.printMessage("Pawn: " + integer + ". Can move?: " + (pawn.isCanStart() && !pawn.isCrossedFinishLine() ? "Yes. Current Position: " + pawn.getColor() + " - " + pawn.getPosition() : "No. Reason: " + (pawn.isCrossedFinishLine() ? "Reached Finish Line" : "Still at Home Base")));
                    }
                });

                int pawnNumber = -1;
                do {
                    scanner.print("Select the required pawn to move: ");
                    int interimPawn = scanner.getInteger();

                    if (interimPawn < 1 || interimPawn > 4) {
                        scanner.printMessage("Invalid pawn selected, please try again ...");
                    } else {
                        pawnNumber = interimPawn;
                    }
                } while (pawnNumber == -1);

                Pawn pawn = pawns.get(pawnNumber);
                GridColor currentColor = pawn.getColor();

                boolean canMovePawn = true;
                Map<String, GridDetail> currentGridDetailsMap = this.gridService.getCurrentGridDetails(this.colorGridMap.get(currentColor));
                GridDetail homeBase = currentGridDetailsMap.get(LudoConstants.SAFE_N_HOME);

                if (ludo.isRequireInitialStartCondition()) {
                    if (!pawn.isCanStart()) {
                        if (diceValue1 == 6 || diceValue2 == 6) {
                            pawn.setCanStart(true);
                            pawn.setPosition(homeBase.getPosition());
                            scanner.printMessage("The pawn number: " + pawn.getPawnNumber() + " can start to move now. It is moved to the home base: " + pawn.getColor() + ": " + pawn.getPosition());
                            scanner.printNewLine();

                            scanner.printMessage("Roll the dice again to move ...");
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

                            diceValue1 = ludo.getDice().rollDice();
                            diceValue2 = 0;
                            if (ludo.isRequireSecondDice()) {
                                diceValue2 = ludo.getSecondDice().rollDice();
                            }

                            diceValue = diceValue1 + diceValue2;
                            scanner.printMessage("Rolling Dice ...");
                            scanner.printMessage("Dice value is: " + diceValue);
                            scanner.printNewLine();

                        } else {
                            scanner.printMessage("The pawn number: " + pawn.getPawnNumber() + " cannot be moved as you have not rolled 6. Please try again ...");
                            scanner.printNewLine();
                            canMovePawn = false;
                        }
                    }
                } else {
                    // if the configuration is set such that the pawn can start directly, then move the pawn to the home base position
                    if (!pawn.isCanStart()) {
                        pawn.setCanStart(true);
                        pawn.setPosition(homeBase.getPosition());
                        scanner.printMessage("The pawn number: " + pawn.getPawnNumber() + " can start to move now. It is moved to the home base: " + pawn.getColor() + ": " + pawn.getPosition());
                        scanner.printNewLine();
                    }
                }

                if (canMovePawn) {
                    int currentPosition = pawn.getPosition();
                    int interimNextPosition = currentPosition + diceValue;

                    if (pawn.isCanEnterFinalLeg()) {
                        // the pawn is in the final stretch, so here there are no other conditions to check
                        // verify whether the pawn has directly landed to the home run position
                        if (pawn.getPosition() == this.homeRunPosition) {
                            scanner.printMessage("The pawn: " + pawn.getPawnNumber() + " successfully moves into the home run position ... Congratulations!");
                            scanner.printNewLine();
                            pawn.setCrossedFinishLine(true);
                        } else if (interimNextPosition > this.homeRunPosition) {
                            scanner.printMessage("The pawn: " + pawn.getPawnNumber() + " cannot be moved as it is trying to cross the home run position ... Please try again next time!");
                            scanner.printNewLine();
                        } else {
                            pawn.setPosition(interimNextPosition);
                            scanner.printMessage("The pawn: " + pawn.getPawnNumber() + " is successfully moved to " + pawn.getColor() + ": " + pawn.getPosition() + " in the home run stretch!");
                            scanner.printNewLine();
                        }
                    } else {
                        GridColor nextColor = pawn.getColor();
                        if (interimNextPosition > this.totalSpacesPerGrid) {
                            nextColor = GridColor.getNextColor(nextColor);
                            pawn.setColor(nextColor);
                            interimNextPosition %= this.totalSpacesPerGrid;
                        }

                        AbstractMap.SimpleEntry<Boolean, Grid> details = this.boardService.getCurrentPositionDetails(interimNextPosition, pawnNumber, player);
                        boolean isCurrentPositionSafe = details.getKey();
                        Grid currentGrid = details.getValue();
                        boolean hasEnteredFinalLeg = false;
                        boolean hasCompletedCycle = GridColor.hasCompletedCycle(pawn.getHomeColor(), currentColor);

                        if (hasCompletedCycle && !pawn.isEnteredBaseAgain() && pawn.getColor() == pawn.getHomeColor()) {
                            pawn.setEnteredBaseAgain(true);
                        }

                        // this means that the pawn has come back to the home grid, now we need to see whether it can go to the final home run
                        if ((hasCompletedCycle && nextColor == pawn.getHomeColor()) || pawn.isEnteredBaseAgain()) {
                            Map<String, GridDetail> gridDetailMap = this.gridService.getCurrentGridDetails(currentGrid);
                            GridDetail entryPoint = gridDetailMap.get(LudoConstants.SAFE_N_ENTRY);

                            // this means that the pawn is trying to move past the entry point and needs to be redirected to the home run stretch
                            if (interimNextPosition > entryPoint.getPosition()) {
                                pawn.setCanEnterFinalLeg(true);
                                interimNextPosition %= this.finalLegLength;
                                hasEnteredFinalLeg = true;
                            }
                        }

                        boolean noBlockers = true;
                        if (hasEnteredFinalLeg) {
                            // verify whether the pawn has directly landed to the home run position
                            if (interimNextPosition == this.homeRunPosition) {
                                scanner.printMessage("The pawn: " + pawn.getPawnNumber() + " successfully moves into the home run position ... Congratulations!");
                                scanner.printNewLine();
                                pawn.setCrossedFinishLine(true);
                            } else if (interimNextPosition > this.homeRunPosition) {
                                noBlockers = false;
                                scanner.printMessage("The pawn: " + pawn.getPawnNumber() + " cannot be moved as it is trying to cross the home run position ... Please try again next time!");
                                scanner.printNewLine();
                            } else {
                                scanner.printMessage("The pawn: " + pawn.getPawnNumber() + " has entered the final stretch!");
                                scanner.printNewLine();
                            }
                        } else {
                            // check if the current player lands on other player pawn, if so then update the other player pawn back to its home base
                            AbstractMap.SimpleEntry<Integer, Player> otherPlayerMap = this.boardService.getOtherPlayerOnPosition(interimNextPosition, pawnNumber, player, ludo.getPlayers());
                            if (otherPlayerMap != null && !isCurrentPositionSafe) {
                                Integer otherPlayerPawnNumber = otherPlayerMap.getKey();
                                Player otherPlayer = otherPlayerMap.getValue();

                                for (Player player1: ludo.getPlayers()) {
                                    if (player1.getName().equalsIgnoreCase(otherPlayer.getName())) {
                                        LudoPosition otherPlayerPosition = (LudoPosition) player1.getPosition();
                                        Pawn otherPlayerPawn = otherPlayerPosition.getGridPawns().get(otherPlayerPawnNumber);

                                        otherPlayerPawn.setPosition(-1);
                                        otherPlayerPawn.setCanStart(false);
                                        otherPlayerPawn.setColor(otherPlayerPawn.getHomeColor());

                                        scanner.printMessage("Player " + player.getName() + "'s pawn: " + pawnNumber + " has landed on the other player " + otherPlayer.getName() + "'s pawn: " + otherPlayerPawnNumber + ". The other player's pawn is moved back to " + otherPlayerPawn.getHomeColor() + " base position.");
                                        break;
                                    }
                                }
                            }
                        }

                        if (noBlockers) {
                            pawn.setPosition(interimNextPosition);
                            scanner.printMessage("The pawn: " + pawn.getPawnNumber() + " is successfully moved to " + pawn.getColor() + ": " + pawn.getPosition());
                            scanner.printNewLine();
                        }
                    }
                }
            }

            // verify whether all the pawns of the player has moved to final position to declare the winner
            boolean allPawnsAreDone = true;
            for (Player player: ludo.getPlayers()) {
                LudoPosition position = (LudoPosition) player.getPosition();
                Map<Integer, Pawn> pawns = position.getGridPawns();

                for (Map.Entry<Integer, Pawn> pawnEntry: pawns.entrySet()) {
                    Pawn pawn = pawnEntry.getValue();
                    allPawnsAreDone = allPawnsAreDone && pawn.isCrossedFinishLine();
                }

                if (allPawnsAreDone) {
                    winner = player;
                    playGoesOn = false;
                    break;
                }
            }
        } while (playGoesOn);

        if (!quit) {
            scanner.printMessage("Congratulations on completing the game!");
            scanner.printMessage("The winner is: " + winner.getName());
            scanner.printNewLine();
        }
    }
}

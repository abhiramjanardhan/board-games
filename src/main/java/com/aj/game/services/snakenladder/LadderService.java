package com.aj.game.services.snakenladder;

import com.aj.game.exceptions.BoardException;
import com.aj.game.exceptions.PawnPositionException;
import com.aj.game.models.snakenladder.Ladder;
import com.aj.game.models.snakenladder.config.Ladders;
import com.aj.game.utilities.InputScanner;
import com.aj.game.utilities.JsonReader;

public class LadderService {
    private final JsonReader jsonReader;
    private final InputScanner scanner;

    public LadderService() {
        this.scanner = InputScanner.getInstance();
        this.jsonReader = JsonReader.getInstance();
    }

    public Ladder configure() throws PawnPositionException {
        Ladder ladder = new Ladder();

        scanner.printMessage("Please enter the details for: " + ladder.getType());

        scanner.print("Please enter the name = ");
        ladder.setName(scanner.getString());

        scanner.print("Please enter the starting position = ");
        ladder.setStart(scanner.getInteger());

        scanner.print("Please enter the ending position = ");
        ladder.setEnd(scanner.getInteger());

        scanner.printMessage("Board values is updated for " + ladder.getType() + ": " + ladder.getName() + "!");

        scanner.printMessage("Validating information ...");
        this.validate(ladder);
        scanner.printMessage("Information is validated successfully for: " + ladder.getName());
        scanner.printNewLine();

        return ladder;
    }

    private void validate(Ladder ladder) throws PawnPositionException {
        if (ladder.getStart() <= 0 || ladder.getEnd() <= 0) {
            throw new PawnPositionException("Invalid start/end value");
        }

        if (ladder.getStart() == ladder.getEnd()) {
            throw new PawnPositionException("Start and end value cannot be the same");
        }

        if (ladder.getStart() > ladder.getEnd()) {
            throw new PawnPositionException();
        }
    }

    public Ladders readFromConfig() throws BoardException {
        Ladders ladders = null;
        try {
            // Assuming your JSON is located in a file named "ladders.json"
            ladders = jsonReader.readJson("snakenladder/ladders.json", Ladders.class);
        } catch (Exception e) {
            throw new BoardException(e.getMessage());
        }

        return ladders;
    }
}

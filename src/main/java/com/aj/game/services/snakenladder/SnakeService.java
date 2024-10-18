package com.aj.game.services.snakenladder;

import com.aj.game.exceptions.BoardException;
import com.aj.game.exceptions.PawnPositionException;
import com.aj.game.models.snakenladder.Snake;
import com.aj.game.models.snakenladder.config.Snakes;
import com.aj.game.utilities.InputScanner;
import com.aj.game.utilities.JsonReader;

public class SnakeService {
    private final JsonReader jsonReader;
    private final InputScanner scanner;

    public SnakeService() {
        this.scanner = new InputScanner();
        this.jsonReader = new JsonReader();
    }

    public Snake configure() throws PawnPositionException {
        Snake snake = new Snake();

        scanner.printMessage("Please enter the details for: " + snake.getType());

        scanner.print("Please enter the name = ");
        snake.setName(scanner.getString());

        scanner.print("Please enter the starting position = ");
        snake.setStart(scanner.getInteger());

        scanner.print("Please enter the ending position = ");
        snake.setEnd(scanner.getInteger());

        scanner.printMessage("Board values is updated for " + snake.getType() + ": " + snake.getName() + "!");

        scanner.printMessage("Validating information ...");
        this.validate(snake);
        scanner.printMessage("Information is validated successfully for: " + snake.getName());
        scanner.printNewLine();

        return snake;
    }

    private void validate(Snake snake) throws PawnPositionException {
        if (snake.getStart() <= 0 || snake.getEnd() <= 0) {
            throw new PawnPositionException("Invalid start/end value");
        }

        if (snake.getStart() == snake.getEnd()) {
            throw new PawnPositionException("Start and end value cannot be the same");
        }

        if (snake.getStart() < snake.getEnd()) {
            throw new PawnPositionException();
        }
    }

    public Snakes readFromConfig() throws BoardException {
        Snakes snakes = null;
        try {
            // Assuming your JSON is located in a file named "snakes.json"
            snakes = jsonReader.readJson("snakenladder/snakes.json", Snakes.class);
        } catch (Exception e) {
            throw new BoardException(e.getMessage());
        }

        return snakes;
    }
}

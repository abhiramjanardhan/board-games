package com.aj.game.services.ludo;

import com.aj.game.constants.ludo.LudoConstants;
import com.aj.game.exceptions.BoardException;
import com.aj.game.models.ludo.config.Grid;
import com.aj.game.models.ludo.config.GridDetail;
import com.aj.game.utilities.InputScanner;
import com.aj.game.utilities.JsonReader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class GridService {
    private final JsonReader jsonReader;
    private final InputScanner scanner;

    public GridService() {
        this.scanner = InputScanner.getInstance();
        this.jsonReader = JsonReader.getInstance();
    }

    public Grid readFromConfig() throws BoardException {
        Grid grid = null;

        try {
            // Assuming your JSON is located in a file named "grid.json"
            grid = jsonReader.readJson("ludo/grid.json", Grid.class);
        } catch (Exception e) {
            throw new BoardException(e.getMessage());
        }

        return grid;
    }

    public String determineGridPosition(int position, Grid grid) {
        String positionType = LudoConstants.STANDARD_POSITION;

        for (GridDetail gridDetail: grid.getDetails()) {
            if (gridDetail.getPosition() == position) {
                if (gridDetail.isHome()) {
                    positionType = LudoConstants.SAFE_N_HOME;
                } else {
                    if (gridDetail.isEntryForHomeRun()) {
                        positionType = LudoConstants.SAFE_N_ENTRY;
                    } else {
                        positionType = LudoConstants.SAFE_ONLY;
                    }
                }
            }
        }

        return positionType;
    }

    public Map<String, GridDetail> getCurrentGridDetails(Grid grid) {
        AtomicReference<GridDetail> safeOnly = new AtomicReference<>();
        AtomicReference<GridDetail> entryPoint = new AtomicReference<>();
        AtomicReference<GridDetail> safeNHome = new AtomicReference<>();

        grid.getDetails().forEach(gridDetail -> {
            if (gridDetail.isHome()) {
                safeNHome.set(gridDetail);
            } else {
                if (gridDetail.isEntryForHomeRun()) {
                    entryPoint.set(gridDetail);
                } else {
                    safeOnly.set(gridDetail);
                }
            }
        });

        Map<String, GridDetail> gridDetailMap = new HashMap<>();
        gridDetailMap.put(LudoConstants.SAFE_N_ENTRY, entryPoint.get());
        gridDetailMap.put(LudoConstants.SAFE_ONLY, safeOnly.get());
        gridDetailMap.put(LudoConstants.SAFE_N_HOME, safeNHome.get());

        return gridDetailMap;
    }
}

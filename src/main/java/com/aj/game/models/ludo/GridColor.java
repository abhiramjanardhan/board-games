package com.aj.game.models.ludo;

import java.util.LinkedHashMap;
import java.util.Map;

public enum GridColor {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    private static final Map<GridColor, Integer> gridColorPositionMap;

    static {
        gridColorPositionMap = new LinkedHashMap<>();
        gridColorPositionMap.put(RED, 1);
        gridColorPositionMap.put(GREEN, 2);
        gridColorPositionMap.put(YELLOW, 3);
        gridColorPositionMap.put(BLUE, 4);
    }

    public static GridColor fromString(String color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        try {
            return GridColor.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No enum constant for color: " + color);
        }
    }

    public static int getGridColorPosition(GridColor color) {
        return gridColorPositionMap.get(color);
    }

    public static GridColor getNextColor(GridColor currentColor) {
        int currentPosition = gridColorPositionMap.get(currentColor);
        int nextPosition = (currentPosition % gridColorPositionMap.size()) + 1; // Loop back to 1 if it's at the max position

        for (Map.Entry<GridColor, Integer> entry : gridColorPositionMap.entrySet()) {
            if (entry.getValue() == nextPosition) {
                return entry.getKey();
            }
        }
        return null; // This shouldn't happen as long as the map is well-formed
    }

    public static boolean hasCompletedCycle(GridColor startColor, GridColor currentColor) {
        return startColor == getNextColor(currentColor);
    }
}

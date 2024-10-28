package com.aj.game.models.ludo.config;

import java.util.List;

public class Grid {
    private int totalSpacesPerGrid;
    private int finalLegLength;
    private List<GridDetail> details;

    public int getTotalSpacesPerGrid() {
        return totalSpacesPerGrid;
    }

    public void setTotalSpacesPerGrid(int totalSpacesPerGrid) {
        this.totalSpacesPerGrid = totalSpacesPerGrid;
    }

    public int getFinalLegLength() {
        return finalLegLength;
    }

    public void setFinalLegLength(int finalLegLength) {
        this.finalLegLength = finalLegLength;
    }

    public List<GridDetail> getDetails() {
        return details;
    }

    public void setDetails(List<GridDetail> details) {
        this.details = details;
    }
}

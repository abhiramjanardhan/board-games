package com.aj.game.models.ludo.config;

public class GridDetail {
    private int position;
    private boolean isHome;
    private boolean entryForHomeRun;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setIsHome(boolean home) {
        isHome = home;
    }

    public boolean isEntryForHomeRun() {
        return entryForHomeRun;
    }

    public void setIsEntryForHomeRun(boolean entryForHomeRun) {
        this.entryForHomeRun = entryForHomeRun;
    }
}

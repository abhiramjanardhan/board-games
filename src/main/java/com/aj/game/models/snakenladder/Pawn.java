package com.aj.game.models.snakenladder;

abstract class Pawn {
    private int start;
    private int end;
    private String name;
    private String type;

    public Pawn(String type) {
        this.start = -1;
        this.end = -1;
        this.type = type;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }
}

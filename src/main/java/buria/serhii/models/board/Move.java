package buria.serhii.models.board;

import buria.serhii.models.pieces.Coordinates;

public class Move {
    public final Coordinates from;
    public final Coordinates to;

    public Move(Coordinates from, Coordinates coordinates) {
        this.from = from;
        to = coordinates;
    }
}
